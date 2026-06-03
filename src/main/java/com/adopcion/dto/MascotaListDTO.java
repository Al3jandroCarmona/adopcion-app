package com.adopcion.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MascotaListDTO {
    private Integer idMascota;
    private String nombre;
    private String raza;
    private String sexo;
    private String estadoAdopcion;
    private String tipoMascota;       // descripción del tipo, para la imagen en Android
    private String urlFotoPrincipal;
}
