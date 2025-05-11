package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public class Asignatura {

    private int identificador;
    private String nombre;
    private int horasAnuales;
    private int horasDesdoble;
    private Curso curso;
    private EspecialidadProfesorado especialidadProfesorado;
    private CicloFormativo cicloFormativo;

    public static final int MIN_IDENTIFICADOR = 1000;
    public static final int MAX_IDENTIFICADOR = 9999;
    public static final int MAX_HORAS_ANUALES = 300;
    public static final int MAX_HORAS_DESDOBLE = 6;

    public Asignatura(int identificador, String nombre, int horasAnuales, int horasDesdoble, Curso curso, EspecialidadProfesorado especialidadProfesorado, CicloFormativo cicloFormativo) {
        setIdentificador(identificador);
        setNombre(nombre);
        setHorasAnuales(horasAnuales);
        setHorasDesdoble(horasDesdoble);
        setCurso(curso);
        setEspecialidadProfesorado(especialidadProfesorado);
        setCicloFormativo(cicloFormativo);
    }

    public Asignatura(Asignatura asignatura) {
        this(asignatura.identificador, asignatura.nombre, asignatura.horasAnuales, asignatura.horasDesdoble, asignatura.curso, asignatura.especialidadProfesorado, asignatura.cicloFormativo);
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public int getHorasAnuales() {
        return horasAnuales;
    }

    public void setHorasAnuales(int horasAnuales) {
        if (horasAnuales <= 0 || horasAnuales > MAX_HORAS_ANUALES) {
            throw new IllegalArgumentException("Las horas anuales deben estar entre 1 y " + MAX_HORAS_ANUALES + ".");
        }
        this.horasAnuales = horasAnuales;
    }

    public int getHorasDesdoble() {
        return horasDesdoble;
    }

    public void setHorasDesdoble(int horasDesdoble) {
        if (horasDesdoble < 0 || horasDesdoble > MAX_HORAS_DESDOBLE) {
            throw new IllegalArgumentException("Las horas de desdoble deben estar entre 0 y " + MAX_HORAS_DESDOBLE + ".");
        }
        this.horasDesdoble = horasDesdoble;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        if (curso == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo.");
        }
        this.curso = curso;
    }

    public EspecialidadProfesorado getEspecialidadProfesorado() {
        return especialidadProfesorado;
    }

    public void setEspecialidadProfesorado(EspecialidadProfesorado especialidadProfesorado) {
        if (especialidadProfesorado == null) {
            throw new IllegalArgumentException("La especialidad del profesorado no puede ser nula.");
        }
        this.especialidadProfesorado = especialidadProfesorado;
    }

    public CicloFormativo getCicloFormativo() {
        return cicloFormativo;
    }

    public void setCicloFormativo(CicloFormativo cicloFormativo) {
        if (cicloFormativo == null) {
            throw new IllegalArgumentException("El ciclo formativo no puede ser nulo.");
        }
        this.cicloFormativo = cicloFormativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Asignatura that = (Asignatura) o;
        return identificador == that.identificador;
    }

    @Override
    public int hashCode() {
        return identificador;
    }

    @Override
    public String toString() {
        return String.format("%04d, %s, %d, %d, %s, %s, %s", identificador, nombre, horasAnuales, horasDesdoble, curso, especialidadProfesorado, cicloFormativo);
    }

    public String imprimir() {
        return toString();
    }
}