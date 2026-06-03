package com.adopcion.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CatTipoMascota")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CatTipoMascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoMascota")
    private Integer idTipoMascota;

    @Column(nullable = false, length = 50)
    private String descripcion;

    @Column(nullable = false)
    private Boolean activo = true;
}
