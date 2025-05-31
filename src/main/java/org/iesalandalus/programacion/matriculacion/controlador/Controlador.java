package org.iesalandalus.programacion.matriculacion.controlador;

import org.iesalandalus.programacion.matriculacion.modelo.Modelo; // Importa tu clase Modelo
import org.iesalandalus.programacion.matriculacion.vista.Vista;   // Importa tu clase Vista
import org.iesalandalus.programacion.matriculacion.vista.Consola; // Importa tu clase Consola

// Importaciones de las clases de dominio, ajusta el paquete si es necesario
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.modelo.negocio.Matriculas;

import java.time.LocalDate; // Para manejar fechas
import java.util.ArrayList; // Para manejar listas

public class Controlador {
    private Modelo modelo;
    private Vista vista;
    private Consola consola;

    // Constructor que recibe las instancias de Modelo, Vista y Consola
    public Controlador(Modelo modelo, Vista vista, Consola consola) {
        // Validaciones básicas para asegurar que las dependencias no son nulas
        if (modelo == null) {
            throw new IllegalArgumentException("ERROR: El modelo no puede ser nulo.");
        }
        if (vista == null) {
            throw new IllegalArgumentException("ERROR: La vista no puede ser nula.");
        }
        if (consola == null) {
            throw new IllegalArgumentException("ERROR: La consola no puede ser nula.");
        }
        this.modelo = modelo;
        this.vista = vista;
        this.consola = consola;
    }

    // Método principal para iniciar la aplicación y mostrar el menú
    public void iniciarAplicacion() {
        int opcion;
        do {
            vista.mostrarMenuPrincipal(); // Muestra el menú principal
            opcion = consola.leerEntero("Seleccione una opción: "); // Lee la opción del usuario
            ejecutarOpcion(opcion); // Ejecuta la opción seleccionada
        } while (opcion != 0); // El bucle continúa hasta que el usuario selecciona la opción de salir (0)
    }

