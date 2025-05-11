package org.iesalandalus.programacion.matriculacion.controlador;

import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.vista.Vista;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;

public class Controlador {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        if (modelo == null || vista == null)
            throw new NullPointerException("Modelo o vista no pueden ser nulos.");

        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
    }

    public void comenzar() {
        modelo.comenzar();
        vista.comenzar();
    }

    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }

    public void insertarAlumno(Alumno alumno) {
        modelo.insertar(alumno);
    }

    public Alumno buscarAlumno(Alumno alumno) {
        return modelo.buscar(alumno);
    }

    public void borrarAlumno(Alumno alumno) {
        modelo.borrar(alumno);
    }

    public Alumno[] getAlumnos() {
        return modelo.getAlumnos();
    }

    // Repetir para Asignaturas, CiclosFormativos y Matrículas
}
