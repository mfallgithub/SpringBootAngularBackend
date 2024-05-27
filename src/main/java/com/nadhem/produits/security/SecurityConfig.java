package com.nadhem.produits.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest
                                                                          request) {
                        CorsConfiguration config = new CorsConfiguration();

                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(List.of("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }).and()


                .authorizeHttpRequests()
                .anyRequest().permitAll();
                        /*requests ->
                                requests.requestMatchers("/api/all/**").hasAnyAuthority("ADMIN", "USER")
                                        .requestMatchers(HttpMethod.GET, "/api/getById/**").hasAnyAuthority("ADMIN", "USER")
                                        //.requestMatchers(HttpMethod.POST, "/api/addProd/**").hasAuthority("ADMIN") on peut utiliser Prehautorized sur le controller
                                        .requestMatchers(HttpMethod.PUT, "/api/updateProd/**").hasAuthority("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/api/delProd/**").hasAuthority("ADMIN")
                                        .anyRequest().authenticated())
                .addFilterBefore(new JWTAuthorizationFilter(),
                        UsernamePasswordAuthenticationFilter.class);*/
        return http.build();
    }
}
