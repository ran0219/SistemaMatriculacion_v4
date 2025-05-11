package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.negocio.*;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;

public class Modelo {
    private Alumnos alumnos;
    private Asignaturas asignaturas;
    private CiclosFormativos ciclosFormativos;
    private Matriculas matriculas;

    public void comenzar() {
        alumnos = new Alumnos();
        asignaturas = new Asignaturas();
        ciclosFormativos = new CiclosFormativos();
        matriculas = new Matriculas();
        System.out.println("Modelo iniciado.");
    }

    public void terminar() {
        System.out.println("Modelo finalizado.");
    }

    public void insertar(Alumno alumno) {
        alumnos.insertar(new Alumno(alumno));
    }

    public Alumno buscar(Alumno alumno) {
        Alumno encontrado = alumnos.buscar(alumno);
        return (encontrado != null) ? new Alumno(encontrado) : null;
    }

    public void borrar(Alumno alumno) {
        alumnos.borrar(alumno);
    }

    public Alumno[] getAlumnos() {
        Alumno[] copia = alumnos.get();
        for (int i = 0; i < copia.length; i++) {
            copia[i] = new Alumno(copia[i]);
        }
        return copia;
    }

    // Repetir métodos para Asignatura, CicloFormativo y Matricula
}
