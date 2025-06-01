package org.iesalandalus.programacion.matriculacion;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Vista;

import java.time.LocalDate;
import java.util.Objects; // Necesario para Objects.requireNonNull

public class MainApp {

    // Se declara 'modelo' como un campo estático para que pueda ser inicializado
    // por 'procesarArgumentosFuenteDatos' y usado por 'main'.
    private static Modelo modelo;

    /**
     * Procesa los argumentos de la línea de comandos para seleccionar la fuente de datos.
     * Inicializa la instancia del Modelo con la fuente de datos seleccionada.
     * @param args Argumentos de la línea de comandos.
     */
    public static void procesarArgumentosFuenteDatos(String[] args) {
        IFuenteDatos fuenteDatosSeleccionada;

        System.out.println("--- Configurando fuente de datos ---");
        if (args.length > 0) {
            switch (args[0]) {
                case "-fdmemoria":
                    fuenteDatosSeleccionada = FactoriaFuenteDatos.MEMORIA.crearFuenteDatos();
                    System.out.println("Fuente de datos: Memoria.");
                    break;
                case "-fdmysql":
                    fuenteDatosSeleccionada = FactoriaFuenteDatos.MYSQL.crearFuenteDatos();
                    System.out.println("Fuente de datos: MySQL.");
                    break;
                default:
                    System.out.println("Argumento de fuente de datos no reconocido ('" + args[0] + "'). Usando memoria por defecto.");
                    fuenteDatosSeleccionada = FactoriaFuenteDatos.MEMORIA.crearFuenteDatos();
                    break;
            }
        } else {
            System.out.println("No se especificó argumento de fuente de datos. Usando memoria por defecto.");
            fuenteDatosSeleccionada = FactoriaFuenteDatos.MEMORIA.crearFuenteDatos();
        }
        // Aquí se inicializa el campo estático 'modelo'
        modelo = new Modelo(fuenteDatosSeleccionada);
    }

