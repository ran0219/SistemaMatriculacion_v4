package org.iesalandalus.programacion.matriculacion.modelo.negocio.memoria;


import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAsignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.ICiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IMatriculas;

import java.util.List;

public class FuenteDatosMemoria implements IFuenteDatos{

    @Override
    public IAlumnos crearAlumnos() {
        return new Alumnos() {
            @Override
            public List<Alumno> get() {
                return List.of();
            }

            @Override
            public int getTamano() {
                return 0;
            }

            @Override
            public void insertar(Alumno alumno) {

            }

            @Override
            public Alumno buscar(Alumno alumno) {
                return null;
            }

            @Override
            public boolean borrar(Alumno alumno) {
                return false;
            }
        }; // Instancia de la clase Alumnos del paquete memoria
    }

    @Override
    public ICiclosFormativos crearCiclosFormativos() {
        return new CiclosFormativos() {
            @Override
            public CicloFormativo.TiposGrado getGrado(String tipoGrado, String nombre, String siglas, int numeroAlumnos, int duracion) {
                return null;
            }

            @Override
            public List<CicloFormativo> get() {
                return List.of();
            }

            @Override
            public int getTamano() {
                return 0;
            }

            @Override
            public void insertar(CicloFormativo cicloFormativo) {

            }

            @Override
            public boolean borrar(CicloFormativo cicloFormativo) {
                return false;
            }
        }; // Instancia de la clase CiclosFormativos del paquete memoria
    }

    @Override
    public IAsignaturas crearAsignaturas() {
        return new Asignaturas() {
            @Override
            public Curso getCurso(String tipoCurso) {
                return null;
            }

            @Override
            public EspecialidadProfesorado getEspecialidadProfesorado(String especialidad) {
                return null;
            }

            @Override
            public List<Asignatura> get() {
                return List.of();
            }

            @Override
            public int getTamano() {
                return 0;
            }

            @Override
            public void insertar(Asignatura asignatura) {

            }

            @Override
            public Asignatura buscar(Asignatura asignatura) {
                return null;
            }

            @Override
            public boolean borrar(Asignatura asignatura) {
                return false;
            }
        }; // Instancia de la clase Asignaturas del paquete memoria
    }

    @Override
    public IMatriculas crearMatriculas() {
        return new Matriculas(); // Instancia de la clase Matriculas del paquete memoria
    }
}
