package com.adopcion.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilUpdateDTO {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String foto;
}