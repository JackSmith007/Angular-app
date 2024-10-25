package com.example.Simple.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfigurer {
	
	@Autowired
	
	private JwtUtil jwtutil;
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Customize your security configuration here
        http
        .cors().and() // Enable CORS
        .csrf().disable()
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers(HttpMethod.OPTIONS, "/auth").permitAll() // Allow OPTIONS requests to /auth
                    .requestMatchers("/auth","/error").permitAll()   // Public endpoints
                    
                .anyRequest().authenticated()
                
            )
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Set session management to stateless
            .and()
            .addFilterBefore(new JwtRequestFilter(myUserDetailsService(), jwtutil), UsernamePasswordAuthenticationFilter.class); // Add JWT filter
            

        return http.build();
        }

    // Define the UserDetailsService bean
    @Bean
    public UserDetailsService myUserDetailsService() {
        // Custom implementation or in-memory example
        return new InMemoryUserDetailsManager(
            User.withUsername("Awais")
                
                .password(passwordEncoder().encode("Boy"))
                .roles("USER")
                .build()
        );
    }

    // Configure the AuthenticationManager to use the custom UserDetailsService
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, 
                UserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder())
            .and()
            .build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Using BCrypt for password encoding
    }
}