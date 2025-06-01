package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public enum TiposGrado {
    GRADO_D("Grado D"),
    GRADO_E("Grado E");

    private String descripcion;

    TiposGrado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
