package com.adopcion.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudRequestDTO {
    private Integer idMascota;
    private String  mensaje;
    private String  tipoVivienda;
    private Boolean tieneNinos;
    private Boolean tieneMascotas;
    private String  experienciaPrevia;
    private String  motivacion;
}
