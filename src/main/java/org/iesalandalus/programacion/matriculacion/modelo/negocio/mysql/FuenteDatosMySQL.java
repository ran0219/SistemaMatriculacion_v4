package org.iesalandalus.programacion.matriculacion.modelo.negocio.mysql;

import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAlumnos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IAsignaturas;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.ICiclosFormativos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IMatriculas;

public class FuenteDatosMySQL implements IFuenteDatos {

    @Override
    public IAlumnos crearAlumnos() {
        return Alumnos.getInstancia();
    }

    @Override
    public ICiclosFormativos crearCiclosFormativos() {
        return CiclosFormativos.getInstancia();
    }

    @Override
    public IAsignaturas crearAsignaturas() {
        return Asignaturas.getInstancia();
    }

    @Override
    public IMatriculas crearMatriculas() {
        return Matriculas.getInstancia();
    }
}
