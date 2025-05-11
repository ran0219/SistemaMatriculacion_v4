package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum Curso {
    PRIMERO("Primero"),
    SEGUNDO("Segundo");

    private String cadenaAMostrar;

    Curso(String cadenaAMostrar) {
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
