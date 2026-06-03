// src/main/java/com/adopcion/service/impl/AuthServiceImpl.java
package com.adopcion.service.impl;

import com.adopcion.dto.LoginRequestDTO;
import com.adopcion.dto.LoginResponseDTO;
import com.adopcion.dto.RegistroRequestDTO;
import com.adopcion.exception.ResourceNotFoundException;
import com.adopcion.model.Usuario;
import com.adopcion.repository.UsuarioRepository;
import com.adopcion.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Credenciales inválidas"));

        if (!usuario.getPassword().equals(request.getPassword())) {
            throw new ResourceNotFoundException("Credenciales inválidas");
        }
        if (!usuario.getActivo()) {
            throw new ResourceNotFoundException("Usuario inactivo");
        }

        return LoginResponseDTO.builder()
                .idUsuario(usuario.getIdUsuario())
                .nombre(usuario.getNombre())
                .apellidoPaterno(usuario.getApellidoPaterno())
                .email(usuario.getEmail())
                .build();
    }

    @Override
    public LoginResponseDTO registro(RegistroRequestDTO request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResourceNotFoundException("El email ya está registrado");
        }

        Usuario nuevo = Usuario.builder()
                .nombre(request.getNombre() != null ? request.getNombre() : "Usuario")
                .apellidoPaterno(request.getApellidoPaterno() != null ? request.getApellidoPaterno() : "")
                .apellidoMaterno(request.getApellidoMaterno() != null ? request.getApellidoMaterno() : "")
                .email(request.getEmail())
                .password(request.getPassword())
                .telefono(request.getTelefono())
                .activo(true)
                .build();

        Usuario guardado = usuarioRepository.save(nuevo);

        return LoginResponseDTO.builder()
                .idUsuario(guardado.getIdUsuario())
                .nombre(guardado.getNombre())
                .apellidoPaterno(guardado.getApellidoPaterno())
                .email(guardado.getEmail())
                .build();
    }
}
