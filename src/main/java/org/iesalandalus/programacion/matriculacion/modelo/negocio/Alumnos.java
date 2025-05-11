package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

public class Alumnos {

    private static final int CAPACIDAD_MAXIMA = 100;
    private Alumno[] alumnos;
    private int numAlumnos;

    public Alumnos() {
        alumnos = new Alumno[CAPACIDAD_MAXIMA];
        numAlumnos = 0;
    }

    public Alumno[] get() {
        Alumno[] copia = new Alumno[numAlumnos];
        for (int i = 0; i < numAlumnos; i++) {
            copia[i] = new Alumno(alumnos[i]); // copia profunda
        }
        return copia;
    }

    public int getCapacidad() {
        return CAPACIDAD_MAXIMA;
    }

    public int getCantidad() {
        return numAlumnos;
    }

    public void insertar(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("No se puede insertar un alumno nulo.");
        }

        if (numAlumnos >= CAPACIDAD_MAXIMA) {
            throw new IllegalStateException("No caben más alumnos.");
        }

        if (buscar(alumno) != null) {
            throw new IllegalArgumentException("El alumno ya existe.");
        }

        alumnos[numAlumnos++] = new Alumno(alumno); // copia defensiva
    }

    public Alumno buscar(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("No se puede buscar un alumno nulo.");
        }

        for (int i = 0; i < numAlumnos; i++) {
            if (alumnos[i].equals(alumno)) {
                return new Alumno(alumnos[i]);
            }
        }
        return null;
    }

    public void borrar(Alumno alumno) {
        if (alumno == null) {
            throw new NullPointerException("No se puede borrar un alumno nulo.");
        }

        for (int i = 0; i < numAlumnos; i++) {
            if (alumnos[i].equals(alumno)) {
                desplazarIzquierda(i);
                numAlumnos--;
                return;
            }
        }

        throw new IllegalArgumentException("No se ha encontrado el alumno a borrar.");
    }

    private void desplazarIzquierda(int indice) {
        for (int i = indice; i < numAlumnos - 1; i++) {
            alumnos[i] = alumnos[i + 1];
        }
        alumnos[numAlumnos - 1] = null;
    }
}
