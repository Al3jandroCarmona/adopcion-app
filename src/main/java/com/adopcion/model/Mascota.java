package com.adopcion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Mascota")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMascota")
    private Integer idMascota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioDonador", nullable = false)
    private Usuario usuarioDonador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipoMascota", nullable = false)
    private CatTipoMascota tipoMascota;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 100)
    private String raza;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('Macho','Hembra')")
    private Sexo sexo;

    @Column(length = 50)
    private String edadAproximada;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, columnDefinition = "ENUM('Disponible','En proceso','Adoptado')")
    private String estadoAdopcion = "Disponible";

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(nullable = false)
    private LocalDateTime fechaPublicacion;

    // ← AGREGAR ESTO
    @JsonIgnore
    @OneToMany(mappedBy = "mascota", fetch = FetchType.LAZY)
    private List<ImagenMascota> imagenes;

    @PrePersist
    protected void onCreate() {
        if (fechaPublicacion == null) fechaPublicacion = LocalDateTime.now();
        if (estadoAdopcion == null) estadoAdopcion = "Disponible";
    }

    public enum Sexo { Macho, Hembra }

    public static final String ESTADO_DISPONIBLE = "Disponible";
    public static final String ESTADO_EN_PROCESO = "En proceso";
    public static final String ESTADO_ADOPTADO   = "Adoptado";
}
