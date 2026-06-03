package com.adopcion.service.impl;

import com.adopcion.exception.ResourceNotFoundException;
import com.adopcion.model.ImagenMascota;
import com.adopcion.repository.*;
import com.adopcion.service.ImagenMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ImagenMascotaServiceImpl implements ImagenMascotaService {

    @Autowired
    private ImagenMascotaRepository imagenMascotaRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Override
    public List<ImagenMascota> findByMascota(Integer idMascota) {
        return imagenMascotaRepository.findByMascota_IdMascota(idMascota);
    }

    @Override
    public ImagenMascota findById(Integer id) {
        return imagenMascotaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ImagenMascota", id));
    }

    @Override
    public ImagenMascota create(Integer idMascota, ImagenMascota imagen) {
        imagen.setMascota(
                mascotaRepository.findById(idMascota)
                        .orElseThrow(() -> new ResourceNotFoundException("Mascota", idMascota))
        );
        return imagenMascotaRepository.save(imagen);
    }

    @Override
    public ImagenMascota update(Integer id, ImagenMascota data) {
        ImagenMascota imagen = findById(id);
        imagen.setUrlImagen(data.getUrlImagen());
        imagen.setImagenPrincipal(data.getImagenPrincipal());
        return imagenMascotaRepository.save(imagen);
    }

    @Override
    public void delete(Integer id) {
        imagenMascotaRepository.deleteById(id);
    }
}
