package company.board_project.global.config;

import company.board_project.global.security.jwt.component.JwtTokenizer;
import company.board_project.global.security.jwt.filter.JwtAuthenticationFilter;
import company.board_project.global.security.jwt.filter.JwtVerificationFilter;
import company.board_project.global.security.jwt.handler.*;
import company.board_project.global.security.oauth.OAuth2UserService;
import company.board_project.global.security.utils.CustomAuthorityUtils;
import company.board_project.global.security.utils.RedisUtils;
import company.board_project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;
    private final OAuth2UserService oAuth2UserService;
    private final UserRepository userRepository;
    private final RedisUtils redisUtils;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrfConfig)->
                        csrfConfig.disable()
                )
                .headers((headerConfig)->
                        headerConfig.frameOptions((frameOptionsConfig ->
                                frameOptionsConfig.sameOrigin())
                        )
                )
                .cors(withDefaults())
                .sessionManagement((sessionManagementConfig)->
                        sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .formLogin((formLoginConfig)->
                        formLoginConfig.disable()
                )
//                .httpBasic().disable()
                .exceptionHandling((exceptionConfig) ->
                        exceptionConfig.authenticationEntryPoint(new UserAuthenticationEntryPoint()).accessDeniedHandler(new UserAccessDeniedHandler())
                )
//                .apply()
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST ,"api/oauth","/api/login", "/api/users/join","/auth/email","/auth/password").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/auth/password").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/user").hasRole("USER")
                        .requestMatchers("/h2/**").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .requestMatchers( "/loading/**","/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(new OAuth2UserSuccessHandler(jwtTokenizer, userRepository,redisUtils))
                        .userInfoEndpoint(userInfoEndpointConfig ->
                                userInfoEndpointConfig.userService(oAuth2UserService)
                        )
                );

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @CrossOrigin
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer, redisUtils);
            jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new UserAuthenticationSuccessHandler());
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new UserAuthenticationFailureHandler());

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer, authorityUtils, redisUtils);

            builder.addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);
        }
    }
}