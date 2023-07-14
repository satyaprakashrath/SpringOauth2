package com.appsdevblog.ws.api.resourceserver.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class WebSecurity {
    /*@Bean
    SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        System.out.println("Request coming here");
        httpSecurity
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {
                }));

        return httpSecurity.build();
    }*/

    /*@Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .antMatchers(HttpMethod.GET,"/users/status").hasAuthority("SCOPE_profile")
                        .anyRequest().authenticated()
                ).oauth2ResourceServer((oauth2ResourceServer) ->
                        oauth2ResourceServer
                                .jwt(withDefaults())
                );

        return http.build();
    }*/

    /*@Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleCoverter());
        http
                .authorizeHttpRequests().antMatchers(HttpMethod.GET,"/users/status").hasRole("developer")
                .anyRequest().authenticated().and().oauth2ResourceServer().jwt().jwtAuthenticationConverter(converter);

        return http.build();
    }*/

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleCoverter());
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .antMatchers(HttpMethod.GET,"/users/status").hasAuthority("SCOPE_profile")
                        .anyRequest().authenticated()
                ).oauth2ResourceServer((oauth2ResourceServer) ->
                        oauth2ResourceServer
                                .jwt(jwt-> jwt.jwtAuthenticationConverter(converter)));

        return http.build();
    }
}
