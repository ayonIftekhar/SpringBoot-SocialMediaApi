package com.example.RestApi.inDetail.SecurityController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityController {
    @Bean

    public SecurityFilterChain getSecurityFilterChain
            (HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                auth-> auth.anyRequest().authenticated()
        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf().disable();
        return http.build();
    }
}
