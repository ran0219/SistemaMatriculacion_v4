package org.iesalandalus.programacion.matriculacion.dominio;

public class CicloFormativo {

    private int identificador;
    private String familiaProfesional;
    private Grado grado;
    private String nombre;
    private int horas;

    public static final int MIN_IDENTIFICADOR = 1000;
    public static final int MAX_IDENTIFICADOR = 9999;
    public static final int MAX_HORAS = 2000;

    public CicloFormativo(int identificador, String familiaProfesional, Grado grado, String nombre, int horas) {
        setIdentificador(identificador);
        setFamiliaProfesional(familiaProfesional);
        setGrado(grado);
        setNombre(nombre);
        setHoras(horas);
    }

    public CicloFormativo(CicloFormativo cicloFormativo) {
        this(cicloFormativo.identificador, cicloFormativo.familiaProfesional, cicloFormativo.grado, cicloFormativo.nombre, cicloFormativo.horas);
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        if (identificador < MIN_IDENTIFICADOR || identificador > MAX_IDENTIFICADOR) {
            throw new IllegalArgumentException("El identificador debe estar entre 1000 y 9999.");
        }
        this.identificador = identificador;
    }

    public String getFamiliaProfesional() {
        return familiaProfesional;
    }

    public void setFamiliaProfesional(String familiaProfesional) {
        if (familiaProfesional == null || familiaProfesional.trim().isEmpty()) {
            throw new IllegalArgumentException("La familia profesional no puede estar vacía.");
        }
        this.familiaProfesional = familiaProfesional;
    }

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        if (grado == null) {
            throw new IllegalArgumentException("El grado no puede ser nulo.");
        }
        this.grado = grado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        if (horas <= 0 || horas > MAX_HORAS) {
            throw new IllegalArgumentException("Las horas deben estar entre 1 y " + MAX_HORAS + ".");
        }
        this.horas = horas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CicloFormativo that = (CicloFormativo) o;
        return identificador == that.identificador;
    }

    @Override
    public int hashCode() {
        return identificador;
    }

    @Override
    public String toString() {
        return String.format("%04d, %s, %s, %s, %d", identificador, familiaProfesional, grado, nombre, horas);
    }

    public String imprimir() {
        return toString();
    }
}