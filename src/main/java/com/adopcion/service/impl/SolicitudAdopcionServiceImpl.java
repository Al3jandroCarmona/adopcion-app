package com.adopcion.service.impl;

import com.adopcion.exception.ResourceNotFoundException;
import com.adopcion.model.SolicitudAdopcion;
import com.adopcion.repository.*;
import com.adopcion.service.SolicitudAdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class SolicitudAdopcionServiceImpl implements SolicitudAdopcionService {

    @Autowired
    private SolicitudAdopcionRepository solicitudAdopcionRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<SolicitudAdopcion> findAll() {
        return solicitudAdopcionRepository.findAll();
    }

    @Override
    public List<SolicitudAdopcion> findByUsuario(Integer idUsuario) {
        return solicitudAdopcionRepository.findByUsuarioSolicitante_IdUsuario(idUsuario);
    }

    @Override
    public List<SolicitudAdopcion> findByMascota(Integer idMascota) {
        return solicitudAdopcionRepository.findByMascota_IdMascota(idMascota);
    }

    @Override
    public SolicitudAdopcion findById(Integer id) {
        return solicitudAdopcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SolicitudAdopcion", id));
    }

    @Override
    public SolicitudAdopcion create(Integer idMascota, Integer idUsuario, SolicitudAdopcion solicitud) {
        solicitud.setMascota(
                mascotaRepository.findById(idMascota)
                        .orElseThrow(() -> new ResourceNotFoundException("Mascota", idMascota))
        );
        solicitud.setUsuarioSolicitante(
                usuarioRepository.findById(idUsuario)
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario", idUsuario))
        );
        return solicitudAdopcionRepository.save(solicitud);
    }

    @Override
    public SolicitudAdopcion cambiarEstado(Integer id, SolicitudAdopcion.EstadoSolicitud estado) {
        SolicitudAdopcion solicitud = findById(id);
        solicitud.setEstadoSolicitud(estado);
        return solicitudAdopcionRepository.save(solicitud);
    }

    @Override
    public void delete(Integer id) {
        solicitudAdopcionRepository.deleteById(id);
    }
}
