package com.adopcion.controller;

import com.adopcion.dto.TipoMascotaResumenDTO;
import com.adopcion.model.CatTipoMascota;
import com.adopcion.service.CatTipoMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tipos-mascota")
public class CatTipoMascotaController {

    @Autowired
    private CatTipoMascotaService catTipoMascotaService;

    /** Vista principal Android: 4 cards con foto y disponibles */
    @GetMapping("/resumen")
    public ResponseEntity<List<TipoMascotaResumenDTO>> getResumen() {
        return ResponseEntity.ok(catTipoMascotaService.findAllConDisponibles());
    }

    @GetMapping
    public ResponseEntity<List<CatTipoMascota>> getAll() {
        return ResponseEntity.ok(catTipoMascotaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatTipoMascota> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(catTipoMascotaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CatTipoMascota> create(@RequestBody CatTipoMascota tipo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(catTipoMascotaService.create(tipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatTipoMascota> update(@PathVariable Integer id, @RequestBody CatTipoMascota tipo) {
        return ResponseEntity.ok(catTipoMascotaService.update(id, tipo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        catTipoMascotaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
