package com.biblioteca.dao;

import java.util.ArrayList;
import java.util.List;

import com.biblioteca.dao.config.ConexionDB;
import com.biblioteca.model.Libro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LibroDAOImpl implements LibroDAO {

    @Override
    public Libro obtenerLibroPorId(Long id) {

        String sql = """
            SELECT id, titulo, autor, isbn, anio_publicacion
            FROM libro
            WHERE id = ?
            """;

        try (Connection conexion = ConexionDB.obtenerConexion();
            PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setLong(1, id);

            try (ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {

                    Long idLibro = rs.getLong("id");
                    String titulo = rs.getString("titulo");
                    String autor = rs.getString("autor");
                    String isbn = rs.getString("isbn");
                    Integer anioPublicacion = rs.getInt("anio_publicacion");

                    return new Libro(
                        idLibro,
                        titulo,
                        autor,
                        isbn,
                        anioPublicacion
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Libro> obtenerLibros() {
        List<Libro> libros = new ArrayList<>();

        String sql = """
            SELECT id, titulo, autor, isbn, anio_publicacion
            FROM libro
            """;

        try (Connection conexion = ConexionDB.obtenerConexion();
            PreparedStatement statement = conexion.prepareStatement(sql);
            ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {

                Long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String isbn = rs.getString("isbn");
                Integer anioPublicacion = rs.getInt("anio_publicacion");

                Libro libro = new Libro(
                    id,
                    titulo,
                    autor,
                    isbn,
                    anioPublicacion
                );

                libros.add(libro);
            }   

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return libros;
    }

    @Override
    public Long insertarLibro(Libro libro) {

        String sql = """
            INSERT INTO libro (titulo, autor, isbn, anio_publicacion)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection conexion = ConexionDB.obtenerConexion();
            PreparedStatement statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getIsbn());
            statement.setInt(4, libro.getAnioPublicacion());
            statement.executeUpdate();

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void eliminarLibro(Long id) {
        
        String sql = """
            DELETE FROM libro
            WHERE id = ?
            """;

        try (Connection conexion = ConexionDB.obtenerConexion();
            PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarLibro(Libro libro) {

        String sql = """
            UPDATE libro
            SET titulo = ?, autor = ?, isbn = ?, anio_publicacion = ?
            WHERE id = ?
            """;

        try (Connection conexion = ConexionDB.obtenerConexion();
            PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getIsbn());
            statement.setInt(4, libro.getAnioPublicacion());
            statement.setLong(5, libro.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}