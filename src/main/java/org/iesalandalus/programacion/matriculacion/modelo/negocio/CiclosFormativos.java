package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Clase que gestiona una colección de objetos CicloFormativo utilizando un ArrayList.
 * Sustituye el uso de Arrays por ArrayLists, eliminando la necesidad de una capacidad fija.
 */
public class CiclosFormativos {
    private ArrayList<CicloFormativo> listaCiclos;
    // Eliminados: atributos relacionados con capacidad fija

    /**
     * Constructor que inicializa el ArrayList de ciclos formativos.
     */
    public CiclosFormativos() {
        this.listaCiclos = new ArrayList<>();
    }

    /**
     * Añade un ciclo formativo a la colección. Se asegura de no añadir ciclos duplicados (basado en código).
     * @param ciclo El objeto CicloFormativo a añadir.
     * @return true si el ciclo se añadió con éxito (no existía previamente y no es nulo), false en caso contrario.
     */
    public boolean añadirCiclo(CicloFormativo ciclo) {
        if (ciclo == null) {
            System.err.println("Advertencia: No se puede añadir un ciclo formativo nulo.");
            return false;
        }
        if (!listaCiclos.contains(ciclo)) { // 'contains' usa el método equals de CicloFormativo (por código)
            return listaCiclos.add(ciclo);
        }
        return false; // El ciclo ya existe en la lista
    }

    /**
     * Busca un ciclo formativo por su código.
     * @param codigo El código del ciclo formativo a buscar.
     * @return El objeto CicloFormativo si se encuentra, o null si no existe.
     */
    public CicloFormativo buscarCicloPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return null; // Código no válido para buscar
        }
        for (CicloFormativo ciclo : listaCiclos) {
            if (ciclo.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return ciclo;
            }
        }
        return null; // Ciclo formativo no encontrado
    }

    /**
     * Elimina un ciclo formativo de la colección por su código.
     * @param codigo El código del ciclo formativo a eliminar.
     * @return true si el ciclo se eliminó con éxito, false si no se encontró.
     */
    public boolean eliminarCicloPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return false; // Código no válido para eliminar
        }
        return listaCiclos.removeIf(ciclo -> ciclo.getCodigo().equalsIgnoreCase(codigo.trim()));
    }

    /**
     * Obtiene una copia de la lista de todos los ciclos formativos.
     * @return Un nuevo ArrayList que contiene todos los ciclos formativos.
     */
    public ArrayList<CicloFormativo> getListaCiclos() {
        return new ArrayList<>(listaCiclos);
    }

    /**
     * Retorna el número actual de ciclos formativos en la colección.
     * @return El tamaño del ArrayList.
     */
    public int getNumeroCiclos() {
        return listaCiclos.size();
    }

    /**
     * Verifica si la lista de ciclos formativos está vacía.
     * @return true si la lista está vacía, false en caso contrario.
     */
    public boolean estaVacia() {
        return listaCiclos.isEmpty();
    }
}