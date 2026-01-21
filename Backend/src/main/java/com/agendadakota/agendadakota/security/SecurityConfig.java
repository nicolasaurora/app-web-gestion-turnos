package com.agendadakota.agendadakota.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // Registro y login público
                        .requestMatchers("/auth/**").permitAll()

                        // PROFESIONAL → puede ver turnos y disponibilidad
                        .requestMatchers("/turnos/**", "/agenda/**")
                        .hasAnyRole("PROFESIONAL", "ADMIN")

                        // PACIENTE → reservar y cancelar turnos
                        .requestMatchers(
                                "/turnos/reservar/**",
                                "/turnos/cancelar/**",
                                "/disponibilidad/**"
                        ).hasRole("PACIENTE")

                        // ADMIN → Acceso a todo
                        //.requestMatchers("/**").hasRole("ADMIN")

                        .anyRequest().hasRole("ADMIN")
                )
                .formLogin(login -> login.disable())
                .httpBasic(basic -> basic.disable())

                // IMPORTANTE: agregar filtro JWT ANTES del filtro de login
                //.addFilterBefore(jwtFilter, SecurityContextHolderFilter.class);
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
