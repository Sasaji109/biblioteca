package com.biblioteca.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.biblioteca.model.Libro;
import org.junit.jupiter.api.AfterEach;

public class LibroDAOImplIntegrationTest {

    @Test
    void debeObtenerLibroPorId() {

        LibroDAO dao = new LibroDAOImpl();
        Libro libro = dao.obtenerLibroPorId(1L);

        assertNotNull(libro);
        assertEquals(1L, libro.getId());
        assertEquals("El Hobbit", libro.getTitulo());
        assertEquals("J. R. R. Tolkien", libro.getAutor());
    }

    @AfterEach
    void limpiarDatosDePrueba() {
        LibroDAO dao = new LibroDAOImpl();
        dao.eliminarLibro(999L);
    }

    @Test
    void debeActualizarLibro() {

        LibroDAO dao = new LibroDAOImpl();
        Libro original = dao.obtenerLibroPorId(1L);
        assertNotNull(original);

        String tituloOriginal = original.getTitulo();
        original.setTitulo("Título temporal de prueba");
        dao.actualizarLibro(original);

        Libro actualizado = dao.obtenerLibroPorId(1L);
        assertNotNull(actualizado);
        assertEquals("Título temporal de prueba", actualizado.getTitulo());

        // Restauramos el dato original
        original.setTitulo(tituloOriginal);
        dao.actualizarLibro(original);
    }

    @Test
    void debeEliminarLibro() {

        LibroDAO dao = new LibroDAOImpl();
        Libro libro = new Libro(
            null,
            "Libro para eliminar",
            "Autor de prueba",
            "ISBN-DELETE-001",
            2026
        );

        Long idGenerado = dao.insertarLibro(libro);
        assertNotNull(idGenerado);

        Libro creado = dao.obtenerLibroPorId(idGenerado);
        assertNotNull(creado);

        dao.eliminarLibro(idGenerado);
        Libro eliminado = dao.obtenerLibroPorId(idGenerado);
        assertNull(eliminado);
    }
}