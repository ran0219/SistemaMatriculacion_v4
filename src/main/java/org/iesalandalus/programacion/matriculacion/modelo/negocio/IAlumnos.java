package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;

import java.util.List;

public interface IAlumnos {
    void comenzar();
    void terminar();

    List<Alumno> get();

    int getTamano();

    void insertar(Alumno alumno);

    Alumno buscar(Alumno alumno);

    boolean borrar(Alumno alumno);
}
