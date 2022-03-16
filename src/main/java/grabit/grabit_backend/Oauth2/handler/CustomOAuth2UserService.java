package grabit.grabit_backend.Oauth2.handler;

import grabit.grabit_backend.domain.OAuthAttributes;
import grabit.grabit_backend.dto.SessionUser;
import grabit.grabit_backend.domain.User;
import grabit.grabit_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Autowired
    CustomOAuth2UserService(UserRepository userRepository, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.httpSession = httpSession;
    }
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest
                .getClientRegistration()
                .getRegistrationId();
        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
//        Set<String> ks = attributes.getAttributes().keySet();
//        for(String key: ks) {
//            System.out.println(key + " : "+attributes.getAttributes().get(key));
//        }
        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));

        OAuth2AccessToken accessToken = userRequest.getAccessToken();

        // TODO : access_token 필요하면 DB에 저장하기
        // TODO : access_token 만료시간 찾기.. 왜 발행시간과 만료시간이 같을까??

        return new DefaultOAuth2User(
            Collections.singleton(
                new SimpleGrantedAuthority("ROLE_USER")
            ),
            attributes.getAttributes(),
            attributes.getNameAttributeKey()
        );
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findById(attributes.getId()).orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
