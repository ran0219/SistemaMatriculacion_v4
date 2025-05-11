package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;

public class MainApp {

    public static void main(String[] args) {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
    }

    private static void ejecutar(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_ALUMNO:
                Consola.insertarAlumno();
                break;
            case BUSCAR_ALUMNO:
                Consola.buscarAlumno();
                break;
            case BORRAR_ALUMNO:
                Consola.borrarAlumno();
                break;
            case MOSTRAR_ALUMNOS:
                Consola.mostrarAlumnos();
                break;
            case INSERTAR_ASIGNATURA:
                Consola.insertarAsignatura();
                break;
            case BUSCAR_ASIGNATURA:
                Consola.buscarAsignatura();
                break;
            case BORRAR_ASIGNATURA:
                Consola.borrarAsignatura();
                break;
            case MOSTRAR_ASIGNATURAS:
                Consola.mostrarAsignaturas();
                break;
            case INSERTAR_CICLO:
                Consola.insertarCicloFormativo();
                break;
            case BUSCAR_CICLO:
                Consola.buscarCicloFormativo();
                break;
            case BORRAR_CICLO:
                Consola.borrarCicloFormativo();
                break;
            case MOSTRAR_CICLOS:
                Consola.mostrarCiclosFormativos();
                break;
            case INSERTAR_MATRICULA:
                Consola.insertarMatricula();
                break;
            case BUSCAR_MATRICULA:
                Consola.buscarMatricula();
                break;
            case ANULAR_MATRICULA:
                Consola.anularMatricula();
                break;
            case MOSTRAR_MATRICULAS:
                Consola.mostrarMatriculas();
                break;
            case MOSTRAR_MATRICULAS_ALUMNO:
                Consola.mostrarMatriculasPorAlumno();
                break;
            case MOSTRAR_MATRICULAS_CICLO:
                Consola.mostrarMatriculasPorCicloFormativo();
                break;
            case MOSTRAR_MATRICULAS_CURSO:
                Consola.mostrarMatriculasPorCursoAcademico();
                break;
            case SALIR:
                Consola.despedir();
                break;
        }
    }
}
