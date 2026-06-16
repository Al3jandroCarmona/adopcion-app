package com.adopcion.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MascotaListDTO {
    private Integer idMascota;
    private Integer idUsuarioDonador;   // ← nuevo
    private String  nombre;
    private String  raza;
    private String  sexo;
    private String  estadoAdopcion;
    private String  tipoMascota;
    private String  urlFotoPrincipal;
}