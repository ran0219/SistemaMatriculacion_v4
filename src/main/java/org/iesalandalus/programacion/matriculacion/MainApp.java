package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Vista;


import java.time.LocalDate; // Necesario para los datos de ejemplo

public class MainApp {
    public static void main(String[] args) {
        // 1. Inicialización de las capas de la aplicación
        // La Consola se encarga de la entrada/salida de datos y su validación.
        Consola consola = new Consola();
        // La Vista se encarga de mostrar la información al usuario.
        Vista vista = new Vista();
        // El Modelo gestiona los datos y la lógica de negocio.
        Modelo modelo = new Modelo();
        // El Controlador orquesta la interacción entre Vista y Modelo.
        Controlador controlador = new Controlador(modelo, vista, consola);

        // 2. Carga opcional de datos de ejemplo
        // Esto es útil para probar la aplicación sin tener que introducir datos manualmente cada vez.
        cargarDatosDeEjemplo(modelo);

        // 3. Inicio de la aplicación
        // El controlador toma el control para iniciar el bucle principal de la aplicación.
        controlador.iniciarAplicacion();

        // 4. Cierre de recursos
        // Es importante cerrar el Scanner de la Consola para liberar recursos del sistema.
        consola.cerrarScanner();

        vista.mostrarMensaje("Aplicación finalizada.");
    }

    /**
     * Método auxiliar para cargar algunos datos de ejemplo en el modelo.
     * Esto ayuda a probar las funcionalidades de añadir, buscar, mostrar, etc.
     * sin tener que rellenar todo manualmente cada vez que se ejecuta.
     * @param modelo El objeto Modelo al que se añadirán los datos.
     */
    private static void cargarDatosDeEjemplo(Modelo modelo) {
        System.out.println("\n--- Cargando datos de ejemplo ---");

        // Alumnos de ejemplo
        modelo.añadirAlumno(new Alumno("11111111A", "Juan", "Pérez García", LocalDate.of(2000, 1, 15)));
        modelo.añadirAlumno(new Alumno("22222222B", "Ana", "López Fernández", LocalDate.of(2001, 5, 20)));
        modelo.añadirAlumno(new Alumno("33333333C", "Pedro", "Martínez Ruiz", LocalDate.of(1999, 11, 30)));
        modelo.añadirAlumno(new Alumno("44444444D", "María", "Gómez Salas", LocalDate.of(2002, 3, 8)));

        // Asignaturas de ejemplo
        modelo.añadirAsignatura(new Asignatura("PROG", "Programación", 6));
        modelo.añadirAsignatura(new Asignatura("BD", "Bases de Datos", 5));
        modelo.añadirAsignatura(new Asignatura("LM", "Lenguaje de Marcas", 4));
        modelo.añadirAsignatura(new Asignatura("SI", "Sistemas Informáticos", 6));

        // Ciclos Formativos de ejemplo
        modelo.añadirCicloFormativo(new CicloFormativo("DAM", "Desarrollo de Aplicaciones Multiplataforma", "Grado Superior"));
        modelo.añadirCicloFormativo(new CicloFormativo("DAW", "Desarrollo de Aplicaciones Web", "Grado Superior"));
        modelo.añadirCicloFormativo(new CicloFormativo("ASIR", "Administración de Sistemas Informáticos en Red", "Grado Superior"));

        // Matrículas de ejemplo
        modelo.añadirMatricula("MAT001", "11111111A", "PROG", LocalDate.of(2024, 9, 1));
        modelo.añadirMatricula("MAT002", "11111111A", "BD", LocalDate.of(2024, 9, 1), 7.5);
        modelo.añadirMatricula("MAT003", "22222222B", "PROG", LocalDate.of(2024, 9, 5), 8.0);
        modelo.añadirMatricula("MAT004", "33333333C", "LM", LocalDate.of(2024, 9, 10));
        modelo.añadirMatricula("MAT005", "22222222B", "BD", LocalDate.of(2024, 9, 5));
        modelo.añadirMatricula("MAT006", "44444444D", "PROG", LocalDate.of(2024, 9, 12), 6.0);
        modelo.añadirMatricula("MAT007", "11111111A", "LM", LocalDate.of(2024, 9, 1), 9.2);


        System.out.println("--- Datos de ejemplo cargados ---");
    }
}