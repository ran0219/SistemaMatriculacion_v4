package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList; // Para la lista de asignaturas
import java.util.List;     // Para el tipo List
import java.util.Objects;   // Para equals y hashCode

/**
 * Clase que representa una matrícula de un alumno en un Ciclo Formativo para un Curso Académico.
 * Esta matrícula puede incluir varias asignaturas.
 */
public class Matricula {

    // --- ATRIBUTOS ---
    private int id; // Identificador único de la matrícula
    private Alumno alumno; // Referencia al objeto Alumno matriculado
    private CicloFormativo cicloFormativo; // Referencia al Ciclo Formativo asociado a la matrícula
    private int cursoAcademico; // El año del curso académico (ej. 2024, 2025)
    private LocalDate fecha; // La fecha en que se realizó la matrícula
    private Double nota; // Nota general de la matrícula (puede ser null si no está calificada)
    private List<Asignatura> asignaturas; // Lista de asignaturas en las que se ha matriculado el alumno

    // Formato de fecha para la representación en String
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // --- CONSTRUCTORES ---

    /**
     * Constructor por defecto. Inicializa la lista de asignaturas a una lista vacía.
     */
    public Matricula() {
        this.asignaturas = new ArrayList<>();
    }

    /**
     * Constructor principal para crear una matrícula.
     * La nota y la lista de asignaturas se inicializan por defecto (null y vacía).
     *
     * @param id El ID único de la matrícula.
     * @param fecha La fecha en que se realizó la matrícula.
     * @param alumno El objeto Alumno matriculado.
     * @param cicloFormativo El objeto CicloFormativo de la matrícula.
     * @param cursoAcademico El año del curso académico.
     * @throws IllegalArgumentException si el alumno, ciclo formativo o fecha son nulos.
     */
    public Matricula(int id, LocalDate fecha, Alumno alumno, CicloFormativo cicloFormativo, int cursoAcademico) {
        if (alumno == null || cicloFormativo == null || fecha == null) {
            throw new IllegalArgumentException("El alumno, el ciclo formativo y la fecha de matrícula no pueden ser nulos.");
        }
        this.id = id;
        this.fecha = fecha;
        this.alumno = alumno;
        this.cicloFormativo = cicloFormativo;
        this.cursoAcademico = cursoAcademico;
        this.nota = null; // Inicialmente sin nota
        this.asignaturas = new ArrayList<>(); // Siempre inicializar la lista
    }

    /**
     * Constructor completo que incluye la nota inicial.
     *
     * @param id El ID único de la matrícula.
     * @param fecha La fecha en que se realizó la matrícula.
     * @param alumno El objeto Alumno matriculado.
     * @param cicloFormativo El objeto CicloFormativo de la matrícula.
     * @param cursoAcademico El año del curso académico.
     * @param nota La nota inicial de la matrícula.
     * @throws IllegalArgumentException si la nota no es válida.
     */
    public Matricula(int id, LocalDate fecha, Alumno alumno, CicloFormativo cicloFormativo, int cursoAcademico, Double nota) {
        this(id, fecha, alumno, cicloFormativo, cursoAcademico); // Reutiliza el constructor anterior
        setNota(nota); // Usa el setter para la validación de nota
    }

    public Matricula(String idMatricula, Alumno alumno, Asignatura asignatura, LocalDate fechaMatriculacion, Double nota) {
    }

    // --- MÉTODOS GETTERS ---

    public int getId() {
        return id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public CicloFormativo getCicloFormativo() {
        return cicloFormativo;
    }

    public int getCursoAcademico() {
        return cursoAcademico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Double getNota() {
        return nota;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    // --- MÉTODOS SETTERS ---

    public void setId(int id) {
        this.id = id;
    }

    public void setAlumno(Alumno alumno) {
        if (alumno == null) {
            throw new IllegalArgumentException("El alumno no puede ser nulo.");
        }
        this.alumno = alumno;
    }

    public void setCicloFormativo(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException("El ciclo formativo no puede ser nulo.");
        }
        this.cicloFormativo = cicloFormativo;
    }

    public void setCursoAcademico(int cursoAcademico) {
        // Podrías añadir validación para el rango del curso académico si lo necesitas
        this.cursoAcademico = cursoAcademico;
    }

    public void setFecha(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        this.fecha = fecha;
    }

    /**
     * Establece la nota de la matrícula.
     * @param nota La nueva nota (debe estar entre 0 y 10, o ser null).
     * @throws IllegalArgumentException si la nota está fuera de rango.
     */
    public void setNota(Double nota) {
        if (nota != null && (nota < 0.0 || nota > 10.0)) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 10.");
        }
        this.nota = nota;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        // Puedes añadir una validación para que la lista no sea null si lo deseas
        this.asignaturas = asignaturas != null ? new ArrayList<>(asignaturas) : new ArrayList<>();
    }

    // --- MÉTODOS OVERRIDE (equals, hashCode, toString) ---

    /**
     * Compara dos objetos Matricula por su ID, alumno, ciclo formativo y curso académico.
     * Dos matrículas son iguales si tienen el mismo ID.
     * @param o El objeto a comparar.
     * @return true si los IDs son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return id == matricula.id; // Las matrículas son iguales si tienen el mismo ID
    }

    /**
     * Genera un código hash basado en el ID de la matrícula.
     * @return El código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Devuelve una representación en cadena del objeto Matrícula.
     * @return String con la información de la matrícula.
     */
    @Override
    public String toString() {
        String infoAsignaturas = "[]";
        if (asignaturas != null && !asignaturas.isEmpty()) {
            List<String> nombresAsignaturas = new ArrayList<>();
            for (Asignatura a : asignaturas) {
                nombresAsignaturas.add(a.getNombre()); // Asumo que Asignatura tiene getNombre()
            }
            infoAsignaturas = nombresAsignaturas.toString();
        }

        return "Matricula{" +
                "ID=" + id +
                ", alumno=" + (alumno != null ? alumno.getDni() : "N/A") + // Asumo que Alumno tiene getDni()
                ", cicloFormativo=" + (cicloFormativo != null ? cicloFormativo.getNombre() : "N/A") + // Asumo que CicloFormativo tiene getNombre()
                ", cursoAcademico=" + cursoAcademico +
                ", fecha=" + (fecha != null ? fecha.format(FORMATO_FECHA) : "N/A") +
                ", nota=" + (nota != null ? String.format("%.2f", nota) : "Sin calificar") +
                ", asignaturas=" + infoAsignaturas +
                '}';
    }

    public boolean getIdMatricula() {
    }

    public CicloFormativo getAsignatura() {
    }
}