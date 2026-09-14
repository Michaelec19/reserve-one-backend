package com.reserveone.lanhua.modules.auth.controller;

import com.reserveone.lanhua.modules.auth.dto.LoginDTO;
import com.reserveone.lanhua.modules.auth.service.AuthService;
import com.reserveone.lanhua.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.reserveone.lanhua.modules.user.repository.UserRepository;
import com.reserveone.lanhua.modules.user.entity.User;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDto) {

        boolean isAuthenticated = authService.login(loginDto);

        if (isAuthenticated) {
            String nombreRol = "CLIENTE";
            Long userId = null;

            try {
                User usuario = userRepository.findByEmailUser(loginDto.getEmail()).get();
                nombreRol = usuario.getRol().getNameRol();
                userId = usuario.getIdUser();
            } catch (Exception e) {
                System.out.println("No se pudo obtener el rol o ID, usando valores por defecto.");
            }

            String token = jwtUtils.generateJwtToken(loginDto.getEmail(), nombreRol);

            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("id", userId);
            response.put("email", loginDto.getEmail());
            response.put("role", nombreRol);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            Map<String, Object> response = new HashMap<>();
            response.put("email", email);
            response.put("authorities", authentication.getAuthorities());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
