package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Consola {

    public static Scanner scanner = new Scanner(System.in);

    private Consola() {}

    public static void mostrarMenu() {
        for (Opcion opcion : Opcion.values()) {
            System.out.println(opcion);
        }
    }

    public static Opcion elegirOpcion() {
        int opcionElegida = scanner.nextInt();
        scanner.nextLine();
        return Opcion.values()[opcionElegida];
    }

    public static Alumno leerAlumno() {
        System.out.println("Introduce el nombre del alumno:");
        String nombre = scanner.nextLine();
        System.out.println("Introduce el teléfono del alumno:");
        String telefono = scanner.nextLine();
        System.out.println("Introduce el email del alumno:");
        String email = scanner.nextLine();
        System.out.println("Introduce el DNI del alumno:");
        String dni = scanner.nextLine();
        LocalDate fechaNacimiento = leerFecha("Introduce la fecha de nacimiento del alumno (dd/MM/yyyy):");
        return new Alumno(nombre, telefono, email, dni, fechaNacimiento);
    }

    public static Alumno getAlumnoPorDni() {
        System.out.println("Introduce el DNI del alumno:");
        String dni = scanner.nextLine();
        return new Alumno("Nombre ficticio", "123456789", "email@ficticio.com", dni, LocalDate.now().minusYears(18));
    }

    public static LocalDate leerFecha(String mensaje) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Alumno.FORMATO_FECHA);
        LocalDate fecha = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                System.out.println(mensaje);
                fecha = LocalDate.parse(scanner.nextLine(), formatter);
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto. Inténtalo de nuevo.");
            }
        }
        return fecha;
    }

    public static Grado leerGrado() {
        System.out.println("Elige el grado:");
        for (Grado grado : Grado.values()) {
            System.out.println(grado.imprimir());
        }
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return Grado.values()[opcion];
    }

    public static CicloFormativo leerCicloFormativo() {
        System.out.println("Introduce el identificador del ciclo formativo:");
        int identificador = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce la familia profesional del ciclo formativo:");
        String familiaProfesional = scanner.nextLine();
        Grado grado = leerGrado();
        System.out.println("Introduce el nombre del ciclo formativo:");
        String nombre = scanner.nextLine();
        System.out.println("Introduce el número de horas del ciclo formativo:");
        int horas = scanner.nextInt();
        scanner.nextLine();
        return new CicloFormativo(identificador, familiaProfesional, grado, nombre, horas);
    }

    public static void mostrarCiclosFormativos() {
        Iterable<? extends CicloFormativo> ciclos = null;
        for (CicloFormativo ciclo : ciclos) {
            if (ciclo != null) {
                System.out.println(ciclo.imprimir());
            }
        }
    }

    public static CicloFormativo getCicloFormativoPorCodigo() {
        System.out.println("Introduce el identificador del ciclo formativo:");
        int identificador = scanner.nextInt();
        scanner.nextLine();
        return new CicloFormativo(identificador, "Familia ficticia", Grado.MEDIO, "Ciclo ficticio", 1000);
    }

    public static Curso leerCurso() {
        System.out.println("Elige el curso:");
        for (Curso curso : Curso.values()) {
            System.out.println(curso.imprimir());
        }
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return Curso.values()[opcion];
    }

    public static EspecialidadProfesorado leerEspecialidadProfesorado() {
        System.out.println("Elige la especialidad del profesorado:");
        for (EspecialidadProfesorado especialidad : EspecialidadProfesorado.values()) {
            System.out.println(especialidad.imprimir());
        }
        int opcion = scanner.nextInt();
        scanner.nextLine();
        return EspecialidadProfesorado.values()[opcion];
    }

    public static Asignatura leerAsignatura() {
        System.out.println("Introduce el identificador de la asignatura:");
        int identificador = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce el nombre de la asignatura:");
        String nombre = scanner.nextLine();
        System.out.println("Introduce el número de horas anuales de la asignatura:");
        int horasAnuales = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce el número de horas de desdoble de la asignatura:");
        int horasDesdoble = scanner.nextInt();
        scanner.nextLine();
        Curso curso = leerCurso();
        EspecialidadProfesorado especialidadProfesorado = leerEspecialidadProfesorado();
        CicloFormativo cicloFormativo = getCicloFormativoPorCodigo();
        return new Asignatura(identificador, nombre, horasAnuales, horasDesdoble, curso, especialidadProfesorado, cicloFormativo);
    }

    public static Asignatura getAsignaturaPorCodigo() {
        System.out.println("Introduce el identificador de la asignatura:");
        int identificador = scanner.nextInt();
        scanner.nextLine();
        return new Asignatura(identificador, "Asignatura ficticia", 100, 2, Curso.PRIMERO, EspecialidadProfesorado.INFORMATICA, getCicloFormativoPorCodigo());
    }


    public static void mostrarAsignaturas(Asignatura[] asignaturas) {
        for (Asignatura asignatura : asignaturas) {
            if (asignatura != null) {
                System.out.println(asignatura.imprimir());
            }
        }
    }

    public static Asignatura[] elegirAsignaturasMatricula(Asignatura[] disponibles) {
        mostrarAsignaturas(disponibles);
        List<Asignatura> seleccionadas = new ArrayList<>();
        String codigo;
        do {
            System.out.print("Introduce el identificador de la asignatura a añadir (deja vacío para terminar): ");
            codigo = scanner.nextLine().trim();
            if (!codigo.isEmpty()) {
                try {
                    int id = Integer.parseInt(codigo);
                    for (Asignatura asignatura : disponibles) {
                        if (asignatura != null && asignatura.getIdentificador() == id && !seleccionadas.contains(asignatura)) {
                            seleccionadas.add(asignatura);
                            System.out.println("Asignatura añadida.");
                            break;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Identificador no válido.");
                }
            }
        } while (!codigo.isEmpty());
        return seleccionadas.toArray(new Asignatura[0]);
    }

    public static Matricula leerMatricula(Alumno alumno, Asignatura[] asignaturas) {
        System.out.print("Introduce el identificador de la matrícula: ");
        int identificador = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Introduce el curso académico: ");
        String cursoAcademico = scanner.nextLine();
        LocalDate fechaMatricula = leerFecha("Introduce la fecha de matrícula (dd/MM/yyyy):");

        List<Asignatura> listaAsignaturas = new ArrayList<>();
        for (Asignatura asignatura : asignaturas) {
            listaAsignaturas.add(asignatura);
        }

        return new Matricula(identificador, cursoAcademico, fechaMatricula, alumno, listaAsignaturas);
    }

    public static Matricula getMatriculaPorIdentificador() {
        System.out.println("Introduce el identificador de la matrícula:");
        int identificador = scanner.nextInt();
        scanner.nextLine();
        return new Matricula(identificador, "Curso ficticio", LocalDate.now(), List.of(getAsignaturaPorCodigo()));
    }

    public static boolean asignaturaYaMatriculada(List<Asignatura> asignaturasMatriculadas, Asignatura asignatura) {
        return asignaturasMatriculadas.contains(asignatura);
    }

    // Métodos vacíos o no modificados
    public static void insertarAlumno() {}
    public static void buscarAlumno() {}
    public static void borrarAlumno() {}
    public static void despedir() {}
    public static void mostrarMatriculasPorCursoAcademico() {}
    public static void mostrarMatriculasPorCicloFormativo() {}
    public static void mostrarMatriculasPorAlumno() {}
    public static void mostrarMatriculas() {}
    public static void anularMatricula() {}
    public static void buscarMatricula() {}
    public static void insertarMatricula() {}
    public static void borrarCicloFormativo() {}
    public static void buscarCicloFormativo() {}
    public static void insertarCicloFormativo() {}
    public static void buscarAsignatura() {}
    public static void borrarAsignatura() {}
    public static void insertarAsignatura() {}
    public static void mostrarAlumnos() {}

    public static void mostrarAsignaturas() {
        Asignatura[] asignaturas = new Asignatura[0];
        if (asignaturas == null || asignaturas.length == 0) {
            System.out.println("No hay asignaturas disponibles.");
        } else {
            for (Asignatura asignatura : asignaturas) {
                System.out.println(asignatura.imprimir());
            }
        }
    }
}
