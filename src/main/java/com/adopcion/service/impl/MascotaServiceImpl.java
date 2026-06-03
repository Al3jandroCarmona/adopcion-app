// src/main/java/com/adopcion/service/impl/MascotaServiceImpl.java
package com.adopcion.service.impl;

import com.adopcion.dto.MascotaListDTO;
import com.adopcion.dto.MascotaRequestDTO;
import com.adopcion.exception.ResourceNotFoundException;
import com.adopcion.model.Mascota;
import com.adopcion.repository.*;
import com.adopcion.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MascotaServiceImpl implements MascotaService {

    @Autowired private MascotaRepository      mascotaRepository;
    @Autowired private UsuarioRepository      usuarioRepository;
    @Autowired private CatTipoMascotaRepository catTipoMascotaRepository;

    @Override
    public List<Mascota> findAll() {
        return mascotaRepository.findByActivoTrue();
    }

    @Override
    public List<Mascota> findDisponibles() {
        return mascotaRepository.findByEstadoAdopcion(Mascota.ESTADO_DISPONIBLE);
    }

    @Override
    public List<MascotaListDTO> findByTipo(Integer idTipo) {
        return mascotaRepository.findByTipoMascota_IdTipoMascotaAndActivoTrue(idTipo)
                .stream()
                .map(this::toListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Mascota findById(Integer id) {
        return mascotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota", id));
    }

    @Override
    public Mascota create(Integer idDonador, Integer idTipo, Mascota mascota) {
        mascota.setUsuarioDonador(
                usuarioRepository.findById(idDonador)
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario", idDonador))
        );
        mascota.setTipoMascota(
                catTipoMascotaRepository.findById(idTipo)
                        .orElseThrow(() -> new ResourceNotFoundException("CatTipoMascota", idTipo))
        );
        return mascotaRepository.save(mascota);
    }

    @Override
    public Mascota createFromDTO(Integer idDonador, MascotaRequestDTO dto) {
        Mascota mascota = Mascota.builder()
                .nombre(dto.getNombre())
                .raza(dto.getRaza())
                .sexo(Mascota.Sexo.valueOf(dto.getSexo()))
                .estadoAdopcion(Mascota.ESTADO_DISPONIBLE)
                .activo(true)
                .build();
        return create(idDonador, dto.getIdTipoMascota(), mascota);
    }

    @Override
    public Mascota update(Integer id, Integer idTipo, Mascota data) {
        Mascota mascota = findById(id);
        mascota.setNombre(data.getNombre());
        mascota.setRaza(data.getRaza());
        mascota.setSexo(data.getSexo());
        mascota.setEdadAproximada(data.getEdadAproximada());
        mascota.setDescripcion(data.getDescripcion());
        mascota.setEstadoAdopcion(data.getEstadoAdopcion());
        if (idTipo != null) {
            mascota.setTipoMascota(
                    catTipoMascotaRepository.findById(idTipo)
                            .orElseThrow(() -> new ResourceNotFoundException("CatTipoMascota", idTipo))
            );
        }
        return mascotaRepository.save(mascota);
    }

    @Override
    public void delete(Integer id) {
        Mascota mascota = findById(id);
        mascota.setActivo(false);
        mascotaRepository.save(mascota);
    }

    private MascotaListDTO toListDTO(Mascota m) {
        // Buscar imagen principal
        String urlFoto = null;
        if (m.getImagenes() != null && !m.getImagenes().isEmpty()) {
            urlFoto = m.getImagenes().stream()
                    .filter(img -> Boolean.TRUE.equals(img.getImagenPrincipal()))
                    .findFirst()
                    .map(img -> img.getUrlImagen())
                    .orElse(m.getImagenes().get(0).getUrlImagen());
        }

        return MascotaListDTO.builder()
                .idMascota(m.getIdMascota())
                .nombre(m.getNombre())
                .raza(m.getRaza())
                .sexo(m.getSexo() != null ? m.getSexo().name() : null)
                .estadoAdopcion(m.getEstadoAdopcion())
                .tipoMascota(m.getTipoMascota().getDescripcion())
                .urlFotoPrincipal(urlFoto)   // ← nuevo
                .build();
    }
}
