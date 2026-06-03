package com.adopcion.repository;

import com.adopcion.model.ImagenMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ImagenMascotaRepository extends JpaRepository<ImagenMascota, Integer> {
    List<ImagenMascota> findByMascota_IdMascota(Integer idMascota);
}
