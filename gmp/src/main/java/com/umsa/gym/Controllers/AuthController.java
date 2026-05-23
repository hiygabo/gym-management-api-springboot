package com.umsa.gym.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umsa.gym.DTOs.AuthResponse;
import com.umsa.gym.DTOs.LoginRequest;
import com.umsa.gym.Security.CustomUserDetailsService;
import com.umsa.gym.Security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            // 1. Spring Security intenta hacer el login con los datos proporcionados
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            // 2. Si la contraseña está mal, lanzamos un error 401
            return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
        }

        // 3. Si pasó la línea anterior, el login fue exitoso. Buscamos sus datos.
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        // 4. Fabricamos el Token JWT firmado
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        // 5. Se lo enviamos a React
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}