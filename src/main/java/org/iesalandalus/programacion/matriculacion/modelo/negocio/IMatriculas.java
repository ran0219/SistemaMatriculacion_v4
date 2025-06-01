package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.Matriculas;

import java.util.List;

public interface IMatriculas {
    void comenzar();
    void terminar();

    <CursoAcademico> List<Matricula> get();

    List<Asignatura> getAsignaturasMatricula(int idMatricula);

    int getTamano();

    void insertarAsignaturasMatricula(int idMatricula, List<Asignatura> asignaturas);

    void insertar(Matricula matricula);

    Matricula buscar(Matricula matricula);

    boolean borrar(Matricula matricula);

    List<Matricula> get(Alumno alumno);

    List<Matricula> get(CicloFormativo cicloFormativo);

    List<Matricula> get(Matriculas.CursoAcademico cursoAcademico);
}
