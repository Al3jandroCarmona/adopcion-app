// src/main/java/com/adopcion/controller/AuthController.java
package com.adopcion.controller;

import com.adopcion.dto.LoginRequestDTO;
import com.adopcion.dto.LoginResponseDTO;
import com.adopcion.dto.RegistroRequestDTO;
import com.adopcion.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /** POST /api/auth/login  — { "email": "...", "password": "..." } */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    /** POST /api/auth/registro  — { "email": "...", "password": "...", ... } */
    @PostMapping("/registro")
    public ResponseEntity<LoginResponseDTO> registro(@RequestBody RegistroRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registro(request));
    }
}
