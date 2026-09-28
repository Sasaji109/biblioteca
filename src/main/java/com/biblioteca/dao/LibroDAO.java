package com.biblioteca.dao;

import java.util.List;
import com.biblioteca.model.Libro;

public interface LibroDAO {
    Libro obtenerLibroPorId(Long id);
    List<Libro> obtenerLibros();
    Long insertarLibro(Libro libro);
    void eliminarLibro(Long id);
    void actualizarLibro(Libro libro);
}