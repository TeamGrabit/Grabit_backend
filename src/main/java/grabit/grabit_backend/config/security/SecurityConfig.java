package grabit.grabit_backend.config.security;

import grabit.grabit_backend.Oauth2.handler.CustomAuthorizationRequestResolver;
import grabit.grabit_backend.Oauth2.handler.CustomOAuth2UserService;
import grabit.grabit_backend.Oauth2.handler.OAuth2AuthenticationSuccessHandler;
import grabit.grabit_backend.Oauth2.repository.CustomAuthorizationRequestRepository;
import grabit.grabit_backend.Repository.UserRefreshTokenRepository;
import grabit.grabit_backend.auth.JwtAuthenticationFilter;
import grabit.grabit_backend.auth.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtProvider jwtProvider;
    private final UserRefreshTokenRepository userRefreshTokenRepository;
    private final ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService,
                          JwtProvider jwtProvider,
                          UserRefreshTokenRepository userRefreshTokenRepository, ClientRegistrationRepository clientRegistrationRepository) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.jwtProvider = jwtProvider;
        this.userRefreshTokenRepository = userRefreshTokenRepository;
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    /**
     * OAuth 인증 성공 핸들러
     */
    @Bean
    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler(jwtProvider, userRefreshTokenRepository, customAuthorizationRequestRepository());
    }

    public CustomAuthorizationRequestRepository customAuthorizationRequestRepository() {
        return new CustomAuthorizationRequestRepository();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .authorizeRequests()
                    .anyRequest().permitAll()//.hasAnyRole("ROLE_BADA", null, "ROLE_[BADA]", "[]")
//                    .antMatchers("/api/oauth2/authorization/**").permitAll()
//                    .antMatchers("/api/channel/**").hasAnyRole("USER")

                .and()
               .oauth2Login()
                    .authorizationEndpoint()
                        .authorizationRequestRepository(customAuthorizationRequestRepository())
                        .authorizationRequestResolver(new CustomAuthorizationRequestResolver(clientRegistrationRepository, "/api/oauth2/authorization"))
                .and()
                    .successHandler(oAuth2AuthenticationSuccessHandler())
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);

        // @formatter:on
    }
}