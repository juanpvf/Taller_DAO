package com.iudigital.taller_dao.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/DB_DAO";
    private static final String USUARIO = "root"; // Reemplaza con tu usuario de MySQL
    private static final String CONTRASENA = ""; // Reemplaza con tu contraseña de MySQL
    private static Connection conexion;

    public static Connection obtenerConexion() {
        if (conexion == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                System.out.println("Conexión a la base de datos exitosa.");
            } catch (ClassNotFoundException e) {
                System.err.println("Error: No se encontró el driver de MySQL.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Error al conectar a la base de datos.");
                e.printStackTrace();
            }
        }
        return conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión a la base de datos cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión a la base de datos.");
                e.printStackTrace();
            }
        }
    }
}
