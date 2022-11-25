package com.fyeeme.resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/messages/**")
                .authorizeHttpRequests()
                .requestMatchers("/messages/**").hasAuthority("SCOPE_message.read")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }

    //The easiest way to ensure that CORS is handled first is to use the CorsWebFilter. Users can integrate the CorsWebFilter
    // with Spring Security by providing a CorsConfigurationSource.
    // For example, the following will integrate CORS support within Spring Security:
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://127.0.0.1:3000"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
//
//        cc.setAllowCredentials(true);
//        cc.setAllowedOrigins(List.of("http://127.0.0.1:3000"));
//        cc.setAllowedHeaders(List.of("*"));
//        cc.setAllowedMethods(List.of("*"));
    }

}
