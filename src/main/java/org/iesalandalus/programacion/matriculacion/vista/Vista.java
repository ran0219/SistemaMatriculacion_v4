package org.iesalandalus.programacion.matriculacion.vista;

// Importaciones necesarias
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.modelo.dominio.Matricula;

import java.util.ArrayList;
import java.util.Collections; // Para Collections.sort si se prefiere
import java.util.Comparator;  // Para Comparator.comparing

public class Vista {

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
        System.out.println("6. Listar Matrículas por Asignatura");
        System.out.println("7. Modificar Nota de Matrícula"); // Opciones de mejora
        System.out.println("0. Volver al Menú Principal");
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
            System.out.println("  Fecha de Nacimiento: " + alumno.getFechaNacimiento());
            System.out.println("---------------------------");
        } else {
            System.out.println("No se pudo mostrar el alumno: el objeto es nulo.");
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
            System.out.println("No se pudo mostrar la asignatura: el objeto es nulo.");
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
            System.out.println("No se pudo mostrar el ciclo formativo: el objeto es nulo.");
        }
    }

    /**
     * Muestra los detalles de una única Matrícula en la consola.
     * Es útil para cuando se busca una matrícula específica.
     * @param matricula El objeto Matricula a mostrar.
     */
    public void mostrarMatricula(Matricula matricula) {
        if (matricula != null) {
            System.out.println("\n--- Detalles de la Matrícula ---");
            System.out.println("  ID: " + matricula.getIdMatricula());

            // Acceso a los detalles del alumno asociado a la matrícula
            Alumno alumno = matricula.getAlumno();
            if (alumno != null) {
                System.out.println("  Alumno: " + alumno.getNombre() + " " + alumno.getApellidos() + " (DNI: " + alumno.getDni() + ")");
            } else {
                System.out.println("  Alumno: [No asignado o nulo]");
            }

            // Acceso a los detalles de la asignatura asociada a la matrícula
            Asignatura asignatura = matricula.getAsignatura();
            if (asignatura != null) {
                System.out.println("  Asignatura: " + asignatura.getNombre() + " (Código: " + asignatura.getCodigo() + ")");
            } else {
                System.out.println("  Asignatura: [No asignada o nula]");
            }

            System.out.println("  Fecha de Matriculación: " + matricula.getFechaMatriculacion());

            // Manejo de la nota, ya que puede ser nula (Double)
            if (matricula.getNota() != null) {
                System.out.println("  Nota: " + String.format("%.2f", matricula.getNota()));
            } else {
                System.out.println("  Nota: Sin asignar");
            }
            System.out.println("----------------------------------");
        } else {
            System.out.println("No se pudo mostrar la matrícula: el objeto es nulo.");
        }
    }


    // --- Métodos para mostrar listas de objetos (con ordenación) ---

    /**
     * Muestra una lista de Alumnos, ordenados por DNI.
     * @param alumnos La lista de Alumnos a mostrar.
     */
    public void mostrarAlumnos(ArrayList<Alumno> alumnos) {
        if (alumnos == null || alumnos.isEmpty()) {
            System.out.println("No hay alumnos para mostrar.");
            return;
        }
        System.out.println("\n--- LISTADO DE ALUMNOS (Ordenado por DNI) ---");
        // Ordena la lista de alumnos por DNI
        alumnos.sort(Comparator.comparing(Alumno::getDni));

        for (Alumno alumno : alumnos) {
            System.out.println(alumno); // Asume que Alumno.toString() está bien implementado
        }
        System.out.println("----------------------------------------");
    }

    /**
     * Muestra una lista de Asignaturas, ordenadas por nombre.
     * @param asignaturas La lista de Asignaturas a mostrar.
     */
    public void mostrarAsignaturas(ArrayList<Asignatura> asignaturas) {
        if (asignaturas == null || asignaturas.isEmpty()) {
            System.out.println("No hay asignaturas para mostrar.");
            return;
        }
        System.out.println("\n--- LISTADO DE ASIGNATURAS (Ordenado por Nombre) ---");
        // Ordena la lista de asignaturas por Nombre
        asignaturas.sort(Comparator.comparing(Asignatura::getNombre));

        for (Asignatura asignatura : asignaturas) {
            System.out.println(asignatura); // Asume que Asignatura.toString() está bien implementado
        }
        System.out.println("----------------------------------------");
    }

    /**
     * Muestra una lista de Ciclos Formativos, ordenados por nombre.
     * @param ciclos La lista de Ciclos Formativos a mostrar.
     */
    public void mostrarCiclosFormativos(ArrayList<CicloFormativo> ciclos) {
        if (ciclos == null || ciclos.isEmpty()) {
            System.out.println("No hay ciclos formativos para mostrar.");
            return;
        }
        System.out.println("\n--- LISTADO DE CICLOS FORMATIVOS (Ordenado por Nombre) ---");
        // Ordena la lista de ciclos formativos por Nombre
        ciclos.sort(Comparator.comparing(CicloFormativo::getNombre));

        for (CicloFormativo ciclo : ciclos) {
            System.out.println(ciclo); // Asume que CicloFormativo.toString() está bien implementado
        }
        System.out.println("----------------------------------------");
    }

    /**
     * Muestra una lista de Matrículas, ordenadas por ID de matrícula.
     * Este es el método general para listar todas las matrículas.
     * @param matriculas La lista de Matrículas a mostrar.
     */
    public void mostrarMatriculas(ArrayList<Matricula> matriculas) {
        if (matriculas == null || matriculas.isEmpty()) {
            System.out.println("No hay matrículas para mostrar.");
            return;
        }
        System.out.println("\n--- LISTADO DE MATRÍCULAS (Ordenado por ID de Matrícula) ---");
        // Ordena la lista de matrículas por ID
        matriculas.sort(Comparator.comparing(Matricula::getIdMatricula));

        for (Matricula matricula : matriculas) {
            System.out.println(matricula); // Asume que Matricula.toString() está bien implementado
        }
        System.out.println("--------------------------------------------------");
    }

    /**
     * Muestra una lista de Matrículas, ordenadas por el DNI del alumno asociado.
     * @param matriculas La lista de Matrículas a mostrar.
     */
    public void mostrarMatriculasPorAlumno(ArrayList<Matricula> matriculas) {
        if (matriculas == null || matriculas.isEmpty()) {
            System.out.println("No hay matrículas para mostrar para este alumno.");
            return;
        }
        System.out.println("\n--- LISTADO DE MATRÍCULAS POR ALUMNO (Ordenado por DNI del Alumno) ---");
        // Ordena la lista de matrículas por DNI del alumno
        // Nota: Asume que getAlumno() nunca devuelve null.
        matriculas.sort(Comparator.comparing(m -> m.getAlumno().getDni()));

        for (Matricula matricula : matriculas) {
            System.out.println(matricula);
        }
        System.out.println("----------------------------------------------------------");
    }

    /**
     * Muestra una lista de Matrículas, ordenadas por el Código de Asignatura asociado.
     * @param matriculas La lista de Matrículas a mostrar.
     */
    public void mostrarMatriculasPorAsignatura(ArrayList<Matricula> matriculas) {
        if (matriculas == null || matriculas.isEmpty()) {
            System.out.println("No hay matrículas para mostrar para esta asignatura.");
            return;
        }
        System.out.println("\n--- LISTADO DE MATRÍCULAS POR ASIGNATURA (Ordenado por Código de Asignatura) ---");
        // Ordena la lista de matrículas por Código de Asignatura
        // Nota: Asume que getAsignatura() nunca devuelve null.
        matriculas.sort(Comparator.comparing(m -> m.getAsignatura().getCodigo()));

        for (Matricula matricula : matriculas) {
            System.out.println(matricula);
        }
        System.out.println("----------------------------------------------------------");
    }
}
