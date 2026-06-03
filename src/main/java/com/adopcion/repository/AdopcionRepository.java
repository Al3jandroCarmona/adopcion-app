package com.adopcion.repository;

import com.adopcion.model.Adopcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdopcionRepository extends JpaRepository<Adopcion, Integer> {
    List<Adopcion> findByUsuarioAdoptante_IdUsuario(Integer idUsuario);
    List<Adopcion> findByMascota_IdMascota(Integer idMascota);
}
