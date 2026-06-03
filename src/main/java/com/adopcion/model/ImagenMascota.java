package com.adopcion.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ImagenMascota")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagenMascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idImagen")
    private Integer idImagen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMascota", nullable = false)
    private Mascota mascota;

    @Column(nullable = false, length = 255)
    private String urlImagen;

    @Column(nullable = false)
    private Boolean imagenPrincipal = false;

    @Column(nullable = false)
    private LocalDateTime fechaSubida;

    @PrePersist
    protected void onCreate() {
        if (fechaSubida == null) fechaSubida = LocalDateTime.now();
    }
}
