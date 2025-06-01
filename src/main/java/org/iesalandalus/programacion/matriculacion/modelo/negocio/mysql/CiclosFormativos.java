package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;

import org.iesalandalus.programacion.matriculacion.modelo.negocio.ICiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.GradoD; // Asegúrate de que GradoD y GradoE estén en el paquete modelo
import org.iesalandalus.programacion.matriculacion.modelo.dominio.GradoE;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CiclosFormativos implements ICiclosFormativos {
    private static CiclosFormativos instancia;
    private Connection conexion;

    private CiclosFormativos() {
        comenzar();
    }

    public static CiclosFormativos getInstancia() {
        if (instancia == null) {
            instancia = new CiclosFormativos();
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

    // Adaptar este método para que cree los objetos GradoD/GradoE
    // basándose en el tipo de grado almacenado en la BD.
    // Asumo que en la BD hay una columna 'tipoGrado' (e.g., "GradoD", "GradoE")
    @Override
    public GradoD getGrado(String tipoGrado, String nombre, String siglas, int numeroAlumnos, int duracion) {
        if ("GradoD".equalsIgnoreCase(tipoGrado)) {
            return new GradoD(nombre, siglas, numeroAlumnos, duracion);
        } else if ("GradoE".equalsIgnoreCase(tipoGrado)) {
            return new GradoE(nombre, siglas, numeroAlumnos, duracion);
        }
        return null; // O lanzar una excepción si el tipo no es válido
    }

    @Override
    public List<CicloFormativo> get() {
        List<CicloFormativo> ciclos = new ArrayList<>();
        String sql = "SELECT * FROM cicloFormativo ORDER BY nombre";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String tipoGrado = rs.getString("tipo_grado"); // Asumo columna para el tipo de grado
                ciclos.add((CicloFormativo) getGrado(
                        tipoGrado,
                        rs.getString("nombre"),
                        rs.getString("siglas"),
                        rs.getInt("numero_alumnos"),
                        rs.getInt("duracion")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener ciclos formativos: " + e.getMessage());
        }
        return ciclos;
    }

    @Override
    public int getTamano() {
        String sql = "SELECT COUNT(*) FROM cicloFormativo";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tamaño de ciclos formativos: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public void insertar(CicloFormativo cicloFormativo) {
        // Necesitas una forma de guardar el tipo de grado en la BD.
        // Asumo una columna 'tipo_grado' en la tabla 'cicloFormativo'.
        String sql = "INSERT INTO cicloFormativo (nombre, siglas, numero_alumnos, duracion, tipo_grado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, cicloFormativo.getNombre());
            pstmt.setString(2, cicloFormativo.getSiglas());
            pstmt.setInt(3, cicloFormativo.getNumeroAlumnos());
            pstmt.setInt(4, cicloFormativo.getDuracion());
            pstmt.setString(5, cicloFormativo instanceof GradoD ? "GradoD" : "GradoE"); // Guardar el tipo de grado
            pstmt.executeUpdate();
            System.out.println("Ciclo formativo insertado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar ciclo formativo: " + e.getMessage());
        }
    }

    @Override
    public CicloFormativo buscar(CicloFormativo cicloFormativo) {
        String sql = "SELECT * FROM cicloFormativo WHERE nombre = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, cicloFormativo.getNombre());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String tipoGrado = rs.getString("tipo_grado");
                    return (CicloFormativo) getGrado(
                            tipoGrado,
                            rs.getString("nombre"),
                            rs.getString("siglas"),
                            rs.getInt("numero_alumnos"),
                            rs.getInt("duracion")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar ciclo formativo: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean borrar(CicloFormativo cicloFormativo) {
        // Verificar si el ciclo formativo tiene matrículas
        String checkMatriculasSql = "SELECT COUNT(*) FROM matricula WHERE cicloFormativo_id = ?"; // Asumo ID o nombre
        try (PreparedStatement pstmtCheck = conexion.prepareStatement(checkMatriculasSql)) {
            pstmtCheck.setString(1, cicloFormativo.getNombre()); // Si el nombre es la clave, usarlo
            try (ResultSet rs = pstmtCheck.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("No se puede borrar el ciclo formativo porque tiene matrículas asociadas.");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar matrículas del ciclo formativo: " + e.getMessage());
            return false;
        }

        String sql = "DELETE FROM cicloFormativo WHERE nombre = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, cicloFormativo.getNombre());
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Ciclo formativo borrado correctamente.");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al borrar ciclo formativo: " + e.getMessage());
        }
        return false;
    }
}