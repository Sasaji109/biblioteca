package com.biblioteca.service;

import java.util.List;

import com.biblioteca.dao.LibroDAO;
import com.biblioteca.dao.LibroDAOImpl;
import com.biblioteca.model.Libro;

public class LibroServiceImpl implements LibroService {
    
    private LibroDAO dao;

    public LibroServiceImpl() {
        this.dao = new LibroDAOImpl();
    }

    public LibroServiceImpl(LibroDAO dao) {
        this.dao = dao;
    }

    @Override
    public Libro obtenerLibroPorId(Long id) {
        return dao.obtenerLibroPorId(id);
    }

    @Override
    public List<Libro> obtenerLibros() {
        return dao.obtenerLibros();
    }

    @Override
    public Long insertarLibro(Libro libro) {
        for (Libro l : dao.obtenerLibros()) {
            if (l.getIsbn().equals(libro.getIsbn())) {
                throw new IllegalArgumentException("El libro con ISBN " + libro.getIsbn() + " ya existe.");
            }
        }
        return dao.insertarLibro(libro);
    }

    @Override
    public void eliminarLibro(Long id) {
        dao.eliminarLibro(id);
    }

    @Override
    public void actualizarLibro(Libro libro) {
        dao.actualizarLibro(libro);
    }
}