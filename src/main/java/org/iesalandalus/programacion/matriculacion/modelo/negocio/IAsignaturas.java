package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Curso;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.EspecialidadProfesorado;

import java.util.List;

public interface IAsignaturas {
    void comenzar();
    void terminar();

    Curso getCurso(String tipoCurso);

    EspecialidadProfesorado getEspecialidadProfesorado(String especialidad);

    List<Asignatura> get();

    int getTamano();

    void insertar(Asignatura asignatura);

    Asignatura buscar(Asignatura asignatura);

    boolean borrar(Asignatura asignatura);
}
