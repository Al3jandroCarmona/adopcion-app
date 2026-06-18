package com.adopcion.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    // Carpeta donde se guardan — configurable en application.properties
    @Value("${upload.dir:uploads}")
    private String uploadDir;

    // URL base del servidor — configurable
    @Value("${server.url:http://192.168.1.100:8181}")
    private String serverUrl;

    @PostMapping
    public ResponseEntity<Map<String, String>> subirImagen(
            @RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Archivo vacío"));
        }

        // Crear carpeta si no existe
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Nombre único para evitar colisiones
        String extension = getExtension(file.getOriginalFilename());
        String nombreArchivo = UUID.randomUUID().toString() + "." + extension;
        Path destino = uploadPath.resolve(nombreArchivo);

        Files.copy(file.getInputStream(), destino, StandardCopyOption.REPLACE_EXISTING);

        String urlImagen = serverUrl + "/api/imagenes-files/" + nombreArchivo;

        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("nombre",  nombreArchivo);
        respuesta.put("ruta",    urlImagen);
        respuesta.put("tipo",    file.getContentType());
        respuesta.put("size",    String.valueOf(file.getSize()));

        return ResponseEntity.ok(respuesta);
    }

    private String getExtension(String filename) {
        if (filename == null || !filename.contains(".")) return "jpg";
        return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
    }
}
