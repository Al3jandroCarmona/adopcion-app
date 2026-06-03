package com.adopcion.controller;

import com.adopcion.model.SolicitudAdopcion;
import com.adopcion.service.SolicitudAdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudAdopcionController {

    @Autowired
    private SolicitudAdopcionService solicitudAdopcionService;

    @GetMapping
    public ResponseEntity<List<SolicitudAdopcion>> getAll() {
        return ResponseEntity.ok(solicitudAdopcionService.findAll());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<SolicitudAdopcion>> getByUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(solicitudAdopcionService.findByUsuario(idUsuario));
    }

    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<SolicitudAdopcion>> getByMascota(@PathVariable Integer idMascota) {
        return ResponseEntity.ok(solicitudAdopcionService.findByMascota(idMascota));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudAdopcion> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(solicitudAdopcionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SolicitudAdopcion> create(
            @RequestParam Integer idMascota,
            @RequestParam Integer idUsuario,
            @RequestBody SolicitudAdopcion solicitud) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(solicitudAdopcionService.create(idMascota, idUsuario, solicitud));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<SolicitudAdopcion> cambiarEstado(
            @PathVariable Integer id,
            @RequestParam SolicitudAdopcion.EstadoSolicitud estado) {
        return ResponseEntity.ok(solicitudAdopcionService.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        solicitudAdopcionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
