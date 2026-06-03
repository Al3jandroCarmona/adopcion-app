package com.adopcion.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Adopcion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Adopcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAdopcion")
    private Integer idAdopcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSolicitud", nullable = false)
    private SolicitudAdopcion solicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMascota", nullable = false)
    private Mascota mascota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioAdoptante", nullable = false)
    private Usuario usuarioAdoptante;

    @Column(nullable = false)
    private LocalDateTime fechaAdopcion;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @PrePersist
    protected void onCreate() {
        if (fechaAdopcion == null) fechaAdopcion = LocalDateTime.now();
    }
}
