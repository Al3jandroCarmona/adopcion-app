package com.adopcion.repository;

import com.adopcion.model.SolicitudAdopcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SolicitudAdopcionRepository extends JpaRepository<SolicitudAdopcion, Integer> {
    List<SolicitudAdopcion> findByUsuarioSolicitante_IdUsuario(Integer idUsuario);
    List<SolicitudAdopcion> findByMascota_IdMascota(Integer idMascota);
    List<SolicitudAdopcion> findByEstadoSolicitud(SolicitudAdopcion.EstadoSolicitud estado);
}
