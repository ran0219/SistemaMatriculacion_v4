package org.iesalandalus.programacion.matriculacion.dominio;

public enum EspecialidadProfesorado {
    INFORMATICA("Programación"),
    SISTEMAS("Sistemas y Aplicaciones Informáticas"),
    FOL("Formación y Orientación Laboral");

    private String cadenaAMostrar;

    EspecialidadProfesorado(String cadenaAMostrar) {
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