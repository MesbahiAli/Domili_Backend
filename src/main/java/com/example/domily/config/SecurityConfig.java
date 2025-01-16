package com.example.domily.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;  
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()  // Enable CORS
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").hasRole("SWAGGER_USER")
                .anyRequest().authenticated()
            )
            .httpBasic();
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails swaggerUser = User.builder()
            .username("swagger-user")  // Change this to your desired username
            .password(passwordEncoder().encode("swagger-pass"))  // Change this to your desired password
            .roles("SWAGGER_USER")
            .build();
        
        return new InMemoryUserDetailsManager(swaggerUser);
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow requests from your React application
        config.addAllowedOrigin("http://localhost:5173");

        // Allow common HTTP methods
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");

        // Allow all headers
        config.addAllowedHeader("*");

        // Allow credentials
        config.setAllowCredentials(true);

        // Apply this configuration to all paths
        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


