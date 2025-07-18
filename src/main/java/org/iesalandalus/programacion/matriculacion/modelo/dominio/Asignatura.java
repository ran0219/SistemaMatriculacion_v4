package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Clase que representa una asignatura en el sistema.
 */
public class Asignatura {
    private String codigo;
    private String nombre;
    private int creditos;
    private String descripcion;
    private CicloFormativo curso;
    private Asignatura EspecialidadProfesorado;

    /**
     * Constructor para crear un nuevo objeto Asignatura.
     * @param codigo El código único de la asignatura.
     * @param nombre El nombre de la asignatura.
     * @param creditos El número de créditos de la asignatura.
     */
    public Asignatura(String codigo, String nombre, int creditos) {
        if (codigo == null || nombre == null || creditos <= 0) {
            throw new IllegalArgumentException("Código y nombre de asignatura no pueden ser nulos/vacíos y créditos deben ser positivos.");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
    }

    public Asignatura() {
        ResourceBundle rs = null;
        String nombreAsignatura = rs.getString("nombre");
        String descripcionAsignatura = rs.getString("descripcion");
        Curso cursoTipoEnum = Curso.valueOf(rs.getString("columna_curso_en_bd")); // "DAW", "DAM", etc.
        EspecialidadProfesorado especialidadEnum = EspecialidadProfesorado.valueOf(rs.getString("columna_especialidad_en_bd")); // "INFORMATICA", "MATEMATICAS", etc.

        Asignatura nuevaAsignatura = new Asignatura();
    }

    private EspecialidadProfesorado valueOf(String columnaEspecialidadEnBd) {
        return null;
    }

    // --- Getters ---
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    // --- Setters ---
    // Generalmente, el código no debería ser modificable.
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    public void setCreditos(int creditos) {
        if (creditos <= 0) {
            throw new IllegalArgumentException("Los créditos deben ser un valor positivo.");
        }
        this.creditos = creditos;
    }

    /**
     * Compara dos objetos Asignatura por su código.
     * @param o El objeto a comparar.
     * @return true si los códigos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asignatura that = (Asignatura) o;
        return Objects.equals(codigo, that.codigo); // Las asignaturas son iguales si tienen el mismo código
    }

    /**
     * Genera un código hash basado en el código de la asignatura.
     * @return El código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    /**
     * Devuelve una representación en cadena del objeto Asignatura.
     * @return String con la información de la asignatura.
     */
    @Override
    public String toString() {
        return "Asignatura [Código: " + codigo + ", Nombre: " + nombre + ", Créditos: " + creditos + "]";
    }
    public String getDescripcion() {
        return this.descripcion; // Devuelve el valor del atributo
    }


    public CicloFormativo getCurso() {
        return this.curso;
    }

    public Asignatura getEspecialidadProfesorado() {
        return this.EspecialidadProfesorado;
    }
}