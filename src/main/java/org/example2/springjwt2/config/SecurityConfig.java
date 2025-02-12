package org.example2.springjwt2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        // 1. CSRF disable
        // JWT는 세션을 stateless 한 상태로 관리.
        http
                .csrf(auth -> auth.disable());
        http
                .formLogin(auth -> auth.disable());

        http
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers("").authenticated()
                        .requestMatchers("").hasAnyAuthority()
                        .anyRequest().permitAll()
                );
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));



        return http.build();

    }

}
