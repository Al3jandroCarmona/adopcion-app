package com.adopcion.controller;

import com.adopcion.dto.MascotaListDTO;
import com.adopcion.dto.MascotaRequestDTO;
import com.adopcion.dto.MascotaResponseDTO;
import com.adopcion.mapper.MascotaMapper;
import com.adopcion.model.Mascota;
import com.adopcion.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping
    public ResponseEntity<List<MascotaResponseDTO>> getAll() {
        return ResponseEntity.ok(
                mascotaService.findAll().stream().map(MascotaMapper::toDTO).toList()
        );
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<MascotaResponseDTO>> getDisponibles() {
        return ResponseEntity.ok(
                mascotaService.findDisponibles().stream().map(MascotaMapper::toDTO).toList()
        );
    }

    /** Vista lista de categoría en Android */
    @GetMapping("/tipo/{idTipo}")
    public ResponseEntity<List<MascotaListDTO>> getByTipo(@PathVariable Integer idTipo) {
        return ResponseEntity.ok(mascotaService.findByTipo(idTipo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(MascotaMapper.toDTO(mascotaService.findById(id)));
    }

    /** Vista agregar mascota en Android */
    @PostMapping("/agregar")
    public ResponseEntity<MascotaResponseDTO> createFromDTO(
            @RequestParam Integer idDonador,
            @RequestBody MascotaRequestDTO dto) {
        Mascota nueva = mascotaService.createFromDTO(idDonador, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(MascotaMapper.toDTO(nueva));
    }

    @PostMapping
    public ResponseEntity<MascotaResponseDTO> create(
            @RequestParam Integer idDonador,
            @RequestParam Integer idTipo,
            @RequestBody Mascota mascota) {
        Mascota nueva = mascotaService.create(idDonador, idTipo, mascota);
        return ResponseEntity.status(HttpStatus.CREATED).body(MascotaMapper.toDTO(nueva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaResponseDTO> update(
            @PathVariable Integer id,
            @RequestParam(required = false) Integer idTipo,
            @RequestBody Mascota mascota) {
        return ResponseEntity.ok(MascotaMapper.toDTO(mascotaService.update(id, idTipo, mascota)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        mascotaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/donador/{idDonador}")
    public ResponseEntity<List<MascotaListDTO>> getByDonador(@PathVariable Integer idDonador) {
        return ResponseEntity.ok(mascotaService.findByDonador(idDonador));
    }
}
