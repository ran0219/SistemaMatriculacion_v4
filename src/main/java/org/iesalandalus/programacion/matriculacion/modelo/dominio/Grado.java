package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum Grado {
    BASICO("Grado Básico"),
    MEDIO("Grado Medio"),
    SUPERIOR("Grado Superior");

    private final String cadenaAMostrar;

    Grado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    @Override
    public String toString() {
        return cadenaAMostrar;
    }
}