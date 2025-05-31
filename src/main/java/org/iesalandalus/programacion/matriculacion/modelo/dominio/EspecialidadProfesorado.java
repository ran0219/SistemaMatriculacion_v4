package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum EspecialidadProfesorado {
    INFORMATICA("Programación"),
    SISTEMAS("Sistemas y Aplicaciones Informáticas"),
    FOL("Formación y Orientación Laboral") {
        @Override
        public String imprimir() {
            return super.imprimir();
        }
    };

    private final String cadenaAMostrar;

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