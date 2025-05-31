package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Clase que gestiona una colección de objetos Matricula utilizando un ArrayList.
 * Sustituye el uso de Arrays por ArrayLists, eliminando la necesidad de una capacidad fija.
 */
public class Matriculas {
    private ArrayList<Matricula> listaMatriculas;
    // Eliminados: atributos relacionados con capacidad fija

    /**
     * Constructor que inicializa el ArrayList de matrículas.
     */
    public Matriculas() {
        this.listaMatriculas = new ArrayList<>();
    }

    /**
     * Añade una matrícula a la colección. Se asegura de no añadir matrículas duplicadas (basado en ID).
     * @param matricula El objeto Matricula a añadir.
     * @return true si la matrícula se añadió con éxito (no existía previamente y no es nula), false en caso contrario.
     */
    public boolean añadirMatricula(Matricula matricula) {
        if (matricula == null) {
            System.err.println("Advertencia: No se puede añadir una matrícula nula.");
            return false;
        }
        if (!listaMatriculas.contains(matricula)) { // 'contains' usa el método equals de Matricula (por ID)
            return listaMatriculas.add(matricula);
        }
        return false; // La matrícula ya existe en la lista
    }

    /**
     * Busca una matrícula por su ID.
     * @param idMatricula El ID de la matrícula a buscar.
     * @return El objeto Matricula si se encuentra, o null si no existe.
     */
    public Matricula buscarMatriculaPorId(String idMatricula) {
        if (idMatricula == null || idMatricula.trim().isEmpty()) {
            return null; // ID no válido para buscar
        }
        for (Matricula matricula : listaMatriculas) {
            if (matricula.getIdMatricula().equalsIgnoreCase(idMatricula.trim())) {
                return matricula;
            }
        }
        return null; // Matrícula no encontrada
    }

    /**
     * Elimina una matrícula de la colección por su ID.
     * @param idMatricula El ID de la matrícula a eliminar.
     * @return true si la matrícula se eliminó con éxito, false si no se encontró.
     */
    public boolean eliminarMatriculaPorId(String idMatricula) {
        if (idMatricula == null || idMatricula.trim().isEmpty()) {
            return false; // ID no válido para eliminar
        }
        return listaMatriculas.removeIf(matricula -> matricula.getIdMatricula().equalsIgnoreCase(idMatricula.trim()));
    }

    /**
     * Obtiene una copia de la lista de todas las matrículas.
     * @return Un nuevo ArrayList que contiene todas las matrículas.
     */
    public ArrayList<Matricula> getListaMatriculas() {
        return new ArrayList<>(listaMatriculas);
    }

    /**
     * Retorna el número actual de matrículas en la colección.
     * @return El tamaño del ArrayList.
     */
    public int getNumeroMatriculas() {
        return listaMatriculas.size();
    }

    /**
     * Verifica si la lista de matrículas está vacía.
     * @return true si la lista está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return listaMatriculas.isEmpty();
    }

    /**
     * Obtiene una lista de matrículas asociadas a un alumno específico.
     * @param dniAlumno El DNI del alumno cuyas matrículas se desean obtener.
     * @return Un ArrayList de Matricula que pertenecen al alumno dado.
     */
    public ArrayList<Matricula> getMatriculasPorAlumno(String dniAlumno) {
        ArrayList<Matricula> matriculasAlumno = new ArrayList<>();
        if (dniAlumno == null || dniAlumno.trim().isEmpty()) {
            return matriculasAlumno; // DNI no válido, devuelve lista vacía
        }
        for (Matricula matricula : listaMatriculas) {
            if (matricula.getAlumno() != null && matricula.getAlumno().getDni().equalsIgnoreCase(dniAlumno.trim())) {
                matriculasAlumno.add(matricula);
            }
        }
        return matriculasAlumno;
    }

    /**
     * Obtiene una lista de matrículas asociadas a una asignatura específica.
     * @param codigoAsignatura El código de la asignatura cuyas matrículas se desean obtener.
     * @return Un ArrayList de Matricula que pertenecen a la asignatura dada.
     */
    public ArrayList<Matricula> getMatriculasPorAsignatura(String codigoAsignatura) {
        ArrayList<Matricula> matriculasAsignatura = new ArrayList<>();
        if (codigoAsignatura == null || codigoAsignatura.trim().isEmpty()) {
            return matriculasAsignatura; // Código no válido, devuelve lista vacía
        }
        for (Matricula matricula : listaMatriculas) {
            if (matricula.getAsignatura() != null && matricula.getAsignatura().getCodigo().equalsIgnoreCase(codigoAsignatura.trim())) {
                matriculasAsignatura.add(matricula);
            }
        }
        return matriculasAsignatura;
    }
}