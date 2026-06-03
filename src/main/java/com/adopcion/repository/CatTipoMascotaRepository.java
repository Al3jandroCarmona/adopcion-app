package com.adopcion.repository;

import com.adopcion.model.CatTipoMascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CatTipoMascotaRepository extends JpaRepository<CatTipoMascota, Integer> {
    List<CatTipoMascota> findByActivoTrue();
}
