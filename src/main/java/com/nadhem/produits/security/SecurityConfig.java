package com.nadhem.produits.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(session ->
                        //il ne gere pas les session
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())

                .cors(cors -> cors.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration cors = new CorsConfiguration();
                        cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        cors.setAllowedMethods(Collections.singletonList("*"));
                        cors.setAllowedHeaders(Collections.singletonList("*"));
                        cors.setExposedHeaders(Collections.singletonList("Authorization"));
                        return cors;
                    }
                }))

                .authorizeHttpRequests(
                        requests ->
                                requests.requestMatchers("/api/all/**").hasAnyAuthority("ADMIN", "USER")
                                        .requestMatchers(HttpMethod.GET, "/api/getById/**").hasAnyAuthority("ADMIN", "USER")
                                        //.requestMatchers(HttpMethod.POST, "/api/addProd/**").hasAuthority("ADMIN") on peut utiliser Prehautorized sur le controller
                                        .requestMatchers(HttpMethod.PUT, "/api/updateProd/**").hasAuthority("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/api/delProd/**").hasAuthority("ADMIN")
                                        .anyRequest().authenticated())
                .addFilterBefore(new JWTAuthorizationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
