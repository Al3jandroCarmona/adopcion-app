package com.adopcion.repository;

import com.adopcion.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {

    List<Mascota> findByActivoTrue();

    List<Mascota> findByUsuarioDonador_IdUsuario(Integer idUsuario);

    // ✅ Usar String en lugar del enum roto
    List<Mascota> findByEstadoAdopcion(String estadoAdopcion);

    List<Mascota> findByTipoMascota_IdTipoMascota(Integer idTipo);

    // ✅ Para la vista de lista por categoría
    List<Mascota> findByTipoMascota_IdTipoMascotaAndActivoTrue(Integer idTipo);

    // ✅ Conteo de disponibles por tipo (para la vista principal)
    @Query("SELECT COUNT(m) FROM Mascota m WHERE m.tipoMascota.idTipoMascota = :idTipo AND m.estadoAdopcion = 'Disponible' AND m.activo = true")
    long countDisponiblesByTipo(@Param("idTipo") Integer idTipo);

    List<Mascota> findByUsuarioDonador_IdUsuarioAndActivoTrue(Integer idUsuario);
}
