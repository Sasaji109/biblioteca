package com.biblioteca.webapp;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biblioteca.model.Libro;
import com.biblioteca.service.LibroService;

@RestController
public class LibroController {
    
    private final LibroService servicio;

    public LibroController(LibroService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/libros")
    public List<Libro> obtenerLibros() {
        return servicio.obtenerLibros();
    }

    @GetMapping("/libros/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id) {
        
        Libro libro = servicio.obtenerLibroPorId(id);

        if (libro == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(libro);
    }

    @PostMapping("/libros")
    public ResponseEntity<Long> insertarLibro(@RequestBody Libro libro) {
        Long id = servicio.insertarLibro(libro);

        var ubicacion = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();

        return ResponseEntity
            .created(ubicacion)
            .body(id);
    }

    @PutMapping("/libros/{id}")
    public ResponseEntity<Void> actualizarLibro(
        @PathVariable Long id,
        @RequestBody Libro libro) {

        Libro libroExistente = servicio.obtenerLibroPorId(id);

        if (libroExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // De momento usamos el mismo ID que viene en la URL
        Libro libroActualizado = new Libro(
            id,
            libro.getTitulo(),
            libro.getAutor(),
            libro.getIsbn(),
            libro.getAnioPublicacion()
        );

        servicio.actualizarLibro(libroActualizado);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {

        Libro libroExistente = servicio.obtenerLibroPorId(id);

        if (libroExistente == null) {
            return ResponseEntity.notFound().build();
        }

        servicio.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
}