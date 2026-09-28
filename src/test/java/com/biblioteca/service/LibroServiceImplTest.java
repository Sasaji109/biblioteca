package com.biblioteca.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import com.biblioteca.dao.LibroDAO;
import com.biblioteca.model.Libro;

public class LibroServiceImplTest {

    @Test
    void noDebePermitirISBNDuplicado() {

        LibroDAO daoMock = mock(LibroDAO.class);
        Libro libroExistente = new Libro(
                1L,
                "Dune",
                "Frank Herbert",
                "9780441172719",
                1965
        );

        when(daoMock.obtenerLibros()).thenReturn(
                java.util.List.of(libroExistente)
        );

        LibroServiceImpl servicio = new LibroServiceImpl(daoMock);

        Libro nuevoLibro = new Libro(
                null,
                "Otro libro",
                "Otro autor",
                "9780441172719",
                2020
        );

        assertThrows(
            IllegalArgumentException.class,
            () -> servicio.insertarLibro(nuevoLibro)
        );
    }

    @Test
    void debeInsertarLibroSiElISBNNoExiste() {

        LibroDAO daoMock = mock(LibroDAO.class);
        when(daoMock.obtenerLibros()).thenReturn(
            java.util.List.of()
        );

        LibroServiceImpl servicio = new LibroServiceImpl(daoMock);

        Libro nuevoLibro = new Libro(
            null,
            "El nombre del viento",
            "Patrick Rothfuss",
            "9788401337208",
            2007
        );

        when(daoMock.insertarLibro(nuevoLibro)).thenReturn(123L);
        Long idGenerado = servicio.insertarLibro(nuevoLibro);
        assertEquals(123L, idGenerado);
        verify(daoMock).insertarLibro(nuevoLibro);
    }

    @Test
    void debeActualizarLibro() {

        LibroDAO daoMock = mock(LibroDAO.class);
        LibroServiceImpl servicio = new LibroServiceImpl(daoMock);

        Libro libro = new Libro(
            2L,
            "Dune actualizado",
            "Frank Herbert",
            "9780441172719",
            1965
        );

        servicio.actualizarLibro(libro);
        verify(daoMock).actualizarLibro(libro);
    }

    @Test
    void debeEliminarLibro() {
        LibroDAO daoMock = mock(LibroDAO.class);
        LibroServiceImpl servicio = new LibroServiceImpl(daoMock);
        servicio.eliminarLibro(2L);
        verify(daoMock).eliminarLibro(2L);
    }
}