package org.iesalandalus.programacion.matriculacion;


import org.iesalandalus.programacion.matriculacion.dominio.Alumno;
import org.iesalandalus.programacion.matriculacion.dominio.Asignatura;
import org.iesalandalus.programacion.matriculacion.dominio.CicloFormativo;
import org.iesalandalus.programacion.matriculacion.dominio.Matricula;
import org.iesalandalus.programacion.matriculacion.negocio.Alumnos;
import org.iesalandalus.programacion.matriculacion.negocio.Asignaturas;
import org.iesalandalus.programacion.matriculacion.negocio.CiclosFormativos;
import org.iesalandalus.programacion.matriculacion.negocio.Matriculas;
import org.iesalandalus.programacion.matriculacion.vista.Consola;
import org.iesalandalus.programacion.matriculacion.vista.Opcion;

public class MainApp {

    private Alumnos alumnos;
    private Asignaturas asignaturas;
    private CiclosFormativos ciclosFormativos;
    private Matriculas matriculas;

    public MainApp(int capacidadAlumnos, int capacidadAsignaturas, int capacidadCiclos, int capacidadMatriculas) {
        this.alumnos = new Alumnos(capacidadAlumnos);
        this.asignaturas = new Asignaturas(capacidadAsignaturas);
        this.ciclosFormativos = new CiclosFormativos(capacidadCiclos);
        this.matriculas = new Matriculas(capacidadMatriculas);
    }

    public void ejecutarOpcion(Opcion opcion) {
        switch (opcion) {
            case INSERTAR_ALUMNO:
                insertarAlumno();
                break;
            case BUSCAR_ALUMNO:
                buscarAlumno();
                break;
            case BORRAR_ALUMNO:
                borrarAlumno();
                break;
            case MOSTRAR_ALUMNOS:
                mostrarAlumnos();
                break;
            case INSERTAR_ASIGNATURA:
                insertarAsignatura();
                break;
            case BUSCAR_ASIGNATURA:
                buscarAsignatura();
                break;
            case BORRAR_ASIGNATURA:
                borrarAsignatura();
                break;
            case MOSTRAR_ASIGNATURAS:
                mostrarAsignaturas();
                break;
            case INSERTAR_CICLO:
                insertarCicloFormativo();
                break;
            case BUSCAR_CICLO:
                buscarCicloFormativo();
                break;
            case BORRAR_CICLO:
                borrarCicloFormativo();
                break;
            case MOSTRAR_CICLOS:
                mostrarCiclosFormativos();
                break;
            case INSERTAR_MATRICULA:
                insertarMatricula();
                break;
            case BUSCAR_MATRICULA:
                buscarMatricula();
                break;
            case ANULAR_MATRICULA:
                anularMatricula();
                break;
            case MOSTRAR_MATRICULAS:
                mostrarMatriculas();
                break;
            case MOSTRAR_MATRICULAS_ALUMNO:
                mostrarMatriculasPorAlumno();
                break;
            case MOSTRAR_MATRICULAS_CICLO:
                mostrarMatriculasPorCicloFormativo();
                break;
            case MOSTRAR_MATRICULAS_CURSO:
                mostrarMatriculasPorCursoAcademico();
                break;
            case SALIR:
                System.out.println("¡Hasta luego!");
                break;
        }
    }

    public void insertarAlumno() {
        try {
            Alumno alumno = Consola.leerAlumno();
            alumnos.insertar(alumno);
            System.out.println("Alumno insertado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al insertar alumno: " + e.getMessage());
        }
    }

    public void buscarAlumno() {
        Alumno alumno = alumnos.buscar(Consola.getAlumnoPorDni().getDni());
        if (alumno != null) {
            System.out.println(alumno.imprimir());
        } else {
            System.out.println("No se encontró ningún alumno con ese DNI.");
        }
    }

    public void borrarAlumno() {
        alumnos.borrar(Consola.getAlumnoPorDni().getDni());
        System.out.println("Alumno borrado correctamente.");
    }

    public void mostrarAlumnos() {
        if (alumnos.getCantidad() > 0) {
            for (Alumno alumno : alumnos.get()) {
                System.out.println(alumno.imprimir());
            }
        } else {
            System.out.println("No hay alumnos registrados.");
        }
    }

    public void insertarAsignatura() {
        try {
            Asignatura asignatura = Consola.leerAsignatura();
            asignaturas.insertar(asignatura);
            System.out.println("Asignatura insertada correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al insertar asignatura: " + e.getMessage());
        }
    }

    public void buscarAsignatura() {
        Asignatura asignatura = asignaturas.buscar(Consola.getAsignaturaPorCodigo().getIdentificador());
        if (asignatura != null) {
            System.out.println(asignatura.imprimir());
        } else {
            System.out.println("No se encontró ninguna asignatura con ese código.");
        }
    }

    public void borrarAsignatura() {
        asignaturas.borrar(Consola.getAsignaturaPorCodigo().getIdentificador());
        System.out.println("Asignatura borrada correctamente.");
    }

