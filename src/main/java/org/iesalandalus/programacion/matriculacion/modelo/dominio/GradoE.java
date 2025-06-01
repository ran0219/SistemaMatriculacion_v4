package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public class GradoE extends Grado {
    public GradoE(String nombre) throws IllegalArgumentException {
        super(nombre);
        setNumAnios(1); // El número de años para Grado E siempre es 1
    }

    public GradoE(String nombre, String siglas, int numeroAlumnos, int duracion) {

        super();
    }

    @Override
    public void setNumAnios(int numAnios) throws IllegalArgumentException {
        if (numAnios != 1) {
            throw new IllegalArgumentException("El número de años para Grado E debe ser 1.");
        }
        super.setNumAnios(numAnios);
    }
}
