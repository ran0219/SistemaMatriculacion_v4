package org.iesalandalus.programacion.matriculacion.vista;

public enum Opcion {
    INSERTAR_ALUMNO("Insertar alumno"),
    BUSCAR_ALUMNO("Buscar alumno"),
    BORRAR_ALUMNO("Borrar alumno"),
    MOSTRAR_ALUMNOS("Mostrar alumnos"),
    INSERTAR_ASIGNATURA("Insertar asignatura"),
    BUSCAR_ASIGNATURA("Buscar asignatura"),
    BORRAR_ASIGNATURA("Borrar asignatura"),
    MOSTRAR_ASIGNATURAS("Mostrar asignaturas"),
    INSERTAR_CICLO("Insertar ciclo formativo"),
    BUSCAR_CICLO("Buscar ciclo formativo"),
    BORRAR_CICLO("Borrar ciclo formativo"),
    MOSTRAR_CICLOS("Mostrar ciclos formativos"),
    INSERTAR_MATRICULA("Insertar matrícula"),
    BUSCAR_MATRICULA("Buscar matrícula"),
    ANULAR_MATRICULA("Anular matrícula"),
    MOSTRAR_MATRICULAS("Mostrar matrículas"),
    MOSTRAR_MATRICULAS_ALUMNO("Mostrar matrículas por alumno"),
    MOSTRAR_MATRICULAS_CICLO("Mostrar matrículas por ciclo formativo"),
    MOSTRAR_MATRICULAS_CURSO("Mostrar matrículas por curso académico"),
    SALIR("Salir");

    private String mensaje;

    Opcion(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return ordinal() + " .- " + mensaje;
    }
}