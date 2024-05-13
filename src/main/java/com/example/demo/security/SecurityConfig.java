package com.example.demo.security;

import com.example.demo.filters.CustomAuthenticationFilter;
import com.example.demo.filters.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManagerBuilder authManagerBuilder;

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authManagerBuilder.getOrBuild());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers(AUTH_WHITE_LIST).permitAll()
                .requestMatchers("/api/login").permitAll()
                .requestMatchers(GET, "/api/users").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(GET, "/api/users/{id}").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(POST, "/api/users").permitAll()
                .requestMatchers(PATCH, "/api/users/user/{id}").permitAll()
                .requestMatchers(DELETE, "/api/users/{id}").permitAll()
                .requestMatchers(GET, "/api/reservations").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(GET, "/api/reservations/{id}").permitAll()
                .requestMatchers(GET, "/api/reservations/byBookingDate").permitAll()
                .requestMatchers(POST, "/api/reservations").permitAll()
                .requestMatchers(PATCH, "/api/reservations/{id}").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(DELETE, "/api/reservations/{id}").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(GET, "/api/flights").permitAll()
                .requestMatchers(GET, "/api/flights/{id}").permitAll()
                .requestMatchers(GET, "/api/flights/byPrice").permitAll()
                .requestMatchers(GET, "/api/flights/byArrivalAirport").permitAll()
                .requestMatchers(GET, "/api/flights/byDepartureAirport").permitAll()
                .requestMatchers(GET, "/api/flights/byArrivalTime").permitAll()
                .requestMatchers(GET, "/api/flights/byDepartureTime").permitAll()
                .requestMatchers(POST, "/api/flights").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(PATCH, "/api/flights/{id}").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(DELETE, "/api/flights/{id}").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(POST, "/api/roles").hasAnyAuthority("ROLE_ADMIN")
                .requestMatchers(POST, "/api/roles/addtouser").hasAnyAuthority("ROLE_ADMIN")
                .anyRequest().authenticated());
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/swagger-ui-custom.html"
    };
}