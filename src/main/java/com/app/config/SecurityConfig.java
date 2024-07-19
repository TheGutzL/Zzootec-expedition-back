package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.app.config.filter.JwtTokenValidator;
import com.app.service.impl.UserDetailServiceImpl;
import com.app.util.JwtUtils;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // Configurar los Endpoints Publicos
                    http.requestMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll();

                    // Configurar los endpoints privados
                    http.requestMatchers(HttpMethod.GET, "/api/v1/products/**").hasAnyRole("ADMIN", "USER", "INVITED");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/products/**").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/products/**").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/products/**").hasAnyRole("ADMIN");

                    http.requestMatchers(HttpMethod.GET, "/api/v1/categories/**").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/categories/**").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/categories/**").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/categories/**").hasAnyRole("ADMIN");

                    http.requestMatchers(HttpMethod.GET, "/api/v1/users/**").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/users/**").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/users/**").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasAnyRole("ADMIN");

                    http.requestMatchers(HttpMethod.GET, "/api/v1/orders/**").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/orders/**").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/orders/**").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/orders/**").hasAnyRole("ADMIN");

                    http.requestMatchers(HttpMethod.GET, "/api/v1/addresses/**").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST, "/api/v1/addresses/**").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT, "/api/v1/addresses/**").hasAnyRole("ADMIN");
                    http.requestMatchers(HttpMethod.DELETE, "/api/v1/addresses/**").hasAnyRole("ADMIN");

                    // http.requestMatchers(HttpMethod.PATCH,
                    // "/method/patch").hasAnyAuthority("REFACTOR");
                    // http.requestMatchers(HttpMethod.GET, "/method/get").hasAnyAuthority("READ");

                    // Configurar el resto de endpoints - no especificados
                    http.anyRequest().denyAll();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailServiceImpl) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailServiceImpl);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
