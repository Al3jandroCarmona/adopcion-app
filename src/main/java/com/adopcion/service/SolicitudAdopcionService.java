package com.adopcion.service;

import com.adopcion.model.SolicitudAdopcion;
import java.util.List;

public interface SolicitudAdopcionService {
    List<SolicitudAdopcion> findAll();
    List<SolicitudAdopcion> findByUsuario(Integer idUsuario);
    List<SolicitudAdopcion> findByMascota(Integer idMascota);
    SolicitudAdopcion findById(Integer id);
    SolicitudAdopcion create(Integer idMascota, Integer idUsuario, SolicitudAdopcion solicitud);
    SolicitudAdopcion cambiarEstado(Integer id, SolicitudAdopcion.EstadoSolicitud estado);
    void delete(Integer id);
}
