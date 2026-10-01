package com.biblioteca.webapp;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntity.ok(id);
    }
}