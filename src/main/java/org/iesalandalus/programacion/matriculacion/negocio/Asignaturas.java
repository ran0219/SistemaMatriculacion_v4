package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;

public class Asignaturas {

    private Asignatura[] asignaturas;
    private int cantidadAsignaturas;

    public Asignaturas(int capacidad) {
        this.asignaturas = new Asignatura[capacidad];
        this.cantidadAsignaturas = 0;
    }

    public Asignatura[] get() {
        return copiaProfundaAsignaturas();
    }

    private Asignatura[] copiaProfundaAsignaturas() {
        Asignatura[] copia = new Asignatura[cantidadAsignaturas];
        for (int i = 0; i < cantidadAsignaturas; i++) {
            copia[i] = new Asignatura(asignaturas[i]);
        }
        return copia;
    }

    public void insertar(Asignatura asignatura) {
        if (asignatura != null && !capacidadSuperada() && buscar(asignatura.getIdentificador()) == null) {
            asignaturas[cantidadAsignaturas] = asignatura;
            cantidadAsignaturas++;
        }
    }

    public Asignatura buscar(int identificador) {
        for (int i = 0; i < cantidadAsignaturas; i++) {
            if (asignaturas[i].getIdentificador() == identificador) {
                return asignaturas[i];
            }
        }
        return null;
    }

    public void borrar(int identificador) {
        int indice = buscarIndice(identificador);
        if (indice != -1) {
            desplazarUnaPosicionHaciaIzquierda(indice);
            asignaturas[cantidadAsignaturas - 1] = null;
            cantidadAsignaturas--;
        }
    }

    private int buscarIndice(int identificador) {
        for (int i = 0; i < cantidadAsignaturas; i++) {
            if (asignaturas[i].getIdentificador() == identificador) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < cantidadAsignaturas - 1; i++) {
            asignaturas[i] = asignaturas[i + 1];
        }
    }

    public boolean capacidadSuperada() {
        return cantidadAsignaturas == asignaturas.length;
    }

    public int getCantidadAsignaturas() {
        return cantidadAsignaturas;
    }

    public int getTamaño() {
        return asignaturas.length;
    }
}