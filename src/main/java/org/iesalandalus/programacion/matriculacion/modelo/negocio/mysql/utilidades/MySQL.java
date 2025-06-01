package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    public static final String HOST = "TU_HOST_AWS_MYSQL"; // Reemplaza con el endpoint de tu BD en AWS
    public static final String ESQUEMA = "dbsistemamatriculacion";
    public static final String USUARIO = "admin";
    public static final String CONTRASENA = "sistemamatriculacion-2025";
    private static Connection conexion = null;

    public static void establecerConexion() {
        try {
            // Cargar el driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + HOST + "/" + ESQUEMA + "?useSSL=false&serverTimezone=UTC";
            conexion = DriverManager.getConnection(url, USUARIO, CONTRASENA);
            System.out.println("Conexión a la base de datos establecida correctamente.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver JDBC de MySQL. " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al establecer la conexión con la base de datos: " + e.getMessage());
        }
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión a la base de datos cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión con la base de datos: " + e.getMessage());
            }
        }
    }

    public static Connection getConexion() {
        return conexion;
    }
}
