package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Clase que gestiona una colección de objetos Asignatura utilizando un ArrayList.
 * Sustituye el uso de Arrays por ArrayLists, eliminando la necesidad de una capacidad fija.
 */
public class Asignaturas {
    private ArrayList<Asignatura> listaAsignaturas;
    // Eliminados: atributos relacionados con capacidad fija

    /**
     * Constructor que inicializa el ArrayList de asignaturas.
     */
    public Asignaturas() {
        this.listaAsignaturas = new ArrayList<>();
    }

    /**
     * Añade una asignatura a la colección. Se asegura de no añadir asignaturas duplicadas (basado en código).
     * @param asignatura El objeto Asignatura a añadir.
     * @return true si la asignatura se añadió con éxito (no existía previamente y no es nula), false en caso contrario.
     */
    public boolean añadirAsignatura(Asignatura asignatura) {
        if (asignatura == null) {
            System.err.println("Advertencia: No se puede añadir una asignatura nula.");
            return false;
        }
        if (!listaAsignaturas.contains(asignatura)) { // 'contains' usa el método equals de Asignatura (por código)
            return listaAsignaturas.add(asignatura);
        }
        return false; // La asignatura ya existe en la lista
    }

    /**
     * Busca una asignatura por su código.
     * @param codigo El código de la asignatura a buscar.
     * @return El objeto Asignatura si se encuentra, o null si no existe.
     */
    public Asignatura buscarAsignaturaPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null; // Código no válido para buscar
        }
        for (Asignatura asignatura : listaAsignaturas) {
            if (asignatura.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return asignatura;
            }
        }
        return null; // Asignatura no encontrada
    }

    /**
     * Elimina una asignatura de la colección por su código.
     * @param codigo El código de la asignatura a eliminar.
     * @return true si la asignatura se eliminó con éxito, false si no se encontró.
     */
    public boolean eliminarAsignaturaPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return false; // Código no válido para eliminar
        }
        return listaAsignaturas.removeIf(asignatura -> asignatura.getCodigo().equalsIgnoreCase(codigo.trim()));
    }

    /**
     * Obtiene una copia de la lista de todas las asignaturas.
     * @return Un nuevo ArrayList que contiene todas las asignaturas.
     */
    public ArrayList<Asignatura> getListaAsignaturas() {
        return new ArrayList<>(listaAsignaturas);
    }

    /**
     * Retorna el número actual de asignaturas en la colección.
     * @return El tamaño del ArrayList.
     */
    public int getNumeroAsignaturas() {
        return listaAsignaturas.size();
    }

    /**
     * Verifica si la lista de asignaturas está vacía.
     * @return true si la lista está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return listaAsignaturas.isEmpty();
    }
}