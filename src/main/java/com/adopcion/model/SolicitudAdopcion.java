package com.adopcion.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "SolicitudAdopcion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitudAdopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSolicitud")
    private Integer idSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMascota", nullable = false)
    private Mascota mascota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioSolicitante", nullable = false)
    private Usuario usuarioSolicitante;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoSolicitud estadoSolicitud = EstadoSolicitud.Pendiente;

    @Column(nullable = false)
    private LocalDateTime fechaSolicitud;

    @PrePersist
    protected void onCreate() {
        if (fechaSolicitud == null) fechaSolicitud = LocalDateTime.now();
    }

    public enum EstadoSolicitud { Pendiente, Aprobada, Rechazada }
}
