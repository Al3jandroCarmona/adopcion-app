package com.adopcion.service;

import com.adopcion.dto.SolicitudRequestDTO;
import com.adopcion.dto.SolicitudResponseDTO;
import com.adopcion.model.SolicitudAdopcion;
import java.util.List;

public interface SolicitudAdopcionService {
    List<SolicitudResponseDTO> findAll();
    List<SolicitudResponseDTO> findByUsuario(Integer idUsuario);
    List<SolicitudResponseDTO> findByMascota(Integer idMascota);
    List<SolicitudResponseDTO> findByDonador(Integer idDonador);
    SolicitudResponseDTO       findById(Integer id);
    SolicitudResponseDTO       create(Integer idUsuario, SolicitudRequestDTO dto);
    SolicitudResponseDTO       cambiarEstado(Integer id, SolicitudAdopcion.EstadoSolicitud estado);
    void                       delete(Integer id);
}