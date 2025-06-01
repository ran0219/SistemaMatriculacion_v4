package org.iesalandalus.programacion.matriculacion.modelo.negocio;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.TiposGrado;

import java.util.List;

public interface ICiclosFormativos {
    void comenzar();
    void terminar();

    // Adaptar este método para que cree los objetos GradoD/GradoE
    // basándose en el tipo de grado almacenado en la BD.
    // Asumo que en la BD hay una columna 'tipoGrado' (e.g., "GradoD", "GradoE")
    CicloFormativo.TiposGrado getGrado(String tipoGrado, String nombre, String siglas, int numeroAlumnos, int duracion);

    List<CicloFormativo> get();

    int getTamano();

    void insertar(CicloFormativo cicloFormativo);

    boolean borrar(CicloFormativo cicloFormativo);
}
