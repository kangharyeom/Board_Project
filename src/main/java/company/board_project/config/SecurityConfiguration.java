//package company.board_project.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
////@EnableWebSecurity(debug = true)
//public class SecurityConfiguration {
//    @Bean
//    public SecurityFilterChain filterChainV3(HttpSecurity http) throws Exception {
//        http
//                .headers().frameOptions().sameOrigin() // 설정 추가된 부분
//                .and()
//                .csrf().disable()
//                .formLogin()
//                .loginPage("/auths/login-form")
//                .loginProcessingUrl("/process_login")
//                .failureUrl("/auths/login-form?error")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/")
//                .and()
//                .exceptionHandling().accessDeniedPage("/auths/access-denied")
//                .and()
//                .authorizeHttpRequests(authorize -> authorize
//                        .antMatchers("/orders/**").hasRole("ADMIN")
//                        .antMatchers("/members/my-page").hasRole("USER")
//                        .antMatchers("/**").permitAll()
//                );
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//}