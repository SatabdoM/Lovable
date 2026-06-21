package com.example.lovable.security;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final HandlerExceptionResolver handlerExceptionResolver;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection (commonly used for stateless REST APIs)
                .cors(Customizer.withDefaults()) // Enable CORS using the application's default configuration
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //Tell Spring security not to create Sessions
                .authorizeHttpRequests(auth -> auth
                        .dispatcherTypeMatchers(DispatcherType.ASYNC).permitAll() //Allow all asynchronous dispatch requests
                        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll() //Allow all error page/error handler dispatch requests
                        .requestMatchers("/api/auth/**", "/webhooks/**").permitAll() //Allow unauthenticated access to authentication and webhook endpoints
                        .anyRequest().authenticated() //Require authentication for every other request
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)// Enable JWT authentication filter before Spring's username/password filter
                .exceptionHandling(exceptionHandlingConfigurer ->
                        exceptionHandlingConfigurer.accessDeniedHandler(
                                (request, response, accessDeniedException) -> {
                                    handlerExceptionResolver.resolveException(
                                            request,
                                            response,
                                            null,
                                            accessDeniedException
                                    );
                                    // Delegate AccessDeniedException handling to Spring's global exception resolver
                                }));
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}