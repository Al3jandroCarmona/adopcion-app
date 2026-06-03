package com.adopcion.service;

import com.adopcion.model.ImagenMascota;
import java.util.List;

public interface ImagenMascotaService {
    List<ImagenMascota> findByMascota(Integer idMascota);
    ImagenMascota findById(Integer id);
    ImagenMascota create(Integer idMascota, ImagenMascota imagen);
    ImagenMascota update(Integer id, ImagenMascota imagen);
    void delete(Integer id);
}
