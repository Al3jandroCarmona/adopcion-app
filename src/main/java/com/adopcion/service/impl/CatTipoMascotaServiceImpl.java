// src/main/java/com/adopcion/service/impl/CatTipoMascotaServiceImpl.java
package com.adopcion.service.impl;

import com.adopcion.dto.TipoMascotaResumenDTO;
import com.adopcion.exception.ResourceNotFoundException;
import com.adopcion.model.CatTipoMascota;
import com.adopcion.repository.CatTipoMascotaRepository;
import com.adopcion.repository.MascotaRepository;
import com.adopcion.service.CatTipoMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CatTipoMascotaServiceImpl implements CatTipoMascotaService {

    @Autowired private CatTipoMascotaRepository catTipoMascotaRepository;
    @Autowired private MascotaRepository         mascotaRepository;

    @Override
    public List<CatTipoMascota> findAll() {
        return catTipoMascotaRepository.findByActivoTrue();
    }

    @Override
    public List<TipoMascotaResumenDTO> findAllConDisponibles() {
        return catTipoMascotaRepository.findByActivoTrue()
                .stream()
                .map(tipo -> TipoMascotaResumenDTO.builder()
                        .idTipoMascota(tipo.getIdTipoMascota())
                        .descripcion(tipo.getDescripcion())
                        .disponibles(mascotaRepository.countDisponiblesByTipo(tipo.getIdTipoMascota()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public CatTipoMascota findById(Integer id) {
        return catTipoMascotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CatTipoMascota", id));
    }

    @Override
    public CatTipoMascota create(CatTipoMascota tipo) {
        return catTipoMascotaRepository.save(tipo);
    }

    @Override
    public CatTipoMascota update(Integer id, CatTipoMascota data) {
        CatTipoMascota tipo = findById(id);
        tipo.setDescripcion(data.getDescripcion());
        tipo.setActivo(data.getActivo());
        return catTipoMascotaRepository.save(tipo);
    }

    @Override
    public void delete(Integer id) {
        CatTipoMascota tipo = findById(id);
        tipo.setActivo(false);
        catTipoMascotaRepository.save(tipo);
    }
}
