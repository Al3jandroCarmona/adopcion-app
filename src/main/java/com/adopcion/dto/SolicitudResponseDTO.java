package com.adopcion.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudResponseDTO {
    private Integer idSolicitud;
    private Integer idMascota;
    private String  nombreMascota;
    private String  urlFotoMascota;
    private Integer idUsuarioSolicitante;
    private String  nombreSolicitante;
    private String  emailSolicitante;
    private String  mensaje;
    private String  tipoVivienda;
    private Boolean tieneNinos;
    private Boolean tieneMascotas;
    private String  experienciaPrevia;
    private String  motivacion;
    private String  estadoSolicitud;
    private LocalDateTime fechaSolicitud;
}