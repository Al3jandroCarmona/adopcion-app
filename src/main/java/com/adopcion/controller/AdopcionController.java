package com.adopcion.controller;

import com.adopcion.model.Adopcion;
import com.adopcion.service.AdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/adopciones")
public class AdopcionController {

    @Autowired
    private AdopcionService adopcionService;

    @GetMapping
    public ResponseEntity<List<Adopcion>> getAll() {
        return ResponseEntity.ok(adopcionService.findAll());
    }

    @GetMapping("/adoptante/{idAdoptante}")
    public ResponseEntity<List<Adopcion>> getByAdoptante(@PathVariable Integer idAdoptante) {
        return ResponseEntity.ok(adopcionService.findByAdoptante(idAdoptante));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adopcion> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(adopcionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Adopcion> create(
            @RequestParam Integer idSolicitud,
            @RequestParam Integer idMascota,
            @RequestParam Integer idAdoptante,
            @RequestBody Adopcion adopcion) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adopcionService.create(idSolicitud, idMascota, idAdoptante, adopcion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adopcion> update(@PathVariable Integer id, @RequestBody Adopcion adopcion) {
        return ResponseEntity.ok(adopcionService.update(id, adopcion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        adopcionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
