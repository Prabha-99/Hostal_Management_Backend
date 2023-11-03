package com.example.hostal_management_b.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JWTAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors();
        // Disable Cross-Site Request Forgery (CSRF)
        http.csrf().disable();
        // Set the session creation policy to stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/login").permitAll()
                .requestMatchers("/api/auth/register").permitAll()
                .requestMatchers("/api/auth/regroom").permitAll()
                .requestMatchers("/api/auth/update-room").permitAll()
                .requestMatchers("/api/auth/count").permitAll()

                .requestMatchers("/api/qr/uploadImage").permitAll()
                .requestMatchers("/api/complain/**").permitAll()


                .requestMatchers("/api/room/add").permitAll()
                .requestMatchers("/api/room/all").permitAll()
                .requestMatchers("/api/room/count").permitAll()

                .requestMatchers("/api/property/add").permitAll()
                .requestMatchers("/api/property/all").permitAll()
                .requestMatchers("/api/property/count").permitAll()


                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