    public void mostrarAsignaturas() {
        if (asignaturas.getCantidadAsignaturas() > 0) {
            for (Asignatura asignatura : asignaturas.get()) {
                System.out.println(asignatura.imprimir());
            }
        } else {
            System.out.println("No hay asignaturas registradas.");
        }
    }

    public void insertarCicloFormativo() {
        try {
            CicloFormativo ciclo = Consola.leerCicloFormativo();
            ciclosFormativos.insertar(ciclo);
            System.out.println("Ciclo formativo insertado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al insertar ciclo formativo: " + e.getMessage());
        }
    }

    public void buscarCicloFormativo() {
        CicloFormativo ciclo = ciclosFormativos.buscar(Consola.getCicloFormativoPorCodigo().getIdentificador());
        if (ciclo != null) {
            System.out.println(ciclo.imprimir());
        } else {
            System.out.println("No se encontró ningún ciclo formativo con ese código.");
        }
    }

    public void borrarCicloFormativo() {
        ciclosFormativos.borrar(Consola.getCicloFormativoPorCodigo().getIdentificador());
        System.out.println("Ciclo formativo borrado correctamente.");
    }

    public void mostrarCiclosFormativos() {
        if (ciclosFormativos.getCantidadCiclos() > 0) {
            Consola.mostrarCiclosFormativos(ciclosFormativos);
        } else {
            System.out.println("No hay ciclos formativos registrados.");
        }
    }

    public void insertarMatricula() {
        try {
            Matricula matricula = Consola.leerMatricula();
            matriculas.insertar(matricula);
            System.out.println("Matrícula insertada correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al insertar matrícula: " + e.getMessage());
        }
    }

    public void buscarMatricula() {
        Matricula matricula = matriculas.buscar(Consola.getMatriculaPorIdentificador().getIdentificador());
        if (matricula != null) {
            System.out.println(matricula.imprimir());
        } else {
            System.out.println("No se encontró ninguna matrícula con ese identificador.");
        }
    }

    public void anularMatricula() {
        System.out.println("Elige la matrícula que deseas anular:");
        this.mostrarMatriculas(); // Añadido 'this.' para resolver la ambigüedad
        Matricula matricula = matriculas.buscar(Consola.getMatriculaPorIdentificador().getIdentificador());
        if (matricula != null) {
            try {
                matricula.setFechaAnulacion(Consola.leerFecha("Introduce la fecha de anulación (dd/MM/yyyy):"));
                System.out.println("Matrícula anulada correctamente.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error al anular matrícula: " + e.getMessage());
            }
        } else {
            System.out.println("No se encontró ninguna matrícula con ese identificador.");
        }
    }

    public void mostrarMatriculas() { // Muestra todas las matrículas
        if (matriculas.getCantidadMatriculas() > 0) {
            for (Matricula matricula : matriculas.get()) {
                System.out.println(matricula.imprimir());
            }
        } else {
            System.out.println("No hay matrículas registradas.");
        }
    }

    public void mostrarMatriculasPorAlumno() {
        Alumno alumno = alumnos.buscar(Consola.getAlumnoPorDni().getDni());
        if (alumno != null) {
            Matricula[] matriculasAlumno = matriculas.get(alumno);
            if (matriculasAlumno.length > 0) {
                for (Matricula matricula : matriculasAlumno) {
                    System.out.println(matricula.imprimir());
                }
            } else {
                System.out.println("No hay matrículas para el alumno indicado.");
            }
        } else {
            System.out.println("No se encontró el alumno.");
        }
    }

    public void mostrarMatriculasPorCicloFormativo() {
        Consola.mostrarCiclosFormativos(ciclosFormativos);
        CicloFormativo ciclo = ciclosFormativos.buscar(Consola.getCicloFormativoPorCodigo().getIdentificador());
        if (ciclo != null) {
            Matricula[] matriculasCiclo = matriculas.get(ciclo);
            if (matriculasCiclo.length > 0) {
                for (Matricula matricula : matriculasCiclo) {
                    System.out.println(matricula.imprimir());
                }
            } else {
                System.out.println("No hay matrículas para el ciclo formativo indicado.");
            }
        } else {
            System.out.println("No se encontró el ciclo formativo.");
        }
    }

    public void mostrarMatriculasPorCursoAcademico() {
        System.out.println("Introduce el curso académico:");
        String cursoAcademico = Consola.scanner.nextLine();
        Matricula[] matriculasCurso = matriculas.get(cursoAcademico);
        if (matriculasCurso.length > 0) {
            for (Matricula matricula : matriculasCurso) {
                System.out.println(matricula.imprimir());
            }
        } else {
            System.out.println("No hay matrículas para el curso académico indicado.");
        }
    }

    public static void main(String[] args) {
        MainApp app = new MainApp(100, 100, 100, 100); // Ajusta las capacidades según necesites
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            app.ejecutarOpcion(opcion);
        } while (opcion != Opcion.SALIR);
    }
}