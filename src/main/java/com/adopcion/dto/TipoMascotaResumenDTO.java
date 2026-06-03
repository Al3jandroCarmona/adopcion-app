package com.adopcion.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoMascotaResumenDTO {
    private Integer idTipoMascota;
    private String descripcion;
    private long disponibles;   // cuántas mascotas disponibles hay de este tipo
}
