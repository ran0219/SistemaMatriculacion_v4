package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import java.util.Arrays;

public class Matriculas {

    private Matricula[] matriculas;
    private int cantidadMatriculas;
    private Object[] coleccion;

    public Matriculas() {
        coleccion = new Object[100];
    }

    public Matriculas(int capacidad) {
        this.matriculas = new Matricula[capacidad];
        this.cantidadMatriculas = 0;
    }

    public Matricula[] get() {
        return copiaProfundaMatriculas();
    }

    private Matricula[] copiaProfundaMatriculas() {
        Matricula[] copia = new Matricula[cantidadMatriculas];
        for (int i = 0; i < cantidadMatriculas; i++) {
            copia[i] = new Matricula(matriculas[i]);
        }
        return copia;
    }

    public void insertar(Matricula matricula) {
        if (matricula != null && !capacidadSuperada() && buscar(matricula.getIdentificador()) == null) {
            matriculas[cantidadMatriculas] = matricula;
            cantidadMatriculas++;
        }
    }

    public Matricula buscar(int identificador) {
        for (int i = 0; i < cantidadMatriculas; i++) {
            if (matriculas[i].getIdentificador() == identificador) {
                return matriculas[i];
            }
        }
        return null;
    }

    public void borrar(int identificador) {
        int indice = buscarIndice(identificador);
        if (indice != -1) {
            desplazarUnaPosicionHaciaIzquierda(indice);
            matriculas[cantidadMatriculas - 1] = null;
            cantidadMatriculas--;
        }
    }

    private int buscarIndice(int identificador) {
        for (int i = 0; i < cantidadMatriculas; i++) {
            if (matriculas[i].getIdentificador() == identificador) {
                return i;
            }
        }
        return -1;
    }

    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for (int i = indice; i < cantidadMatriculas - 1; i++) {
            matriculas[i] = matriculas[i + 1];
        }
    }

    public boolean capacidadSuperada() {
        return cantidadMatriculas == matriculas.length;
    }

    public int getCantidadMatriculas() {
        return cantidadMatriculas;
    }

    public int getTamaño() {
        return matriculas.length;
    }

    public Matricula[] get(Alumno alumno) {
        Matricula[] resultado = new Matricula[cantidadMatriculas];
        int cantidadResultados = 0;
        for (int i = 0; i < cantidadMatriculas; i++) {
            for (Asignatura asignatura : matriculas[i].getAsignaturas()) {
                if (asignatura.getCicloFormativo().equals(alumno)) {
                    resultado[cantidadResultados] = matriculas[i];
                    cantidadResultados++;
                    break;
                }
            }
        }
        return Arrays.copyOf(resultado, cantidadResultados);
    }

    public Matricula[] get(CicloFormativo cicloFormativo) {
        Matricula[] resultado = new Matricula[cantidadMatriculas];
        int cantidadResultados = 0;
        for (int i = 0; i < cantidadMatriculas; i++) {
            for (Asignatura asignatura : matriculas[i].getAsignaturas()) {
                if (asignatura.getCicloFormativo().equals(cicloFormativo)) {
                    resultado[cantidadResultados] = matriculas[i];
                    cantidadResultados++;
                    break;
                }
            }
        }
        return Arrays.copyOf(resultado, cantidadResultados);
    }

    public Matricula[] get(String cursoAcademico) {
        Matricula[] resultado = new Matricula[cantidadMatriculas];
        int cantidadResultados = 0;
        for (int i = 0; i < cantidadMatriculas; i++) {
            if (matriculas[i].getCursoAcademico().equals(cursoAcademico)) {
                resultado[cantidadResultados] = matriculas[i];
                cantidadResultados++;
            }
        }
        return Arrays.copyOf(resultado, cantidadResultados);
    }
}