    // Método para ejecutar la opción seleccionada del menú principal
    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                gestionarAlumnos();
                break;
            case 2:
                gestionarAsignaturas();
                break;
            case 3:
                gestionarCiclosFormativos();
                break;
            case 4:
                gestionarMatriculas();
                break;
            case 0:
                vista.mostrarMensaje("Saliendo de la aplicación..."); // Mensaje de despedida
                break;
            default:
                vista.mostrarMensaje("Opción no válida. Por favor, intente de nuevo."); // Opción inválida
        }
    }

    // --- Gestión de Alumnos ---

    private void gestionarAlumnos() {
        int opcion;
        do {
            vista.mostrarMenuAlumnos(); // Muestra el menú de alumnos
            opcion = consola.leerEntero("Seleccione una opción para Alumnos: ");
            ejecutarOpcionAlumnos(opcion);
        } while (opcion != 0);
    }

    private void ejecutarOpcionAlumnos(int opcion) {
        switch (opcion) {
            case 1:
                añadirAlumno();
                break;
            case 2:
                buscarAlumno();
                break;
            case 3:
                eliminarAlumno();
                break;
            case 4:
                listarAlumnos();
                break;
            case 0:
                vista.mostrarMensaje("Volviendo al menú principal.");
                break;
            default:
                vista.mostrarMensaje("Opción no válida para Alumnos. Intente de nuevo.");
        }
    }

    private void añadirAlumno() {
        vista.mostrarMensaje("--- Añadir Alumno ---");
        try {
            String dni = consola.leerCadena("Introduzca DNI del alumno: ");
            // Se usa buscarAlumno (sin 'PorDni') como se definió en Modelo
            if (modelo.buscarAlumno(dni) != null) {
                vista.mostrarMensaje("Error: Ya existe un alumno con ese DNI.");
                return;
            }

            String nombre = consola.leerCadena("Introduzca nombre del alumno: ");
            String apellidos = consola.leerCadena("Introduzca apellidos del alumno: ");
            LocalDate fechaNacimiento = consola.leerFecha("Introduzca fecha de nacimiento (AAAA-MM-DD): ");

            Alumno nuevoAlumno = new Alumno(dni, nombre, apellidos, fechaNacimiento);

            if (modelo.añadirAlumno(nuevoAlumno)) {
                vista.mostrarMensaje("Alumno añadido correctamente.");
            } else {
                vista.mostrarMensaje("Error: No se pudo añadir el alumno.");
            }
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje("Error al añadir alumno: " + e.getMessage());
        }
    }

    private void buscarAlumno() {
        vista.mostrarMensaje("--- Buscar Alumno ---");
        String dni = consola.leerCadena("Introduzca DNI del alumno a buscar: ");
        // Se usa buscarAlumno (sin 'PorDni') como se definió en Modelo
        Alumno alumno = modelo.buscarAlumno(dni);
        if (alumno != null) {
            vista.mostrarAlumno(alumno); // Mostrar un único alumno
        } else {
            vista.mostrarMensaje("Alumno no encontrado.");
        }
    }

    private void eliminarAlumno() {
        vista.mostrarMensaje("--- Eliminar Alumno ---");
        String dni = consola.leerCadena("Introduzca DNI del alumno a eliminar: ");
        // Se usa eliminarAlumno (sin 'PorDni') como se definió en Modelo
        if (modelo.eliminarAlumno(dni)) {
            vista.mostrarMensaje("Alumno eliminado correctamente.");
        } else {
            vista.mostrarMensaje("Error: No se pudo eliminar el alumno. DNI no encontrado o no existe.");
        }
    }

    private void listarAlumnos() {
        vista.mostrarMensaje("--- Listado de Alumnos ---");
        // Se usa getTodosLosAlumnos (sin 'Lista') como se definió en Modelo
        ArrayList<Alumno> alumnos = modelo.getTodosLosAlumnos();
        if (alumnos.isEmpty()) {
            vista.mostrarMensaje("No hay alumnos registrados.");
        } else {
            vista.mostrarAlumnos(alumnos); // Este método de Vista debe ordenar si es necesario
        }
    }

    // --- Gestión de Asignaturas ---

    private void gestionarAsignaturas() {
        int opcion;
        do {
            vista.mostrarMenuAsignaturas();
            opcion = consola.leerEntero("Seleccione una opción para Asignaturas: ");
            ejecutarOpcionAsignaturas(opcion);
        } while (opcion != 0);
    }

    private void ejecutarOpcionAsignaturas(int opcion) {
        switch (opcion) {
            case 1:
                añadirAsignatura();
                break;
            case 2:
                buscarAsignatura();
                break;
            case 3:
                eliminarAsignatura();
                break;
            case 4:
                listarAsignaturas();
                break;
            case 0:
                vista.mostrarMensaje("Volviendo al menú principal.");
                break;
            default:
                vista.mostrarMensaje("Opción no válida para Asignaturas. Intente de nuevo.");
        }
    }

    private void añadirAsignatura() {
        vista.mostrarMensaje("--- Añadir Asignatura ---");
        try {
            String codigo = consola.leerCadena("Introduzca código de la asignatura: ");
            // Se usa buscarAsignatura (sin 'PorCodigo') como se definió en Modelo
            if (modelo.buscarAsignatura(codigo) != null) {
                vista.mostrarMensaje("Error: Ya existe una asignatura con ese código.");
                return;
            }

            String nombre = consola.leerCadena("Introduzca nombre de la asignatura: ");
            int creditos = consola.leerEntero("Introduzca créditos de la asignatura: ");

            Asignatura nuevaAsignatura = new Asignatura(codigo, nombre, creditos);

            if (modelo.añadirAsignatura(nuevaAsignatura)) {
                vista.mostrarMensaje("Asignatura añadida correctamente.");
            } else {
                vista.mostrarMensaje("Error: No se pudo añadir la asignatura.");
            }
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje("Error al añadir asignatura: " + e.getMessage());
        }
    }

    private void buscarAsignatura() {
        vista.mostrarMensaje("--- Buscar Asignatura ---");
        String codigo = consola.leerCadena("Introduzca código de la asignatura a buscar: ");
        // Se usa buscarAsignatura (sin 'PorCodigo') como se definió en Modelo
        Asignatura asignatura = modelo.buscarAsignatura(codigo);
        if (asignatura != null) {
            vista.mostrarAsignatura(asignatura); // Mostrar una única asignatura
        } else {
            vista.mostrarMensaje("Asignatura no encontrada.");
        }
    }

    private void eliminarAsignatura() {
        vista.mostrarMensaje("--- Eliminar Asignatura ---");
        String codigo = consola.leerCadena("Introduzca código de la asignatura a eliminar: ");
        // Se usa eliminarAsignatura (sin 'PorCodigo') como se definió en Modelo
        if (modelo.eliminarAsignatura(codigo)) {
            vista.mostrarMensaje("Asignatura eliminada correctamente.");
        } else {
            vista.mostrarMensaje("Error: No se pudo eliminar la asignatura. Código no encontrado o no existe.");
        }
    }

    private void listarAsignaturas() {
        vista.mostrarMensaje("--- Listado de Asignaturas ---");
        // Se usa getTodasLasAsignaturas (sin 'Lista') como se definió en Modelo
        ArrayList<Asignatura> asignaturas = modelo.getTodasLasAsignaturas();
        if (asignaturas.isEmpty()) {
            vista.mostrarMensaje("No hay asignaturas registradas.");
        } else {
            vista.mostrarAsignaturas(asignaturas); // Este método de Vista debe ordenar si es necesario
        }
    }

    // --- Gestión de Ciclos Formativos ---

    private void gestionarCiclosFormativos() {
        int opcion;
        do {
            vista.mostrarMenuCiclosFormativos();
            opcion = consola.leerEntero("Seleccione una opción para Ciclos Formativos: ");
            ejecutarOpcionCiclosFormativos(opcion);
        } while (opcion != 0);
    }

    private void ejecutarOpcionCiclosFormativos(int opcion) {
        switch (opcion) {
            case 1:
                añadirCicloFormativo();
                break;
            case 2:
                buscarCicloFormativo();
                break;
            case 3:
                eliminarCicloFormativo();
                break;
            case 4:
                listarCiclosFormativos();
                break;
            case 0:
                vista.mostrarMensaje("Volviendo al menú principal.");
                break;
            default:
                vista.mostrarMensaje("Opción no válida para Ciclos Formativos. Intente de nuevo.");
        }
    }

    private void añadirCicloFormativo() {
        vista.mostrarMensaje("--- Añadir Ciclo Formativo ---");
        try {
            String codigo = consola.leerCadena("Introduzca código del ciclo formativo: ");
            // Se usa buscarCicloFormativo (sin 'PorCodigo') como se definió en Modelo
            if (modelo.buscarCicloFormativo(codigo) != null) {
                vista.mostrarMensaje("Error: Ya existe un ciclo formativo con ese código.");
                return;
            }

            String nombre = consola.leerCadena("Introduzca nombre del ciclo formativo: ");
            String tipo = consola.leerCadena("Introduzca tipo del ciclo formativo (e.g., Grado Superior): ");

            CicloFormativo nuevoCiclo = new CicloFormativo(codigo, nombre, tipo);

            // Se usa añadirCicloFormativo (sin 'añadirCiclo') como se definió en Modelo
            if (modelo.añadirCicloFormativo(nuevoCiclo)) {
                vista.mostrarMensaje("Ciclo Formativo añadido correctamente.");
            } else {
                vista.mostrarMensaje("Error: No se pudo añadir el ciclo formativo.");
            }
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje("Error al añadir ciclo formativo: " + e.getMessage());
        }
    }

    private void buscarCicloFormativo() {
        vista.mostrarMensaje("--- Buscar Ciclo Formativo ---");
        String codigo = consola.leerCadena("Introduzca código del ciclo formativo a buscar: ");
        // Se usa buscarCicloFormativo (sin 'PorCodigo') como se definió en Modelo
        CicloFormativo ciclo = modelo.buscarCicloFormativo(codigo);
        if (ciclo != null) {
            vista.mostrarCicloFormativo(ciclo); // Mostrar un único ciclo formativo
        } else {
            vista.mostrarMensaje("Ciclo Formativo no encontrado.");
        }
    }

    private void eliminarCicloFormativo() {
        vista.mostrarMensaje("--- Eliminar Ciclo Formativo ---");
        String codigo = consola.leerCadena("Introduzca código del ciclo formativo a eliminar: ");
        // Se usa eliminarCicloFormativo (sin 'PorCodigo') como se definió en Modelo
        if (modelo.eliminarCicloFormativo(codigo)) {
            vista.mostrarMensaje("Ciclo Formativo eliminado correctamente.");
        } else {
            vista.mostrarMensaje("Error: No se pudo eliminar el ciclo formativo. Código no encontrado o no existe.");
        }
    }

    private void listarCiclosFormativos() {
        vista.mostrarMensaje("--- Listado de Ciclos Formativos ---");
        // Se usa getTodosLosCiclosFormativos (sin 'Lista') como se definió en Modelo
        ArrayList<CicloFormativo> ciclos = modelo.getTodosLosCiclosFormativos();
        if (ciclos.isEmpty()) {
            vista.mostrarMensaje("No hay ciclos formativos registrados.");
        } else {
            vista.mostrarCiclosFormativos(ciclos); // Este método de Vista debe ordenar si es necesario
        }
    }

    // --- Gestión de Matrículas ---

    private void gestionarMatriculas() {
        int opcion;
        do {
            vista.mostrarMenuMatriculas();
            opcion = consola.leerEntero("Seleccione una opción para Matrículas: ");
            ejecutarOpcionMatriculas(opcion);
        } while (opcion != 0);
    }

    private void ejecutarOpcionMatriculas(int opcion) {
        switch (opcion) {
            case 1:
                añadirMatricula();
                break;
            case 2:
                buscarMatricula();
                break;
            case 3:
                eliminarMatricula();
                break;
            case 4:
                listarMatriculasTodas();
                break;
            case 5:
                listarMatriculasPorAlumno(); // Nuevo método
                break;
            case 6:
                listarMatriculasPorAsignatura(); // Nuevo método
                break;
            case 7:
                modificarNotaMatricula(); // Nuevo método
                break;
            case 0:
                vista.mostrarMensaje("Volviendo al menú principal.");
                break;
            default:
                vista.mostrarMensaje("Opción no válida para Matrículas. Intente de nuevo.");
        }
    }

    private void añadirMatricula() {
        vista.mostrarMensaje("--- Añadir Matrícula ---");
        try {
            String idMatricula = consola.leerCadena("Introduzca ID de la matrícula: ");
            // Se usa buscarMatricula (sin 'PorId') como se definió en Modelo
            if (modelo.buscarMatricula(idMatricula) != null) {
                vista.mostrarMensaje("Error: Ya existe una matrícula con ese ID.");
                return;
            }

            String dniAlumno = consola.leerCadena("Introduzca DNI del alumno a matricular: ");
            Alumno alumno = modelo.buscarAlumno(dniAlumno); // Usando el método correcto
            if (alumno == null) {
                vista.mostrarMensaje("Error: No se encontró el alumno con DNI " + dniAlumno + ". No se puede matricular.");
                return;
            }

            String codigoAsignatura = consola.leerCadena("Introduzca código de la asignatura: ");
            Asignatura asignatura = modelo.buscarAsignatura(codigoAsignatura); // Usando el método correcto
            if (asignatura == null) {
                vista.mostrarMensaje("Error: No se encontró la asignatura con código " + codigoAsignatura + ". No se puede matricular.");
                return;
            }

            LocalDate fechaMatriculacion = consola.leerFecha("Introduzca fecha de matriculación (AAAA-MM-DD): ");
            Double nota = consola.leerDouble("Introduzca nota (deje en blanco o 'null' si no hay nota todavía): ");

            // La llamada a añadirMatricula en Modelo espera parámetros, no un objeto Matricula
            if (modelo.añadirMatricula(idMatricula, dniAlumno, codigoAsignatura, fechaMatriculacion, nota)) {
                vista.mostrarMensaje("Matrícula añadida correctamente.");
            } else {
                vista.mostrarMensaje("Error: No se pudo añadir la matrícula (posiblemente ya existe el alumno/asignatura o el ID de matrícula).");
            }
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje("Error al añadir matrícula: " + e.getMessage());
        }
    }

    private void buscarMatricula() {
        vista.mostrarMensaje("--- Buscar Matrícula ---");
        String idMatricula = consola.leerCadena("Introduzca ID de la matrícula a buscar: ");
        // Se usa buscarMatricula (sin 'PorId') como se definió en Modelo
        Matricula matricula = modelo.buscarMatricula(idMatricula);
        if (matricula != null) {
            vista.mostrarMatricula(matricula); // Mostrar una única matrícula
        } else {
            vista.mostrarMensaje("Matrícula no encontrada.");
        }
    }

    private void eliminarMatricula() {
        vista.mostrarMensaje("--- Eliminar Matrícula ---");
        String idMatricula = consola.leerCadena("Introduzca ID de la matrícula a eliminar: ");
        // Se usa eliminarMatricula (sin 'PorId') como se definió en Modelo
        if (modelo.eliminarMatricula(idMatricula)) {
            vista.mostrarMensaje("Matrícula eliminada correctamente.");
        } else {
            vista.mostrarMensaje("Error: No se pudo eliminar la matrícula. ID no encontrado o no existe.");
        }
    }

    private void listarMatriculasTodas() {
        vista.mostrarMensaje("--- Listado de Todas las Matrículas ---");
        // Declaración e inicialización de la variable 'matriculas'
        ArrayList<Matricula> matriculas = modelo.getTodasLasMatriculas();

        if (matriculas.isEmpty()) {
            vista.mostrarMensaje("No hay matrículas registradas.");
        } else {
            vista.mostrarMatriculas(matriculas);
        }
    }

    private void listarMatriculasPorAlumno() {
        vista.mostrarMensaje("--- Listado de Matrículas por Alumno ---");
        String dniAlumno = consola.leerCadena("Introduzca DNI del alumno para listar sus matrículas: ");

        // Declaración e inicialización de la variable 'matriculasAlumno'
        ArrayList<Matricula> matriculasAlumno = modelo.getMatriculasDeAlumno(dniAlumno);

        if (matriculasAlumno.isEmpty()) {
            vista.mostrarMensaje("No hay matrículas para el alumno con DNI " + dniAlumno + ".");
        } else {
            vista.mostrarMatriculasPorAlumno(matriculasAlumno);
        }
    }

    private void listarMatriculasPorAsignatura() {
        vista.mostrarMensaje("--- Listado de Matrículas por Asignatura ---");
        String codigoAsignatura = consola.leerCadena("Introduzca código de la asignatura para listar sus matrículas: ");

        // Declaración e inicialización de la variable 'matriculasAsignatura'
        ArrayList<Matricula> matriculasAsignatura = modelo.getMatriculasDeAsignatura(codigoAsignatura);

        if (matriculasAsignatura.isEmpty()) {
            vista.mostrarMensaje("No hay matrículas para la asignatura con código " + codigoAsignatura + ".");
        } else {
            vista.mostrarMatriculasPorAsignatura(matriculasAsignatura);
        }
    }

    private void modificarNotaMatricula() {
        vista.mostrarMensaje("--- Modificar Nota de Matrícula ---");
        String idMatricula = consola.leerCadena("Introduzca ID de la matrícula a modificar: ");
        // Se usa buscarMatricula (sin 'PorId') como se definió en Modelo
        Matricula matricula = modelo.buscarMatricula(idMatricula);
        if (matricula == null) {
            vista.mostrarMensaje("Error: Matrícula no encontrada.");
            return;
        }

        vista.mostrarMensaje("Matrícula actual: " + matricula);
        Double nuevaNota = consola.leerDouble("Introduzca nueva nota (deje en blanco o 'null' para sin nota): ");

        if (modelo.actualizarNotaMatricula(idMatricula, nuevaNota)) {
            vista.mostrarMensaje("Nota de matrícula actualizada correctamente.");
        } else {
            vista.mostrarMensaje("Error: No se pudo actualizar la nota de la matrícula.");
        }
    }
}
