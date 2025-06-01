package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public class GradoD extends Grado {
    private Integer numAnios;

    public GradoD(String nombre, String siglas, int numeroAlumnos, int duracion) {
        super(nombre);
        setNumAnios(2); // Valor por defecto válido
    }

    @Override
    public void setNumAnios(int numAnios) {
        if (numAnios != 2 && numAnios != 3) {
            throw new IllegalArgumentException("ERROR: El número de años para Grado D debe ser 2 o 3.");
        }
        this.numAnios = numAnios; // Asignación directa al campo
    }

    @Override
    public String toString() {
        return String.format("%s, Años: %d, Tipo: Grado D", super.toString(), numAnios);
    }
}