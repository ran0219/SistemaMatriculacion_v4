package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.dominio.*;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Consola {

    public static Scanner scanner = new Scanner(System.in);

    private Consola() {
    }

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

    public static void mostrarCiclosFormativos(CiclosFormativos ciclos) {
        for (CicloFormativo ciclo : ciclos.get()) {
            System.out.println(ciclo.imprimir());
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

    public static void mostrarAsignaturas(Asignaturas asignaturas) {
        for (Asignatura asignatura : asignaturas.get()) {
            System.out.println(asignatura.imprimir());
        }
    }

    public static boolean asignaturaYaMatriculada(List<Asignatura> asignaturasMatriculadas, Asignatura asignatura) {
        return asignaturasMatriculadas.contains(asignatura);
    }

    public static Matricula leerMatricula() {
        System.out.println("Introduce el identificador de la matrícula:");
        int identificador = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce el curso académico de la matrícula:");
        String cursoAcademico = scanner.nextLine();
        LocalDate fechaMatricula = leerFecha("Introduce la fecha de matrícula (dd/MM/yyyy):");
        List<Asignatura> asignaturasMatriculadas = new ArrayList<>();
        boolean continuar = true;
        while (continuar) {
            Asignatura asignatura = getAsignaturaPorCodigo();
            if (!asignaturaYaMatriculada(asignaturasMatriculadas, asignatura)) {
                asignaturasMatriculadas.add(asignatura);
                System.out.println("¿Deseas añadir otra asignatura? (s/n)");
                String respuesta = scanner.nextLine();
                continuar = respuesta.equalsIgnoreCase("s");
            } else {
                System.out.println("La asignatura ya está matriculada. Inténtalo de nuevo.");
            }
        }
        return new Matricula(identificador, cursoAcademico, fechaMatricula, asignaturasMatriculadas);
    }

    public static Matricula getMatriculaPorIdentificador() {
        System.out.println("Introduce el identificador de la matrícula:");
        int identificador = scanner.nextInt();
        scanner.nextLine();
        return new Matricula(identificador, "Curso ficticio", LocalDate.now(), List.of(getAsignaturaPorCodigo()));
    }
}
