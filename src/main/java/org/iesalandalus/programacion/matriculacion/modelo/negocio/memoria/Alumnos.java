package org.iesalandalus.programacion.matriculacion.modelo.negocio.memoria;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql.utilidades.MySQL;

import java.util.ArrayList;

/**
 * Clase que gestiona una colección de objetos Alumno utilizando un ArrayList.
 * Sustituye el uso de Arrays por ArrayLists, eliminando la necesidad de una capacidad fija.
 */
public abstract class Alumnos implements IAlumnos {
    private ArrayList<Alumno> listaAlumnos;
    // Eliminados: private static final int CAPACIDAD;
    // Eliminados: private int numAlumnos;

    /**
     * Constructor que inicializa el ArrayList de alumnos.
     */
    public Alumnos() {
        this.listaAlumnos = new ArrayList<>();
    }

    /**
     * Añade un alumno a la colección. Se asegura de no añadir alumnos duplicados (basado en DNI).
     * @param alumno El objeto Alumno a añadir.
     * @return true si el alumno se añadió con éxito (no existía previamente y no es nulo), false en caso contrario.
     */
    public boolean añadirAlumno(Alumno alumno) {
        if (alumno == null) {
            System.err.println("Advertencia: No se puede añadir un alumno nulo.");
            return false;
        }
        if (!listaAlumnos.contains(alumno)) { // 'contains' usa el método equals de Alumno (por DNI)
            return listaAlumnos.add(alumno);
        }
        return false; // El alumno ya existe en la lista
    }

    /**
     * Busca un alumno por su DNI.
     * @param dni El DNI del alumno a buscar.
     * @return El objeto Alumno si se encuentra, o null si no existe.
     */
    public Alumno buscarAlumnoPorDni(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            return null; // DNI no válido para buscar
        }
        for (Alumno alumno : listaAlumnos) {
            if (alumno.getDni().equalsIgnoreCase(dni.trim())) {
                return alumno;
            }
        }
        return null; // Alumno no encontrado
    }

    /**
     * Elimina un alumno de la colección por su DNI.
     * @param dni El DNI del alumno a eliminar.
     * @return true si el alumno se eliminó con éxito, false si no se encontró.
     */
    public boolean eliminarAlumnoPorDni(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            return false; // DNI no válido para eliminar
        }
        // removeIf usa el método equals de Alumno o un predicado
        return listaAlumnos.removeIf(alumno -> alumno.getDni().equalsIgnoreCase(dni.trim()));
    }

    /**
     * Obtiene una copia de la lista de todos los alumnos.
     * Devolver una copia es una buena práctica para proteger la lista interna de modificaciones externas directas.
     * @return Un nuevo ArrayList que contiene todos los alumnos.
     */
    public ArrayList<Alumno> getListaAlumnos() {
        return new ArrayList<>(listaAlumnos);
    }

    /**
     * Retorna el número actual de alumnos en la colección.
     * @return El tamaño del ArrayList.
     */
    public int getNumeroAlumnos() {
        return listaAlumnos.size();
    }

    /**
     * Verifica si la lista de alumnos está vacía.
     * @return true si la lista está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return listaAlumnos.isEmpty();
    }

    @Override
    public void comenzar() {
        MySQL.establecerConexion(); // Necesitarás la clase MySQL
    }

    @Override
    public void terminar() {
        MySQL.cerrarConexion(); // Necesitarás la clase MySQL
    }
}