    public static void main(String[] args) {
        // 1. Procesar argumentos y configurar la fuente de datos para el Modelo
        procesarArgumentosFuenteDatos(args);

        // Asegurarse de que el modelo no es nulo después de la configuración
        Objects.requireNonNull(modelo, "ERROR: El modelo no pudo ser inicializado.");

        // 2. Inicialización de las capas de la aplicación
        Consola consola = new Consola();
        Vista vista = new Vista(); // La Vista ya está adaptada para el nuevo modelo de Matricula
        // El Modelo ya está inicializado y configurado por procesarArgumentosFuenteDatos

        // 3. El Controlador orquesta la interacción entre Vista y Modelo.
        Controlador controlador = new Controlador(modelo, vista, consola);

        // 4. Carga opcional de datos de ejemplo
        // Esto es útil para probar la aplicación sin tener que introducir datos manualmente cada vez.
        // NOTA: Se ha adaptado para usar el nuevo modelo de Matricula con lista de asignaturas.
        cargarDatosDeEjemplo(modelo);

        // 5. Inicio de la aplicación
        // El modelo debe comenzar antes de que el controlador inicie la aplicación principal.
        modelo.comenzar(); // Inicia la conexión o carga de datos de la fuente

        controlador.iniciarAplicacion();

        // 6. Cierre de recursos
        // Es importante cerrar el Scanner de la Consola para liberar recursos del sistema.
        consola.cerrarScanner();

        // 7. Gancho de apagado para asegurar el cierre de recursos del modelo
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (modelo != null) {
                modelo.terminar(); // Llama a terminar() en el modelo para cerrar conexiones, etc.
            }
            System.out.println("Aplicación terminada correctamente.");
        }));

        vista.mostrarMensaje("Aplicación finalizada."); // Este mensaje se mostrará después del bucle del controlador
    }

    /**
     * Método auxiliar para cargar algunos datos de ejemplo en el modelo.
     * Esto ayuda a probar las funcionalidades de añadir, buscar, mostrar, etc.
     * sin tener que rellenar todo manualmente cada vez que se ejecuta.
     *
     * @param modelo El objeto Modelo al que se añadirán los datos.
     */
    private static void cargarDatosDeEjemplo(Modelo modelo) {
        System.out.println("\n--- Cargando datos de ejemplo ---");

        try {
            // Alumnos de ejemplo
            Alumno alumno1 = new Alumno("11111111A", "Juan", "Pérez García", LocalDate.of(2000, 1, 15));
            Alumno alumno2 = new Alumno("22222222B", "Ana", "López Fernández", LocalDate.of(2001, 5, 20));
            Alumno alumno3 = new Alumno("33333333C", "Pedro", "Martínez Ruiz", LocalDate.of(1999, 11, 30));
            Alumno alumno4 = new Alumno("44444444D", "María", "Gómez Salas", LocalDate.of(2002, 3, 8));

            modelo.añadirAlumno(alumno1);
            modelo.añadirAlumno(alumno2);
            modelo.añadirAlumno(alumno3);
            modelo.añadirAlumno(alumno4);

            // Asignaturas de ejemplo
            Asignatura prog = new Asignatura("PROG", "Programación", 6);
            Asignatura bd = new Asignatura("BD", "Bases de Datos", 5);
            Asignatura lm = new Asignatura("LM", "Lenguaje de Marcas", 4);
            Asignatura si = new Asignatura("SI", "Sistemas Informáticos", 6);

            modelo.añadirAsignatura(prog);
            modelo.añadirAsignatura(bd);
            modelo.añadirAsignatura(lm);
            modelo.añadirAsignatura(si);

            // Ciclos Formativos de ejemplo
            CicloFormativo dam = new CicloFormativo("DAM", "Desarrollo de Aplicaciones Multiplataforma", "Grado Superior");
            CicloFormativo daw = new CicloFormativo("DAW", "Desarrollo de Aplicaciones Web", "Grado Superior");
            CicloFormativo asir = new CicloFormativo("ASIR", "Administración de Sistemas Informáticos en Red", "Grado Superior");

            modelo.añadirCicloFormativo(dam);
            modelo.añadirCicloFormativo(daw);
            modelo.añadirCicloFormativo(asir);

            // --- Matrículas de ejemplo (ADAPTADO AL NUEVO MODELO DE MATRICULA) ---
            // El modelo de Matricula es general por ciclo/curso y tiene una lista de asignaturas.
            // Los IDs de matrícula son enteros.

            // Matricula 1: Juan Pérez (DAM, Curso 2024), Asignaturas: PROG, BD, LM
            Matricula mat1 = new Matricula(1, LocalDate.of(2024, 9, 1), alumno1, dam, 2024);
            modelo.añadirMatricula(mat1); // Asumo que modelo.añadirMatricula toma un objeto Matricula
            // Ahora, añade las asignaturas a esta matrícula (asumo un método en Modelo para esto)
            // IMPORTANTE: Necesitarás implementar estos métodos en tu clase Modelo
            modelo.añadirAsignaturaAMatricula(mat1, prog); // Nuevo método en Modelo
            modelo.añadirAsignaturaAMatricula(mat1, bd);   // Nuevo método en Modelo
            modelo.añadirAsignaturaAMatricula(mat1, lm);   // Nuevo método en Modelo
            // Opcional: poner nota a la matrícula general
            mat1.setNota(7.5);


            // Matricula 2: Ana López (DAW, Curso 2024), Asignaturas: PROG, SI
            Matricula mat2 = new Matricula(2, LocalDate.of(2024, 9, 5), alumno2, daw, 2024, 8.0);
            modelo.añadirMatricula(mat2);
            modelo.añadirAsignaturaAMatricula(mat2, prog);
            modelo.añadirAsignaturaAMatricula(mat2, si);


            // Matricula 3: Pedro Martínez (ASIR, Curso 2024), Asignaturas: LM
            Matricula mat3 = new Matricula(3, LocalDate.of(2024, 9, 10), alumno3, asir, 2024);
            modelo.añadirMatricula(mat3);
            modelo.añadirAsignaturaAMatricula(mat3, lm);


            // Matricula 4: María Gómez (DAM, Curso 2024), Asignaturas: PROG, BD
            Matricula mat4 = new Matricula(4, LocalDate.of(2024, 9, 12), alumno4, dam, 2024, 6.0);
            modelo.añadirMatricula(mat4);
            modelo.añadirAsignaturaAMatricula(mat4, prog);
            modelo.añadirAsignaturaAMatricula(mat4, bd);

            System.out.println("--- Datos de ejemplo cargados correctamente ---");

        } catch (Exception e) {
            System.err.println("ERROR al cargar datos de ejemplo: " + e.getMessage());
            // Puedes imprimir la pila de la excepción para depuración: e.printStackTrace();
        }
    }
}