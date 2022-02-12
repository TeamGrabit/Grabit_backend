package grabit.grabit_backend.Oauth2.handler;

import grabit.grabit_backend.Domain.UserRefreshToken;
import grabit.grabit_backend.Oauth2.repository.CustomAuthorizationRequestRepository;
import grabit.grabit_backend.Repository.UserRefreshTokenRepository;
import grabit.grabit_backend.auth.JwtProvider;
import grabit.grabit_backend.utils.CookieUtil;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import static grabit.grabit_backend.utils.CookieUtil.REDIRECT_URI_PARAM_COOKIE_NAME;
import static grabit.grabit_backend.utils.CookieUtil.REFRESH_TOKEN;

public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private JwtProvider jwtProvider;
    private UserRefreshTokenRepository userRefreshTokenRepository;
    private CustomAuthorizationRequestRepository authorizationRequestRepository;
    private long refreshTokenExpiry = 24 * 60 * 60 * 1000; // 24시간
    private long accessTokenExpiry = 12 * 60 * 60 * 1000; // 12시간

    public OAuth2AuthenticationSuccessHandler(
            JwtProvider jwtProvider,
            UserRefreshTokenRepository userRefreshTokenRepository,
            CustomAuthorizationRequestRepository authorizationRequestRepository
    ) {
        this.jwtProvider = jwtProvider;
        this.userRefreshTokenRepository = userRefreshTokenRepository;
        this.authorizationRequestRepository = authorizationRequestRepository;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = makeTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            return;
        }
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }


    protected String makeTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Optional<String> redirectUri = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME).map(Cookie::getValue);
        if (redirectUri.isPresent() && !isAuthRedirectUri(redirectUri.get())) {
            throw new IllegalArgumentException("Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
        }

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        OAuth2User user = (OAuth2User) authentication.getPrincipal();
        int userId = user.getAttribute("id");

        String refreshToken = jwtProvider.issueJwt(userId, refreshTokenExpiry);
        String accessToken = jwtProvider.issueJwt(userId, accessTokenExpiry);

        // refreshToken DB에 저장
        UserRefreshToken userRefreshToken = userRefreshTokenRepository.findByUserId(userId);
        if (userRefreshToken != null) {
            userRefreshToken.setRefreshToken(refreshToken);
        } else {
            userRefreshToken = new UserRefreshToken(userId, refreshToken);
            userRefreshTokenRepository.saveAndFlush(userRefreshToken);
        }
        // refreshToken Cookie에 담기
        int cookieMaxAge = (int) refreshTokenExpiry / 60;
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN);
        CookieUtil.addCookie(response, REFRESH_TOKEN, refreshToken, cookieMaxAge);

        // accessToken query param에 담기
        targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", accessToken)
                .build().toUriString();

        return targetUrl;
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    // TODO : redirect url이 유효한 url인지 확인
    private boolean isAuthRedirectUri(String uri) {
        URI clientRedirectUri = URI.create(uri);
        return true;
    }
}
