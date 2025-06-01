package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;

import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAsignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Curso; // Asegúrate de que Curso y EspecialidadProfesorado estén en el paquete modelo
import org.iesalandalus.programacion.matriculacion.modelo.dominio.EspecialidadProfesorado;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Asignaturas implements IAsignaturas {
    private static Asignaturas instancia;
    private Connection conexion;

    private Asignaturas() {
        comenzar();
    }

    public static Asignaturas getInstancia() {
        if (instancia == null) {
            instancia = new Asignaturas();
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

    @Override
    public Curso getCurso(String tipoCurso) {
        return Curso.valueOf(tipoCurso);
    }

    @Override
    public EspecialidadProfesorado getEspecialidadProfesorado(String especialidad) {
        return EspecialidadProfesorado.valueOf(especialidad);
    }

    @Override
    public List<Asignatura> get() {
        List<Asignatura> asignaturas = new ArrayList<>();
        String sql = "SELECT * FROM asignatura ORDER BY nombre";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                asignaturas.add(new Asignatura(
                        // Asumo columna 'curso_tipo'
                        // Asumo columna
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener asignaturas: " + e.getMessage());
        }
        return asignaturas;
    }

    @Override
    public int getTamano() {
        String sql = "SELECT COUNT(*) FROM asignatura";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tamaño de asignaturas: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public void insertar(Asignatura asignatura) {
        String sql = "INSERT INTO asignatura (nombre, descripcion, curso_tipo, especialidad_profesorado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, asignatura.getNombre());
            pstmt.setString(2, asignatura.getDescripcion());
            pstmt.setString(3, asignatura.getCurso().getTipo()); // Asumo un método getTipo() en Curso
            pstmt.setString(4, asignatura.getEspecialidadProfesorado().getNombre()); // Asumo un método getNombre() en EspecialidadProfesorado
            pstmt.executeUpdate();
            System.out.println("Asignatura insertada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar asignatura: " + e.getMessage());
        }
    }

    @Override
    public Asignatura buscar(Asignatura asignatura) {
        String sql = "SELECT * FROM asignatura WHERE nombre = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, asignatura.getNombre());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Asignatura(
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar asignatura: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean borrar(Asignatura asignatura) {
        // Verificar si la asignatura tiene matrículas asociadas
        String checkMatriculasSql = "SELECT COUNT(*) FROM asignaturasMatricula WHERE asignatura_nombre = ?"; // Asumo nombre de asignatura como FK
        try (PreparedStatement pstmtCheck = conexion.prepareStatement(checkMatriculasSql)) {
            pstmtCheck.setString(1, asignatura.getNombre());
            try (ResultSet rs = pstmtCheck.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    System.out.println("No se puede borrar la asignatura porque tiene matrículas asociadas.");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al verificar matrículas de la asignatura: " + e.getMessage());
            return false;
        }

        String sql = "DELETE FROM asignatura WHERE nombre = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, asignatura.getNombre());
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Asignatura borrada correctamente.");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al borrar asignatura: " + e.getMessage());
        }
        return false;
    }
    // En mysql/Asignaturas.java

    @Override
    public List<Asignatura> get() {
        List<Asignatura> asignaturas = new ArrayList<>();
        // Asegúrate de seleccionar las columnas correctas en tu SQL
        // Incluyendo las columnas que guardan el nombre del enum (por ejemplo, 'curso_tipo_str', 'especialidad_prof_str')
        String sql = "SELECT id, nombre, descripcion, curso_tipo, especialidad_profesorado FROM asignatura ORDER BY nombre"; // Asegúrate de que los nombres de columna son correctos en tu BD
        try (Connection conexion = MySQL.getConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql); // Podrías usar Statement si no hay parámetros
             ResultSet rs = pstmt.executeQuery()) { // Si usas PreparedStatement, el executeQuery no lleva SQL
            while (rs.next()) {
                // Obtener los Strings de la BD
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String cursoTipoStr = rs.getString("curso_tipo"); // Nombre de la columna en la BD
                String especialidadProfesoradoStr = rs.getString("especialidad_profesorado"); // Nombre de la columna en la BD

                // Convertir los Strings a las instancias de enum usando el método valueOf()
                Curso cursoTipo = Curso.valueOf(cursoTipoStr);
                EspecialidadProfesorado especialidadProfesorado = EspecialidadProfesorado.valueOf(especialidadProfesoradoStr);

                // Crear la instancia de Asignatura con los objetos enum
                asignaturas.add(new Asignatura());
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener asignaturas: " + e.getMessage());
        }
        return asignaturas;
    }
}