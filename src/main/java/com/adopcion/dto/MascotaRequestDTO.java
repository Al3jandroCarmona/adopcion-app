package com.adopcion.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MascotaRequestDTO {
    private String nombre;
    private Integer idTipoMascota;
    private String raza;
    private String sexo;             // "Macho" o "Hembra"
}
