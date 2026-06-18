package com.adopcion.service.impl;

import com.adopcion.dto.SolicitudRequestDTO;
import com.adopcion.dto.SolicitudResponseDTO;
import com.adopcion.exception.ResourceNotFoundException;
import com.adopcion.model.*;
import com.adopcion.repository.*;
import com.adopcion.service.SolicitudAdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SolicitudAdopcionServiceImpl implements SolicitudAdopcionService {

    @Autowired private SolicitudAdopcionRepository solicitudRepo;
    @Autowired private MascotaRepository           mascotaRepo;
    @Autowired private UsuarioRepository           usuarioRepo;

    @Override
    public List<SolicitudResponseDTO> findAll() {
        return solicitudRepo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SolicitudResponseDTO> findByUsuario(Integer idUsuario) {
        return solicitudRepo.findByUsuarioSolicitante_IdUsuario(idUsuario)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SolicitudResponseDTO> findByMascota(Integer idMascota) {
        return solicitudRepo.findByMascota_IdMascota(idMascota)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<SolicitudResponseDTO> findByDonador(Integer idDonador) {
        return mascotaRepo.findByUsuarioDonador_IdUsuarioAndActivoTrue(idDonador)
                .stream()
                .flatMap(m -> solicitudRepo.findByMascota_IdMascota(m.getIdMascota()).stream())
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SolicitudResponseDTO findById(Integer id) {
        return toDTO(solicitudRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SolicitudAdopcion", id)));
    }

    @Override
    public SolicitudResponseDTO create(Integer idUsuario, SolicitudRequestDTO dto) {
        Mascota mascota = mascotaRepo.findById(dto.getIdMascota())
                .orElseThrow(() -> new ResourceNotFoundException("Mascota", dto.getIdMascota()));
        Usuario usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", idUsuario));

        // ← NUEVO: impedir auto-solicitud
        if (mascota.getUsuarioDonador().getIdUsuario().equals(idUsuario)) {
            throw new IllegalArgumentException("No puedes solicitar la adopción de tu propia mascota");
        }

        SolicitudAdopcion s = SolicitudAdopcion.builder()
                .mascota(mascota)
                .usuarioSolicitante(usuario)
                .mensaje(dto.getMensaje())
                .tipoVivienda(dto.getTipoVivienda())
                .tieneNinos(dto.getTieneNinos() != null ? dto.getTieneNinos() : false)
                .tieneMascotas(dto.getTieneMascotas() != null ? dto.getTieneMascotas() : false)
                .experienciaPrevia(dto.getExperienciaPrevia())
                .motivacion(dto.getMotivacion())
                .build();

        return toDTO(solicitudRepo.save(s));
    }

    @Override
    public SolicitudResponseDTO cambiarEstado(Integer id, SolicitudAdopcion.EstadoSolicitud estado) {
        SolicitudAdopcion s = solicitudRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SolicitudAdopcion", id));
        s.setEstadoSolicitud(estado);
        if (estado == SolicitudAdopcion.EstadoSolicitud.Aprobada) {
            s.getMascota().setEstadoAdopcion(Mascota.ESTADO_EN_PROCESO);
            mascotaRepo.save(s.getMascota());
        }
        return toDTO(solicitudRepo.save(s));
    }

    @Override
    public void delete(Integer id) {
        solicitudRepo.deleteById(id);
    }

    private SolicitudResponseDTO toDTO(SolicitudAdopcion s) {
        String urlFoto = null;
        try {
            if (s.getMascota().getImagenes() != null && !s.getMascota().getImagenes().isEmpty()) {
                urlFoto = s.getMascota().getImagenes().stream()
                        .filter(i -> Boolean.TRUE.equals(i.getImagenPrincipal()))
                        .findFirst()
                        .map(i -> i.getUrlImagen())
                        .orElse(s.getMascota().getImagenes().get(0).getUrlImagen());
            }
        } catch (Exception e) {
            urlFoto = null;
        }
        return SolicitudResponseDTO.builder()
                .idSolicitud(s.getIdSolicitud())
                .idMascota(s.getMascota().getIdMascota())
                .nombreMascota(s.getMascota().getNombre())
                .urlFotoMascota(urlFoto)
                .idUsuarioSolicitante(s.getUsuarioSolicitante().getIdUsuario())
                .nombreSolicitante(s.getUsuarioSolicitante().getNombre()
                        + " " + s.getUsuarioSolicitante().getApellidoPaterno())
                .emailSolicitante(s.getUsuarioSolicitante().getEmail())
                .mensaje(s.getMensaje())
                .tipoVivienda(s.getTipoVivienda())
                .tieneNinos(s.getTieneNinos())
                .tieneMascotas(s.getTieneMascotas())
                .experienciaPrevia(s.getExperienciaPrevia())
                .motivacion(s.getMotivacion())
                .estadoSolicitud(s.getEstadoSolicitud().name())
                .fechaSolicitud(s.getFechaSolicitud())
                .build();
    }
}