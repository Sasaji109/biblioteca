package com.biblioteca;

import com.biblioteca.model.Libro;
import com.biblioteca.service.LibroService;
import com.biblioteca.service.LibroServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        //System.out.println("=== Biblioteca ===");
        //System.out.println("1. Listar libros");
        //System.out.println("2. Añadir libro");
        //System.out.println("3. Prestar libro");
        //System.out.println("4. Devolver libro");

        LibroService service = new LibroServiceImpl();
        List<Libro> libros = service.obtenerLibros();
        for (Libro libro : libros) {
            System.out.println(libro);
        }

        Libro libro = service.obtenerLibroPorId(2L);
        libro.setTitulo("Dune - Edición actualizada");
        service.actualizarLibro(libro);
        service.eliminarLibro(4L);
    }
}