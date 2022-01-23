package grabit.grabit_backend.config.security;

import grabit.grabit_backend.Oauth2.handler.CustomOAuth2UserService;
import grabit.grabit_backend.Oauth2.handler.OAuth2AuthenticationSuccessHandler;
import grabit.grabit_backend.Oauth2.repository.CustomAuthorizationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;
    @Autowired
    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    /**
     * OAuth 인증 성공 핸들러
     */
    @Bean
    public OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler() {
        return new OAuth2AuthenticationSuccessHandler();
    }

    public CustomAuthorizationRequestRepository customAuthorizationRequestRepository() {
        return new CustomAuthorizationRequestRepository();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeRequests(a -> a
                        .antMatchers("/bada", "/error", "/webjars/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
               .oauth2Login()
                    .authorizationEndpoint().authorizationRequestRepository(customAuthorizationRequestRepository())
                .and()
                    .successHandler(oAuth2AuthenticationSuccessHandler())
                    .userInfoEndpoint()
                    .userService(customOAuth2UserService);


        // @formatter:on
    }
}