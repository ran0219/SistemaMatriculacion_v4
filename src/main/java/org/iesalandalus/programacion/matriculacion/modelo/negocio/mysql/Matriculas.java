package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;

import org.iesalandalus.programacion.matriculacion.modelo.negocio.IMatriculas;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;// Asumo esta clase existe
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import java.sql.*;
import java.time.LocalDate; // Para manejar fechas
import java.util.ArrayList;
import java.util.List;

public abstract class Matriculas implements IMatriculas {
    private static Matriculas instancia;
    private Connection conexion;

    private Matriculas() {
        comenzar();
    }

    public static Matriculas getInstancia() {
        if (instancia == null) {
            instancia = new Matriculas() {
                @Override
                public List<Matricula> get() {
                    return List.of();
                }
            };
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
    public List<Asignatura> getAsignaturasMatricula(int idMatricula) {
        List<Asignatura> asignaturas = new ArrayList<>();
        String sql = "SELECT a.* FROM asignatura a " +
                "JOIN asignaturasMatricula am ON a.id = am.asignatura_id " + // Asumo ID en asignatura
                "WHERE am.matricula_id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, idMatricula);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // Necesitas el mismo logic de Asignaturas para reconstruir el objeto
                    asignaturas.add(new Asignatura(
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener asignaturas de la matrícula: " + e.getMessage());
        }
        return asignaturas;
    }

    @Override
    public List<Matricula> get() {
        List<Matricula> matriculas = new ArrayList<>();
        // Asumo que tienes IDs o DNI para relacionar en la BD
        String sql = "SELECT m.*, a.nombre as alumno_nombre, c.nombre as ciclo_nombre " +
                "FROM matricula m " +
                "JOIN alumno a ON m.alumno_dni = a.dni " +
                "JOIN cicloFormativo c ON m.cicloFormativo_id = c.nombre " + // Asumo nombre como FK
                "ORDER BY fechaMatriculacion DESC, alumno_nombre ASC";

        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Alumno alumno = Alumnos.getInstancia().buscar(new Alumno(rs.getString("alumno_dni"), null, null));
                CicloFormativo ciclo = CiclosFormativos.getInstancia().buscar(new CicloFormativo(rs.getString("cicloFormativo_id"), null, 0, 0) {}); // Necesita un constructor adecuado
                int curso = rs.getInt(Integer.parseInt("cursoAcademico_anio")); // Obtener el año del curso académico
                LocalDate fecha = rs.getDate("fechaMatriculacion").toLocalDate();
                int id = rs.getInt("id"); // Asumo un ID para la matrícula

                Matricula matricula = new Matricula(id, fecha, alumno, ciclo, curso);
                matricula.setAsignaturas(getAsignaturasMatricula(id)); // Cargar asignaturas
                matriculas.add(matricula);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener matrículas: " + e.getMessage());
        }
        return matriculas;
    }

    @Override
    public int getTamano() {
        String sql = "SELECT COUNT(*) FROM matricula";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener tamaño de matrículas: " + e.getMessage());
        }
        return 0;
    }

    @Override
    public void insertarAsignaturasMatricula(int idMatricula, List<Asignatura> asignaturas) {
        String sql = "INSERT INTO asignaturasMatricula (matricula_id, asignatura_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            for (Asignatura asignatura : asignaturas) {
                pstmt.setInt(1, idMatricula);
                // Necesitas obtener el ID de la asignatura de alguna manera. Asumo que Asignatura tiene un método getId()
                // o que la tabla asignaturas usa el nombre como clave primaria.
                // Si usa nombre como PK:
                // pstmt.setString(2, asignatura.getNombre());
                // Si usa ID numérico:
                // pstmt.setInt(2, asignatura.getId());
                pstmt.executeUpdate();
            }
            System.out.println("Asignaturas de matrícula insertadas correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar asignaturas de matrícula: " + e.getMessage());
        }
    }

    @Override
    public void insertar(Matricula matricula) {
        String sql = "INSERT INTO matricula (id, fechaMatriculacion, alumno_dni, cicloFormativo_id, cursoAcademico_anio) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, matricula.getId()); // Asumo que el ID de la matrícula es generado o proporcionado
            pstmt.setDate(2, Date.valueOf(matricula.getFechaMatriculacion()));
            pstmt.setString(3, matricula.getAlumno().getDni());
            pstmt.setString(4, matricula.getCicloFormativo().getNombre()); // Asumo nombre del ciclo como FK
            pstmt.setInt(5, (Integer) matricula.getCursoAcademico());  // Asumo un getAnio() en CursoAcademico
            pstmt.executeUpdate();
            System.out.println("Matrícula insertada correctamente.");
            // Ahora inserta las asignaturas de la matrícula
            insertarAsignaturasMatricula(matricula.getId(), matricula.getAsignaturas());
        } catch (SQLException e) {
            System.err.println("Error al insertar matrícula: " + e.getMessage());
        }
    }

    @Override
    public Matricula buscar(Matricula matricula) {
        String sql = "SELECT m.*, a.nombre as alumno_nombre, c.nombre as ciclo_nombre " +
                "FROM matricula m " +
                "JOIN alumno a ON m.alumno_dni = a.dni " +
                "JOIN cicloFormativo c ON m.cicloFormativo_id = c.nombre " +
                "WHERE m.id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, matricula.getId());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Alumno alumno = Alumnos.getInstancia().buscar(new Alumno(rs.getString("alumno_dni"), null, null));
                    CicloFormativo ciclo = CiclosFormativos.getInstancia().buscar(new CicloFormativo(rs.getString("cicloFormativo_id"), null, 0, 0) {});
                    CursoAcademico curso = new CursoAcademico();
                    LocalDate fecha = rs.getDate("fechaMatriculacion").toLocalDate();
                    int id = rs.getInt("id");

                    Matricula foundMatricula = new Matricula(id, fecha, alumno, ciclo, curso);
                    foundMatricula.setAsignaturas(getAsignaturasMatricula(id));
                    return foundMatricula;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar matrícula: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean borrar(Matricula matricula) {
        // Primero borrar las asignaturas asociadas a la matrícula
        String deleteAsignaturasSql = "DELETE FROM asignaturasMatricula WHERE matricula_id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(deleteAsignaturasSql)) {
            pstmt.setInt(1, matricula.getId());
            pstmt.executeUpdate();
            System.out.println("Asignaturas de matrícula borradas.");
        } catch (SQLException e) {
            System.err.println("Error al borrar asignaturas de matrícula: " + e.getMessage());
            return false;
        }

        // Luego borrar la matrícula
        String deleteMatriculaSql = "DELETE FROM matricula WHERE id = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(deleteMatriculaSql)) {
            pstmt.setInt(1, matricula.getId());
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Matrícula borrada correctamente.");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al borrar matrícula: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Matricula> get(Alumno alumno) {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT m.*, c.nombre as ciclo_nombre FROM matricula m JOIN cicloFormativo c ON m.cicloFormativo_id = c.nombre WHERE m.alumno_dni = ? ORDER BY fechaMatriculacion DESC";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, alumno.getDni());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CicloFormativo ciclo = CiclosFormativos.getInstancia().buscar(new CicloFormativo(rs.getString("cicloFormativo_id"), null, 0, 0) {});
                    CursoAcademico curso = new CursoAcademico();
                    LocalDate fecha = rs.getDate("fechaMatriculacion").toLocalDate();
                    int id = rs.getInt("id");
                    Matricula matricula = new Matricula(id, fecha, alumno, ciclo, curso);
                    matricula.setAsignaturas(getAsignaturasMatricula(id));
                    matriculas.add(matricula);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener matrículas por alumno: " + e.getMessage());
        }
        return matriculas;
    }

    @Override
    public List<Matricula> get(CicloFormativo cicloFormativo) {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT m.*, a.nombre as alumno_nombre FROM matricula m JOIN alumno a ON m.alumno_dni = a.dni WHERE m.cicloFormativo_id = ? ORDER BY fechaMatriculacion DESC";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, cicloFormativo.getNombre());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Alumno alumno = Alumnos.getInstancia().buscar(new Alumno(rs.getString("alumno_dni"), null, null));
                    CursoAcademico curso = new CursoAcademico();
                    LocalDate fecha = rs.getDate("fechaMatriculacion").toLocalDate();
                    int id = rs.getInt("id");
                    Matricula matricula = new Matricula(id, fecha, alumno, cicloFormativo, curso);
                    matricula.setAsignaturas(getAsignaturasMatricula(id));
                    matriculas.add(matricula);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener matrículas por ciclo formativo: " + e.getMessage());
        }
        return matriculas;
    }

    @Override
    public List<Matricula> get(CursoAcademico cursoAcademico) {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT m.*, a.nombre as alumno_nombre, c.nombre as ciclo_nombre FROM matricula m JOIN alumno a ON m.alumno_dni = a.dni JOIN cicloFormativo c ON m.cicloFormativo_id = c.nombre WHERE m.cursoAcademico_anio = ? ORDER BY fechaMatriculacion DESC";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, cursoAcademico.getAnio());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Alumno alumno = Alumnos.getInstancia().buscar(new Alumno(rs.getString("alumno_dni"), null, null));
                    CicloFormativo ciclo = CiclosFormativos.getInstancia().buscar(new CicloFormativo(rs.getString("cicloFormativo_id"), null, 0, 0) {});
                    LocalDate fecha = rs.getDate("fechaMatriculacion").toLocalDate();
                    int id = rs.getInt("id");
                    Matricula matricula = new Matricula(id, fecha, alumno, ciclo, cursoAcademico);
                    matricula.setAsignaturas(getAsignaturasMatricula(id));
                    matriculas.add(matricula);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener matrículas por curso académico: " + e.getMessage());
        }
        return matriculas;
    }

    public class CursoAcademico {
        public CursoAcademico() {
        }

        public int getAnio() {
            return 0;
        }
    }
}