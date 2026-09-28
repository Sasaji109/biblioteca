package com.biblioteca.service;

import java.util.List;
import com.biblioteca.model.Libro;

public interface LibroService {
    Libro obtenerLibroPorId(Long id);
    List<Libro> obtenerLibros();
    Long insertarLibro(Libro libro);
    void eliminarLibro(Long id);
    void actualizarLibro(Libro libro);
}