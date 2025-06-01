package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.util.Objects;

/**
 * Clase que representa un ciclo formativo (ej. Grado Superior, Grado Medio) en el sistema.
 */
public class CicloFormativo {
    private final String cicloFormativoId;
    private final Object duracionAnios;
    private String codigo;
    private String nombre;
    private String nivel; // Ej: "Grado Medio", "Grado Superior"
    private String tipo;

    /**
     * Constructor para crear un nuevo objeto CicloFormativo.
     * @param codigo El código único del ciclo formativo.
     * @param nombre El nombre del ciclo formativo.
     * @param nivel El nivel del ciclo formativo (ej. "Grado Medio", "Grado Superior").
     */
    public CicloFormativo(Object duracionAnios, String codigo, String nombre, String nivel) {
        this.duracionAnios = duracionAnios;
        if (codigo == null || nombre == null || nivel == null) {
            throw new IllegalArgumentException("Todos los campos del ciclo formativo deben ser no nulos.");
        }
        this.codigo = codigo;
        this.nombre = nombre;
        this.nivel = nivel;
        cicloFormativoId = "";
    }

    public CicloFormativo(String cicloFormativoId, Object nombre, int i, int i1) {
        this.cicloFormativoId = cicloFormativoId;
        this.nombre = nombre.toString();
        Object duracionAnios = new Object();
        this.duracionAnios = duracionAnios;
    }

    public CicloFormativo(String dam, String desarrolloDeAplicacionesMultiplataforma, String gradoSuperior, String cicloFormativoId, Object duracionAnios) {
        this.cicloFormativoId = cicloFormativoId;
        this.duracionAnios = duracionAnios;
    }

    // --- Getters ---
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNivel() {
        return nivel;
    }

    // --- Setters ---
    // Generalmente, el código no debería ser modificable.
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    public void setNivel(String nivel) {
        if (nivel == null || nivel.trim().isEmpty()) {
            throw new IllegalArgumentException("El nivel no puede ser nulo o vacío.");
        }
        this.nivel = nivel;
    }

    /**
     * Compara dos objetos CicloFormativo por su código.
     * @param o El objeto a comparar.
     * @return true si los códigos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CicloFormativo that = (CicloFormativo) o;
        return Objects.equals(codigo, that.codigo); // Los ciclos son iguales si tienen el mismo código
    }

    /**
     * Genera un código hash basado en el código del ciclo formativo.
     * @return El código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    /**
     * Devuelve una representación en cadena del objeto CicloFormativo.
     * @return String con la información del ciclo formativo.
     */
    @Override
    public String toString() {
        return "CicloFormativo [Código: " + codigo + ", Nombre: " + nombre + ", Nivel: " + nivel + "]";
    }

    public String getTipo() {
        return tipo;
    }


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
}