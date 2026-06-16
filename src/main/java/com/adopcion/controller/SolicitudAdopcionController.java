package com.adopcion.controller;

import com.adopcion.dto.SolicitudRequestDTO;
import com.adopcion.dto.SolicitudResponseDTO;
import com.adopcion.model.SolicitudAdopcion;
import com.adopcion.service.SolicitudAdopcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudAdopcionController {

    @Autowired private SolicitudAdopcionService solicitudService;

    @GetMapping
    public ResponseEntity<List<SolicitudResponseDTO>> getAll() {
        return ResponseEntity.ok(solicitudService.findAll());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<SolicitudResponseDTO>> getByUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(solicitudService.findByUsuario(idUsuario));
    }

    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<SolicitudResponseDTO>> getByMascota(@PathVariable Integer idMascota) {
        return ResponseEntity.ok(solicitudService.findByMascota(idMascota));
    }

    @GetMapping("/donador/{idDonador}")
    public ResponseEntity<List<SolicitudResponseDTO>> getByDonador(@PathVariable Integer idDonador) {
        return ResponseEntity.ok(solicitudService.findByDonador(idDonador));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(solicitudService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SolicitudResponseDTO> create(
            @RequestParam Integer idUsuario,
            @RequestBody SolicitudRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(solicitudService.create(idUsuario, dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<SolicitudResponseDTO> cambiarEstado(
            @PathVariable Integer id,
            @RequestParam SolicitudAdopcion.EstadoSolicitud estado) {
        return ResponseEntity.ok(solicitudService.cambiarEstado(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        solicitudService.delete(id);
        return ResponseEntity.noContent().build();
    }
}