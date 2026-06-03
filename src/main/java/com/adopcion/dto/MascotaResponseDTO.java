package com.adopcion.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MascotaResponseDTO {
    private Integer idMascota;
    private Integer idUsuarioDonador;
    private String  nombreDonador;
    private Integer idTipoMascota;
    private String  tipoMascotaDescripcion;
    private String  nombre;
    private String  raza;
    private String  sexo;
    private String  edadAproximada;
    private String  descripcion;
    private String  estadoAdopcion;
    private Boolean activo;
    private LocalDateTime fechaPublicacion;
}
