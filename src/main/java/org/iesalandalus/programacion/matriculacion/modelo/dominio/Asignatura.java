package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.util.Objects;

/**
 * Clase que representa una asignatura en el sistema.
 */
public class Asignatura {
    private String codigo;
    private String nombre;
    private int creditos;

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
}