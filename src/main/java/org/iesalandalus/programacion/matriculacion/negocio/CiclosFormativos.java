package org.iesalandalus.programacion.matriculacion.negocio;

import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;

public class CiclosFormativos {

    private CicloFormativo[] ciclosFormativos;
    private int cantidadCiclos;

    public CiclosFormativos(int capacidad) {
        this.ciclosFormativos = new CicloFormativo[capacidad];
        this.cantidadCiclos = 0;
    }

    public CicloFormativo[] get() {
        return copiaProfundaCiclosFormativos();
    }

    private CicloFormativo[] copiaProfundaCiclosFormativos() {
        CicloFormativo[] copia = new CicloFormativo[cantidadCiclos];
        for (int i = 0; i < cantidadCiclos; i++) {
            copia[i] = new CicloFormativo(ciclosFormativos[i]);
        }
        return copia;
    }

    public void insertar(CicloFormativo cicloFormativo) {
        if (cicloFormativo != null && !capacidadSuperada() && buscar(cicloFormativo.getIdentificador()) == null) {
            ciclosFormativos[cantidadCiclos] = cicloFormativo;
            cantidadCiclos++;
        }
    }

    public CicloFormativo buscar(int identificador) {
        for (int i = 0; i < cantidadCiclos; i++) {
            if (ciclosFormativos[i].getIdentificador() == identificador) {
                return ciclosFormativos[i];
            }
        }
        return null;
    }

    public void borrar(int identificador) {
        int indice = buscarIndice(identificador);
        if (indice != -1) {
            desplazarUnaPosicionHaciaIzquierda(indice);
            ciclosFormativos[cantidadCiclos - 1] = null;
            cantidadCiclos--;
        }
    }

    private int buscarIndice(int identificador) {
        for (int i = 0; i < cantidadCiclos; i++) {
            if (ciclosFormativos[i].getIdentificador() == identificador) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < cantidadCiclos - 1; i++) {
            ciclosFormativos[i] = ciclosFormativos[i + 1];
        }
    }

    public boolean capacidadSuperada() {
        return cantidadCiclos == ciclosFormativos.length;
    }

    public int getCantidadCiclos() {
        return cantidadCiclos;
    }

    public int getTamaño() {
        return ciclosFormativos.length;
    }
}
