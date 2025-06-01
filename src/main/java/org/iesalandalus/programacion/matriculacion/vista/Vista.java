package org.iesalandalus.programacion.matriculacion.vista;

// Importaciones necesarias
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import java.time.format.DateTimeFormatter; // Para formatear fechas
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List; // Importar List para mayor claridad

public class Vista {

    // Formato de fecha para la presentación en la vista
    private static final DateTimeFormatter FORMATO_FECHA_VISTA = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Constructor de la Vista
    public Vista() {
        // Puedes añadir lógica de inicialización si es necesaria
    }

    // --- Métodos de Mensajes Generales ---

    /**
     * Muestra un mensaje general en la consola.
     * @param mensaje El texto del mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Muestra un mensaje de error en la consola.
     * @param mensajeError El texto del mensaje de error a mostrar.
     */
    public void mostrarError(String mensajeError) {
        System.err.println("ERROR: " + mensajeError);
    }

    // --- Métodos de Menú ---

    /**
     * Muestra el menú principal de la aplicación.
     */
    public void mostrarMenuPrincipal() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Gestión de Alumnos");
        System.out.println("2. Gestión de Asignaturas");
        System.out.println("3. Gestión de Ciclos Formativos");
        System.out.println("4. Gestión de Matrículas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Muestra el menú de gestión de Alumnos.
     */
    public void mostrarMenuAlumnos() {
        System.out.println("\n--- MENÚ ALUMNOS ---");
        System.out.println("1. Añadir Alumno");
        System.out.println("2. Buscar Alumno");
        System.out.println("3. Eliminar Alumno");
        System.out.println("4. Listar Alumnos");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Muestra el menú de gestión de Asignaturas.
     */
    public void mostrarMenuAsignaturas() {
        System.out.println("\n--- MENÚ ASIGNATURAS ---");
        System.out.println("1. Añadir Asignatura");
        System.out.println("2. Buscar Asignatura");
        System.out.println("3. Eliminar Asignatura");
        System.out.println("4. Listar Asignaturas");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Muestra el menú de gestión de Ciclos Formativos.
     */
    public void mostrarMenuCiclosFormativos() {
        System.out.println("\n--- MENÚ CICLOS FORMATIVOS ---");
        System.out.println("1. Añadir Ciclo Formativo");
        System.out.println("2. Buscar Ciclo Formativo");
        System.out.println("3. Eliminar Ciclo Formativo");
        System.out.println("4. Listar Ciclos Formativos");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Muestra el menú de gestión de Matrículas.
     */
    public void mostrarMenuMatriculas() {
        System.out.println("\n--- MENÚ MATRÍCULAS ---");
        System.out.println("1. Añadir Matrícula");
        System.out.println("2. Buscar Matrícula");
        System.out.println("3. Eliminar Matrícula");
        System.out.println("4. Listar Todas las Matrículas");
        System.out.println("5. Listar Matrículas por Alumno");
        // Opción 6: Listar Matrículas por Asignatura es compleja para el modelo actual (List<Asignatura>)
        // Si necesitas esto, deberías filtrar en la capa de negocio y pasar una lista ya filtrada aquí.
        // System.out.println("6. Listar Matrículas por Asignatura");
        System.out.println("6. Modificar Nota de Matrícula");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione una opción: ");
    }

    // --- Métodos para mostrar objetos individuales ---

    /**
     * Muestra los detalles de un único Alumno en la consola.
     * @param alumno El objeto Alumno a mostrar.
     */
    public void mostrarAlumno(Alumno alumno) {
        if (alumno != null) {
            System.out.println("\n--- Detalles del Alumno ---");
            System.out.println("  DNI: " + alumno.getDni());
            System.out.println("  Nombre: " + alumno.getNombre());
            System.out.println("  Apellidos: " + alumno.getApellidos());
            System.out.println("  Fecha de Nacimiento: " + (alumno.getFechaNacimiento() != null ? alumno.getFechaNacimiento().format(FORMATO_FECHA_VISTA) : "N/A"));
            System.out.println("---------------------------");
        } else {
            mostrarError("No se pudo mostrar el alumno: el objeto es nulo.");
        }
    }

    /**
     * Muestra los detalles de una única Asignatura en la consola.
     * @param asignatura El objeto Asignatura a mostrar.
     */
    public void mostrarAsignatura(Asignatura asignatura) {
        if (asignatura != null) {
            System.out.println("\n--- Detalles de la Asignatura ---");
            System.out.println("  Código: " + asignatura.getCodigo());
            System.out.println("  Nombre: " + asignatura.getNombre());
            System.out.println("  Créditos: " + asignatura.getCreditos());
            System.out.println("-------------------------------");
        } else {
            mostrarError("No se pudo mostrar la asignatura: el objeto es nulo.");
        }
    }

    /**
     * Muestra los detalles de un único Ciclo Formativo en la consola.
     * @param ciclo El objeto CicloFormativo a mostrar.
     */
    public void mostrarCicloFormativo(CicloFormativo ciclo) {
        if (ciclo != null) {
            System.out.println("\n--- Detalles del Ciclo Formativo ---");
            System.out.println("  Código: " + ciclo.getCodigo());
            System.out.println("  Nombre: " + ciclo.getNombre());
            System.out.println("  Tipo: " + ciclo.getTipo());
            System.out.println("----------------------------------");
        } else {
            mostrarError("No se pudo mostrar el ciclo formativo: el objeto es nulo.");
        }
    }

    /**
     * Muestra los detalles de una única Matrícula en la consola,
     * adaptada al modelo de Matrícula que contiene una lista de Asignaturas.
     * @param matricula El objeto Matricula a mostrar.
     */
    public void mostrarMatricula(Matricula matricula) {
        if (matricula != null) {
            System.out.println("\n--- Detalles de la Matrícula ---");
            // Usamos getId() para el ID de la matrícula
            System.out.println("  ID de Matrícula: " + matricula.getId());

            Alumno alumno = matricula.getAlumno();
            if (alumno != null) {
                System.out.println("  Alumno: " + alumno.getNombre() + " " + alumno.getApellidos() + " (DNI: " + alumno.getDni() + ")");
            } else {
                System.out.println("  Alumno: [No asignado]");
            }

            CicloFormativo ciclo = matricula.getCicloFormativo();
            if (ciclo != null) {
                System.out.println("  Ciclo Formativo: " + ciclo.getNombre() + " (Código: " + ciclo.getCodigo() + ")");
            } else {
                System.out.println("  Ciclo Formativo: [No asignado]");
            }

            System.out.println("  Curso Académico: " + matricula.getCursoAcademico());

            // Usamos getFecha() para la fecha de la matrícula
            System.out.println("  Fecha de Matrícula: " + (matricula.getFecha() != null ? matricula.getFecha().format(FORMATO_FECHA_VISTA) : "N/A"));

            // Manejo de la nota
            if (matricula.getNota() != null) {
                System.out.println("  Nota: " + String.format("%.2f", matricula.getNota()));
            } else {
                System.out.println("  Nota: Sin calificar");
            }

            // Muestra la lista de asignaturas de esta matrícula
            List<Asignatura> asignaturasMatriculadas = matricula.getAsignaturas();
            if (asignaturasMatriculadas != null && !asignaturasMatriculadas.isEmpty()) {
                System.out.println("  Asignaturas Matriculadas:");
                for (Asignatura asig : asignaturasMatriculadas) {
                    System.out.println("    - " + asig.getNombre() + " (Código: " + asig.getCodigo() + ")");
                }
            } else {
                System.out.println("  Asignaturas Matriculadas: Ninguna");
            }
            System.out.println("----------------------------------");
        } else {
            mostrarError("No se pudo mostrar la matrícula: el objeto es nulo.");
        }
    }


    // --- Métodos para mostrar listas de objetos (con ordenación) ---

    /**
     * Muestra una lista de Alumnos, ordenados por DNI.
     * @param alumnos La lista de Alumnos a mostrar.
     */
    public void mostrarAlumnos(List<Alumno> alumnos) { // Usar List en lugar de ArrayList para mayor flexibilidad
        if (alumnos == null || alumnos.isEmpty()) {
            mostrarMensaje("No hay alumnos para mostrar.");
            return;
        }
        System.out.println("\n--- LISTADO DE ALUMNOS (Ordenado por DNI) ---");
        // Se crea una copia para ordenar si la lista original no es modificable o si no queremos modificarla
        List<Alumno> alumnosOrdenados = new ArrayList<>(alumnos);
        alumnosOrdenados.sort(Comparator.comparing(Alumno::getDni));

        for (Alumno alumno : alumnosOrdenados) {
            System.out.println(alumno); // Asume que Alumno.toString() está bien implementado
        }
        System.out.println("----------------------------------------");
    }

    /**
     * Muestra una lista de Asignaturas, ordenadas por nombre.
     * @param asignaturas La lista de Asignaturas a mostrar.
     */
    public void mostrarAsignaturas(List<Asignatura> asignaturas) {
        if (asignaturas == null || asignaturas.isEmpty()) {
            mostrarMensaje("No hay asignaturas para mostrar.");
            return;
        }
        System.out.println("\n--- LISTADO DE ASIGNATURAS (Ordenado por Nombre) ---");
        List<Asignatura> asignaturasOrdenadas = new ArrayList<>(asignaturas);
        asignaturasOrdenadas.sort(Comparator.comparing(Asignatura::getNombre));

        for (Asignatura asignatura : asignaturasOrdenadas) {
            System.out.println(asignatura); // Asume que Asignatura.toString() está bien implementado
        }
        System.out.println("----------------------------------------");
    }

    /**
     * Muestra una lista de Ciclos Formativos, ordenados por nombre.
     * @param ciclos La lista de Ciclos Formativos a mostrar.
     */
    public void mostrarCiclosFormativos(List<CicloFormativo> ciclos) {
        if (ciclos == null || ciclos.isEmpty()) {
            mostrarMensaje("No hay ciclos formativos para mostrar.");
            return;
        }
        System.out.println("\n--- LISTADO DE CICLOS FORMATIVOS (Ordenado por Nombre) ---");
        List<CicloFormativo> ciclosOrdenados = new ArrayList<>(ciclos);
        ciclosOrdenados.sort(Comparator.comparing(CicloFormativo::getNombre));

        for (CicloFormativo ciclo : ciclosOrdenados) {
            System.out.println(ciclo); // Asume que CicloFormativo.toString() está bien implementado
        }
        System.out.println("----------------------------------------");
    }

    /**
     * Muestra una lista de Matrículas, ordenadas por ID de matrícula.
     * @param matriculas La lista de Matrículas a mostrar.
     */
    public void mostrarMatriculas(List<Matricula> matriculas) {
        if (matriculas == null || matriculas.isEmpty()) {
            mostrarMensaje("No hay matrículas para mostrar.");
            return;
        }
        System.out.println("\n--- LISTADO DE MATRÍCULAS (Ordenado por ID de Matrícula) ---");
        List<Matricula> matriculasOrdenadas = new ArrayList<>(matriculas);
        matriculasOrdenadas.sort(Comparator.comparing(Matricula::getId)); // Usar getId()
        // Alternativa si el ID es String: matriculasOrdenadas.sort(Comparator.comparing(Matricula::getIdString));

        for (Matricula matricula : matriculasOrdenadas) {
            System.out.println(matricula); // Asume que Matricula.toString() está bien implementado
        }
        System.out.println("--------------------------------------------------");
    }

    /**
     * Muestra una lista de Matrículas, filtrada (presumiblemente por alumno)
     * y ordenada por el DNI del alumno asociado.
     * @param matriculas La lista de Matrículas a mostrar (ya filtradas por un alumno).
     */
    public void mostrarMatriculasPorAlumno(List<Matricula> matriculas) {
        if (matriculas == null || matriculas.isEmpty()) {
            mostrarMensaje("No hay matrículas para mostrar para este alumno.");
            return;
        }
        System.out.println("\n--- LISTADO DE MATRÍCULAS POR ALUMNO (Ordenado por DNI del Alumno) ---");
        List<Matricula> matriculasOrdenadas = new ArrayList<>(matriculas);
        // Ordena la lista de matrículas por DNI del alumno
        // Se añade un null-check para el alumno por robustez
        matriculasOrdenadas.sort(Comparator.comparing(m -> m.getAlumno() != null ? m.getAlumno().getDni() : ""));

        for (Matricula matricula : matriculasOrdenadas) {
            System.out.println(matricula);
        }
        System.out.println("----------------------------------------------------------");
    }

    /*
     * NOTA: El método 'mostrarMatriculasPorAsignatura' del código original no se adapta bien
     * al modelo de 'Matricula' con List<Asignatura>.
     *
     * Si necesitas mostrar matrículas que contengan una asignatura específica,
     * esa lógica de filtrado debería hacerse ANTES de llamar a un método de la Vista.
     * La Vista sólo debería recibir y mostrar la lista ya filtrada.
     *
     * Por ejemplo, si el controlador te pasa una List<Matricula> que YA ha sido
     * filtrada para contener solo aquellas matrículas que tienen una asignatura particular,
     * entonces podrías usar un método como este:
     */
    // public void mostrarMatriculasFiltradasPorAsignatura(List<Matricula> matriculasFiltradas) {
    //     if (matriculasFiltradas == null || matriculasFiltradas.isEmpty()) {
    //         mostrarMensaje("No hay matrículas para mostrar con esta asignatura.");
    //         return;
    //     }
    //     System.out.println("\n--- LISTADO DE MATRÍCULAS CON ASIGNATURA ESPECÍFICA (Ordenado por Alumno) ---");
    //     List<Matricula> matriculasOrdenadas = new ArrayList<>(matriculasFiltradas);
    //     matriculasOrdenadas.sort(Comparator.comparing(m -> m.getAlumno() != null ? m.getAlumno().getDni() : ""));
    //
    //     for (Matricula matricula : matriculasOrdenadas) {
    //         System.out.println(matricula);
    //     }
    //     System.out.println("----------------------------------------------------------");
    // }
}
