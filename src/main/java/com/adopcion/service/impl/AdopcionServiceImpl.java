package com.adopcion.service.impl;

import com.adopcion.exception.ResourceNotFoundException;
import com.adopcion.model.*;
import com.adopcion.repository.*;
import com.adopcion.service.AdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class AdopcionServiceImpl implements AdopcionService {

    @Autowired
    private AdopcionRepository adopcionRepository;

    @Autowired
    private SolicitudAdopcionRepository solicitudAdopcionRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Adopcion> findAll() {
        return adopcionRepository.findAll();
    }

    @Override
    public List<Adopcion> findByAdoptante(Integer idAdoptante) {
        return adopcionRepository.findByUsuarioAdoptante_IdUsuario(idAdoptante);
    }

    @Override
    public Adopcion findById(Integer id) {
        return adopcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adopcion", id));
    }

    @Override
    public Adopcion create(Integer idSolicitud, Integer idMascota, Integer idAdoptante, Adopcion adopcion) {
        SolicitudAdopcion solicitud = solicitudAdopcionRepository.findById(idSolicitud)
                .orElseThrow(() -> new ResourceNotFoundException("SolicitudAdopcion", idSolicitud));

        Mascota mascota = mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota", idMascota));

        adopcion.setSolicitud(solicitud);
        adopcion.setMascota(mascota);
        adopcion.setUsuarioAdoptante(
                usuarioRepository.findById(idAdoptante)
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario", idAdoptante))
        );

        mascota.setEstadoAdopcion(Mascota.ESTADO_ADOPTADO);   // era: Mascota.EstadoAdopcion.Adoptado
        mascotaRepository.save(mascota);

        // Marcar solicitud como Aprobada
        solicitud.setEstadoSolicitud(SolicitudAdopcion.EstadoSolicitud.Aprobada);
        solicitudAdopcionRepository.save(solicitud);

        return adopcionRepository.save(adopcion);
    }

    @Override
    public Adopcion update(Integer id, Adopcion data) {
        Adopcion adopcion = findById(id);
        adopcion.setObservaciones(data.getObservaciones());
        return adopcionRepository.save(adopcion);
    }

    @Override
    public void delete(Integer id) {
        adopcionRepository.deleteById(id);
    }
}
