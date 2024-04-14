package dev.auth_service.common.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.net.Inet4Address;
import java.net.UnknownHostException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
//    private final LogoutHandler logoutHandler;
//    private final CustomOAuth2UserService oAuth2UserService;
//    private final OAuth2AuthenticateSuccessHandler oAuth2AuthenticateSuccessHandler;
//
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests((authorize) -> {
                                    authorize
                                    .requestMatchers("/login", "/registration", "/css/**", "/js/**", "/img/**", "/api/v1/auth/**", "/data-socket/**").permitAll()
                                    .anyRequest().authenticated();
                        }
                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider);
//                .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//                .logout(logout ->
//                        logout.logoutUrl("/api/v1/auth/logout")
//                                .addLogoutHandler(logoutHandler)
//                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//                );
//                .oauth2Login(oauth2Login -> oauth2Login
//                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig.userService(oAuth2UserService))
//                        .successHandler(oAuth2AuthenticateSuccessHandler)
//                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() throws UnknownHostException {
        Inet4Address address = (Inet4Address) Inet4Address.getLocalHost();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(String.format("http://%s:8888", address.getHostAddress()));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}