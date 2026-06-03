package com.adopcion.service;

import com.adopcion.dto.TipoMascotaResumenDTO;
import com.adopcion.model.CatTipoMascota;
import java.util.List;

public interface CatTipoMascotaService {
    List<CatTipoMascota>        findAll();
    List<TipoMascotaResumenDTO> findAllConDisponibles();  // para la vista principal
    CatTipoMascota              findById(Integer id);
    CatTipoMascota              create(CatTipoMascota tipo);
    CatTipoMascota              update(Integer id, CatTipoMascota tipo);
    void                        delete(Integer id);
}
