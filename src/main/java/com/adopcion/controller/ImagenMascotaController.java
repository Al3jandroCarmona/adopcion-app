package com.adopcion.controller;

import com.adopcion.model.ImagenMascota;
import com.adopcion.service.ImagenMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenMascotaController {

    @Autowired
    private ImagenMascotaService imagenMascotaService;

    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<ImagenMascota>> getByMascota(@PathVariable Integer idMascota) {
        return ResponseEntity.ok(imagenMascotaService.findByMascota(idMascota));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenMascota> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(imagenMascotaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ImagenMascota> create(
            @RequestParam Integer idMascota,
            @RequestBody ImagenMascota imagen) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(imagenMascotaService.create(idMascota, imagen));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagenMascota> update(
            @PathVariable Integer id,
            @RequestBody ImagenMascota imagen) {
        return ResponseEntity.ok(imagenMascotaService.update(id, imagen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        imagenMascotaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
