package com.adopcion.service;

import com.adopcion.model.Adopcion;
import java.util.List;

public interface AdopcionService {
    List<Adopcion> findAll();
    List<Adopcion> findByAdoptante(Integer idAdoptante);
    Adopcion findById(Integer id);
    Adopcion create(Integer idSolicitud, Integer idMascota, Integer idAdoptante, Adopcion adopcion);
    Adopcion update(Integer id, Adopcion adopcion);
    void delete(Integer id);
}
