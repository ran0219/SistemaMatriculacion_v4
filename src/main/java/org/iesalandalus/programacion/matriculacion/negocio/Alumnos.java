package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Alumno;

public class Alumnos {

    private Alumno[] alumnos;
    private int cantidadAlumnos;

    public Alumnos(int capacidad) {
        this.alumnos = new Alumno[capacidad];
        this.cantidadAlumnos = 0;
    }

    public Alumno[] get() {
        Alumno[] copia = new Alumno[cantidadAlumnos];
        for (int i = 0; i < cantidadAlumnos; i++) {
            copia[i] = new Alumno(alumnos[i]);
        }
        return copia;
    }

    public int getTamaño() {
        return alumnos.length;
    }

    public int getCantidad() {
        return cantidadAlumnos;
    }

    public void insertar(Alumno alumno) {
        if (alumno != null && cantidadAlumnos < alumnos.length && buscar(alumno.getDni()) == null) {
            alumnos[cantidadAlumnos] = alumno;
            cantidadAlumnos++;
        }
    }

    private int buscarIndice(String dni) {
        for (int i = 0; i < cantidadAlumnos; i++) {
            if (alumnos[i].getDni().equals(dni)) {
                return i;
            }
        }
        return -1;
    }

    public boolean tamañoSuperado() {
        return cantidadAlumnos > 0;
    }

    public boolean capacidadSuperada() {
        return cantidadAlumnos == alumnos.length;
    }

    public Alumno buscar(String dni) {
        for (int i = 0; i < cantidadAlumnos; i++) {
            if (alumnos[i].getDni().equals(dni)) {
                return alumnos[i];
            }
        }
        return null;
    }

    public void borrar(String dni) {
        for (int i = 0; i < cantidadAlumnos; i++) {
            if (alumnos[i].getDni().equals(dni)) {
                for (int j = i; j < cantidadAlumnos - 1; j++) {
                    alumnos[j] = alumnos[j + 1];
                }
                alumnos[cantidadAlumnos - 1] = null;
                cantidadAlumnos--;
                return;
            }
        }
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < cantidadAlumnos - 1; i++) {
            alumnos[i] = alumnos[i + 1];
        }
    }
}
