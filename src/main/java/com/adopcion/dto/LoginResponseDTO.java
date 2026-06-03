package com.adopcion.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
    private Integer idUsuario;
    private String nombre;
    private String apellidoPaterno;
    private String email;
}
