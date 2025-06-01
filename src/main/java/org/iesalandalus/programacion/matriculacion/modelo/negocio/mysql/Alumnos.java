package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;

import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Alumnos implements IAlumnos {
    private static Alumnos instancia;
    private Connection conexion;


    private Alumnos() {
        // Constructor privado para el patrón Singleton
        comenzar();
    }

    public static Alumnos getInstancia() {
        if (instancia == null) {
            instancia = new Alumnos();
        }
        return instancia;
    }

    @Override
    public void comenzar() {
        MySQL.establecerConexion();
        this.conexion = MySQL.getConexion();
    }

    @Override
    public void terminar() {
        MySQL.cerrarConexion();
    }

    @Override // Esta anotación verifica que realmente implementas un método de la interfaz
    public List<Alumno> get() {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT id, dni, nombre, apellidos FROM alumno ORDER BY dni";
        try (Statement stmt = MySQL.getConexion().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                alumnos.add(new Alumno(rs.getInt("id"), rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos")));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener alumnos: " + e.getMessage());
            // Considera lanzar una excepción de negocio o envolver la SQLException
        }
        return alumnos;
    }

    @Override
    public int getTamano() {
        String sql = "SELECT COUNT(*) FROM alumno";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tamaño de alumnos: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public void insertar(Alumno alumno) {
        String sql = "INSERT INTO alumno (dni, nombre, apellidos) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, alumno.getDni());
            pstmt.setString(2, alumno.getNombre());
            pstmt.setString(3, alumno.getApellidos());
            pstmt.executeUpdate();
            System.out.println("Alumno insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar alumno: " + e.getMessage());
        }
    }

    @Override
    public Alumno buscar(Alumno alumno) {
        String sql = "SELECT * FROM alumno WHERE dni = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, alumno.getDni());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Alumno(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellidos"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar alumno: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean borrar(Alumno alumno) {
        // Verificar si el alumno tiene matrículas
        String checkMatriculasSql = "SELECT COUNT(*) FROM matricula WHERE alumno_dni = ?";
        try (PreparedStatement pstmtCheck = conexion.prepareStatement(checkMatriculasSql)) {
            pstmtCheck.setString(1, alumno.getDni());
            try (ResultSet rs = pstmtCheck.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("No se puede borrar el alumno porque tiene matrículas asociadas.");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar matrículas del alumno: " + e.getMessage());
            return false;
        }

        String sql = "DELETE FROM alumno WHERE dni = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, alumno.getDni());
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Alumno borrado correctamente.");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al borrar alumno: " + e.getMessage());
        }
        return false;
    }
}
