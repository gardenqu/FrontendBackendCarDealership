package com.example.project.Dealership.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SpringSecurityConfigurationBasicAuth {

    @Autowired
    JwtAuthEntryPoint authEntryPoint;

    @Autowired
    private CustomUserDetailsService userDetailsService;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //1: Response to preflight request doesn't pass access control check
        //2: basic auth
        http
                // Allow all OPTIONS requests (useful for CORS preflight requests)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.OPTIONS, "/inventory/**").permitAll() // Allow OPTIONS globally
                        .requestMatchers("/auth/**").permitAll() // Permit all requests to /auth endpoints
                        .anyRequest().authenticated() // Require authentication for all other requests
                )

                // Enable HTTP Basic authentication (useful for simple APIs)
                .httpBasic(Customizer.withDefaults())

                // Configure session management to be stateless (no session creation)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless session management
                )

                // Disable CSRF protection (common for stateless APIs)
                .csrf(AbstractHttpConfigurer::disable)

                // Set a custom AuthenticationEntryPoint directly
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(authEntryPoint) // Custom authentication entry point for 401 responses
                );

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }






    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public  JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter();
    }


}



