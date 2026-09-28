package com.biblioteca.dao.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    public static Connection obtenerConexion() throws SQLException {

        String url = System.getenv("DB_URL");
        String usuario = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        return DriverManager.getConnection(url, usuario, password);

        /* 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, usuario, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }*/
    }
}
