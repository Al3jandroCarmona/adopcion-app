package com.adopcion.service;

import com.adopcion.dto.LoginRequestDTO;
import com.adopcion.dto.LoginResponseDTO;
import com.adopcion.dto.RegistroRequestDTO;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO request);
    LoginResponseDTO registro(RegistroRequestDTO request);
}
