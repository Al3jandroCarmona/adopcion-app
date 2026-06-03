package com.adopcion.mapper;

import com.adopcion.dto.MascotaResponseDTO;
import com.adopcion.model.Mascota;

public class MascotaMapper {

    public static MascotaResponseDTO toDTO(Mascota m) {
        if (m == null) return null;
        return MascotaResponseDTO.builder()
                .idMascota(m.getIdMascota())
                .idUsuarioDonador(m.getUsuarioDonador().getIdUsuario())
                .nombreDonador(m.getUsuarioDonador().getNombre() + " " + m.getUsuarioDonador().getApellidoPaterno())
                .idTipoMascota(m.getTipoMascota().getIdTipoMascota())
                .tipoMascotaDescripcion(m.getTipoMascota().getDescripcion())
                .nombre(m.getNombre())
                .raza(m.getRaza())
                .sexo(m.getSexo() != null ? m.getSexo().name() : null)
                .edadAproximada(m.getEdadAproximada())
                .descripcion(m.getDescripcion())
                .estadoAdopcion(m.getEstadoAdopcion())   // ya es String
                .activo(m.getActivo())
                .fechaPublicacion(m.getFechaPublicacion())
                .build();
    }
}
