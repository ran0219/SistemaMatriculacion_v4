package org.iesalandalus.programacion.matriculacion.dominio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;

public class Matricula {

    private int identificador;
    private String cursoAcademico;
    private LocalDate fechaMatricula;
    private LocalDate fechaAnulacion;
    private List<Asignatura> asignaturas;

    public static final int MAX_HORAS_MATRICULA = 1000;
    public static final int MAX_ASIGNATURAS_MATRICULA = 10;
    public static final int MAX_DIAS_RETRASO_MATRICULA = 15;
    public static final int MAX_MESES_ANULACION_MATRICULA = 6;

    public Matricula(int identificador, String cursoAcademico, LocalDate fechaMatricula, List<Asignatura> asignaturas) {
        setIdentificador(identificador);
        setCursoAcademico(cursoAcademico);
        setFechaMatricula(fechaMatricula);
        setAsignaturas(asignaturas);
        this.fechaAnulacion = null;
    }

    public Matricula(Matricula matricula) {
        this(matricula.identificador, matricula.cursoAcademico, matricula.fechaMatricula, matricula.asignaturas);
        this.fechaAnulacion = matricula.fechaAnulacion;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        if (identificador < 1000 || identificador > 9999) {
            throw new IllegalArgumentException("El identificador debe estar entre 1000 y 9999.");
        }
        this.identificador = identificador;
    }

    public String getCursoAcademico() {
        return cursoAcademico;
    }

    public void setCursoAcademico(String cursoAcademico) {
        if (cursoAcademico == null || cursoAcademico.trim().isEmpty()) {
            throw new IllegalArgumentException("El curso académico no puede estar vacío.");
        }
        this.cursoAcademico = cursoAcademico;
    }

    public LocalDate getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(LocalDate fechaMatricula) {
        if (fechaMatricula == null) {
            throw new IllegalArgumentException("La fecha de matrícula no puede ser nula.");
        }
        if (ChronoUnit.DAYS.between(fechaMatricula, LocalDate.now()) > MAX_DIAS_RETRASO_MATRICULA) {
            throw new IllegalArgumentException("La fecha de matrícula no puede ser posterior a " + MAX_DIAS_RETRASO_MATRICULA + " días.");
        }
        this.fechaMatricula = fechaMatricula;
    }

    public LocalDate getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDate fechaAnulacion) {
        if (fechaAnulacion != null && ChronoUnit.MONTHS.between(fechaMatricula, fechaAnulacion) > MAX_MESES_ANULACION_MATRICULA) {
            throw new IllegalArgumentException("La fecha de anulación no puede ser posterior a " + MAX_MESES_ANULACION_MATRICULA + " meses.");
        }
        this.fechaAnulacion = fechaAnulacion;
    }

    public List<Asignatura> getAsignaturas() {
        List<Asignatura> copia = new ArrayList<>(asignaturas.size());
        for (Asignatura asignatura : asignaturas) {
            copia.add(new Asignatura(asignatura));
        }
        return copia;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        if (asignaturas == null || asignaturas.isEmpty()) {
            throw new IllegalArgumentException("La lista de asignaturas no puede estar vacía.");
        }
        if (asignaturas.size() > MAX_ASIGNATURAS_MATRICULA) {
            throw new IllegalArgumentException("No se pueden matricular más de " + MAX_ASIGNATURAS_MATRICULA + " asignaturas.");
        }
        this.asignaturas = asignaturas;
    }

    public boolean superaMaximoNumeroHorasMatricula() {
        int totalHoras = 0;
        for (Asignatura asignatura : asignaturas) {
            totalHoras += asignatura.getHorasAnuales();
        }
        return totalHoras > MAX_HORAS_MATRICULA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return identificador == matricula.identificador;
    }

    @Override
    public int hashCode() {
        return identificador;
    }

    @Override
    public String toString() {
        return String.format("%04d, %s, %s, %s, %s", identificador, cursoAcademico, fechaMatricula, fechaAnulacion, asignaturasMatricula());
    }

    public String imprimir() {
        return toString();
    }

    public String asignaturasMatricula() {
        StringBuilder sb = new StringBuilder();
        for (Asignatura asignatura : asignaturas) {
            sb.append(asignatura.getNombre()).append(", ");
        }
        return sb.toString().replaceAll(", $", "");
    }
}
