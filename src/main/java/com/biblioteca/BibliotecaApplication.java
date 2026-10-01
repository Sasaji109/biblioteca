package com.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.biblioteca.service.LibroService;

@SpringBootApplication
public class BibliotecaApplication {

    public static void main(String[] args) {
                
        var contexto = SpringApplication.run(
            BibliotecaApplication.class,
            args
        );

        LibroService servicio = contexto.getBean(LibroService.class);
    }
}