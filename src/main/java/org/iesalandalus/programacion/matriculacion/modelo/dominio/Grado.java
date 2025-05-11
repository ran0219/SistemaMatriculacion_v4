package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum Grado {
    BASICO("Grado Básico"),
    MEDIO("Grado Medio"),
    SUPERIOR("Grado Superior");

    private String cadenaAMostrar;

    Grado(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    public String imprimir() {
        return ordinal() + ".-" + cadenaAMostrar;
    }

    @Override
    public String toString() {
        return cadenaAMostrar;
    }
}