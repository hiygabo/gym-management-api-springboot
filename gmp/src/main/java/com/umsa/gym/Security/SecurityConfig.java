package com.umsa.gym.Security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Configuramos CORS para que React (puerto 5173) pueda comunicarse con Java sin bloqueos
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            // 2. Desactivamos CSRF porque usaremos Tokens (esto es estándar en APIs REST)
            .csrf(csrf -> csrf.disable())
            // 3. ¡LAS REGLAS DE LA PUERTA!
            .authorizeHttpRequests(auth -> auth
                // Permisos públicos (permitAll): Cualquiera puede entrar sin token
                .requestMatchers("/api/auth/**").permitAll() // Aquí pondremos el endpoint para hacer Login
                .requestMatchers("/api/asistencia/marcar").permitAll() // El kiosko de recepción
                .requestMatchers("/api/registros/**").permitAll() // ¡Ajusta esta ruta a la de tu formulario de inscripción de clientes!
                .requestMatchers("/api/planes/**").permitAll() // <-- ¡AÑADE ESTA LÍNEA!
                // Todo el resto de la aplicación (Clientes, Entrenadores, etc.) está BAJO LLAVE
                .anyRequest().authenticated()
            )
            // 4. Le decimos que no guarde sesiones en memoria, todo será a través del Token
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // 5. Ponemos a nuestro Guardia de Seguridad (Filtro) antes de que la petición llegue al controlador
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Configuración exacta para permitirle el paso a tu frontend de React
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173")); 
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // El encriptador de contraseñas. Nunca guardaremos "1234" literal en Oracle.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // El motor que verifica si el usuario y contraseña son correctos
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}