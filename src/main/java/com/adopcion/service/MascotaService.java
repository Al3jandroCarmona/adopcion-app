package com.adopcion.service;

import com.adopcion.dto.MascotaListDTO;
import com.adopcion.dto.MascotaRequestDTO;
import com.adopcion.model.Mascota;
import java.util.List;

public interface MascotaService {
    List<Mascota>      findAll();
    List<Mascota>      findDisponibles();
    List<MascotaListDTO> findByTipo(Integer idTipo);
    Mascota            findById(Integer id);
    Mascota            create(Integer idDonador, Integer idTipo, Mascota mascota);
    Mascota            createFromDTO(Integer idDonador, MascotaRequestDTO dto);
    Mascota            update(Integer id, Integer idTipo, Mascota data);
    void               delete(Integer id);
}
