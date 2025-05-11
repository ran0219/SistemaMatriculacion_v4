package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        if (controlador != null)
            this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            opcion.ejecutar(controlador);
        } while (opcion != Opcion.SALIR);
    }

    public void terminar() {
        System.out.println("¡Hasta pronto!");
    }

    // Métodos para insertar, buscar, borrar, listar — usando Consola y Controlador
}
