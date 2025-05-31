package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Clase que representa una matrícula de un alumno en una asignatura.
 */
public class Matricula {
    private final String idMatricula;
    private final Alumno alumno; // Referencia al objeto Alumno
    private final Asignatura asignatura; // Referencia al objeto Asignatura
    private final LocalDate fechaMatriculacion;
    private Double nota; // Usamos Double para permitir null si la nota no está establecida
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Constructor para crear una nueva matrícula sin nota inicial.
     * @param idMatricula El ID único de la matrícula.
     * @param alumno El objeto Alumno matriculado.
     * @param asignatura El objeto Asignatura en la que se matricula.
     * @param fechaMatriculacion La fecha en que se realizó la matrícula.
     */
    public Matricula(String idMatricula, Alumno alumno, Asignatura asignatura, LocalDate fechaMatriculacion) {
        if (idMatricula == null || alumno == null || asignatura == null || fechaMatriculacion == null) {
            throw new IllegalArgumentException("El ID, alumno, asignatura y fecha de matrícula no pueden ser nulos.");
        }
        this.idMatricula = idMatricula;
        this.alumno = alumno;
        this.asignatura = asignatura;
        this.fechaMatriculacion = fechaMatriculacion;
        this.nota = null; // Inicialmente sin nota
    }

    /**
     * Constructor para crear una nueva matrícula con nota inicial.
     * @param idMatricula El ID único de la matrícula.
     * @param alumno El objeto Alumno matriculado.
     * @param asignatura El objeto Asignatura en la que se matricula.
     * @param fechaMatriculacion La fecha en que se realizó la matrícula.
     * @param nota La nota inicial de la matrícula (puede ser null).
     */
    public Matricula(String idMatricula, Alumno alumno, Asignatura asignatura, LocalDate fechaMatriculacion, Double nota) {
        this(idMatricula, alumno, asignatura, fechaMatriculacion); // Reutiliza el constructor anterior
        setNota(nota); // Usa el setter para la validación de nota
    }

    // --- Getters ---
    public String getIdMatricula() {
        return idMatricula;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        if (nota != null && (nota < 0.0 || nota > 10.0)) {
            throw new IllegalArgumentException("La nota debe estar entre 0.0 y 10.0.");
        }
        this.nota = nota;
    }

    /**
     * Compara dos objetos Matricula por su ID de matrícula.
     * @param o El objeto a comparar.
     * @return true si los ID son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return Objects.equals(idMatricula, matricula.idMatricula); // Las matrículas son iguales si tienen el mismo ID
    }

    /**
     * Genera un código hash basado en el ID de la matrícula.
     * @return El código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(idMatricula);
    }

    /**
     * Devuelve una representación en cadena del objeto Matrícula
     * @return String con la información de la matrícula.
     */
    @Override
    public String toString() {
        return "Matricula [ID: " + idMatricula +
                ", Alumno DNI: " + (alumno != null ? alumno.getDni() : "N/A") +
                ", Asignatura Código: " + (asignatura != null ? asignatura.getCodigo() : "N/A") +
                ", Fecha: " + fechaMatriculacion.format(FORMATO_FECHA) +
                ", Nota: " + (nota != null ? String.format("%.2f", nota) : "Sin calificar") + "]";
    }
}