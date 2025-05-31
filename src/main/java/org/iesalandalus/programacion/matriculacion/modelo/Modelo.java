package org.iesalandalus.programacion.matriculacion.modelo;

import org.iesalandalus.programacion.matriculacion.modelo.dominio.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

// Asegúrate de que las siguientes clases (Alumno, Asignatura, CicloFormativo, Matrícula)
// estén en el mismo paquete o importadas correctamente desde sus respectivos paquetes.
// Por ejemplo:
// import com.yourpackage.Alumno;
// import com.yourpackage.Asignatura;
// import com.yourpackage.CicloFormativo;
// import com.yourpackage.Matricula;

public class Modelo {
    // Declaración de las colecciones que almacenarán los datos.
    // Usamos ArrayList para gestionar dinámicamente el tamaño.
    private final ArrayList<Alumno> listaAlumnos;
    private final ArrayList<Asignatura> listaAsignaturas;
    private final ArrayList<CicloFormativo> listaCiclosFormativos;
    private final ArrayList<Matricula> listaMatriculas;

    // La constante CAPACIDAD ya NO es necesaria al usar ArrayLists.

    public Modelo() {
        // Inicialización de los ArrayLists en el constructor.
        this.listaAlumnos = new ArrayList<>();
        this.listaAsignaturas = new ArrayList<>();
        this.listaCiclosFormativos = new ArrayList<>();
        this.listaMatriculas = new ArrayList<>();
    }

    // --- Métodos de Gestión de Alumnos ---

    /**
     * Añade un Alumno a la colección.
     * Se asegura de que no se añadan alumnos duplicados (basado en el DNI, usando el método equals de Alumno).
     * @param alumno El objeto Alumno a añadir.
     * @return true si el alumno se añadió con éxito, false si ya existía.
     */
    public boolean añadirAlumno(Alumno alumno) {
        if (alumno == null) {
            System.err.println("Error: No se puede añadir un alumno nulo.");
            return false;
        }
        if (!listaAlumnos.contains(alumno)) {
            listaAlumnos.add(alumno);
            return true;
        }
        System.out.println("Advertencia: El alumno con DNI " + alumno.getDni() + " ya existe.");
        return false;
    }

    /**
     * Busca un Alumno por su DNI.
     * @param dni El DNI del alumno a buscar.
     * @return El objeto Alumno si se encuentra, o null si no existe.
     */
    public Alumno buscarAlumno(String dni) {
        for (Alumno alumno : listaAlumnos) {
            if (alumno.getDni().equalsIgnoreCase(dni)) {
                return alumno;
            }
        }
        return null; // No se encontró el alumno
    }

    /**
     * Elimina un Alumno por su DNI.
     * Antes de eliminar un alumno, se recomienda revisar y posiblemente eliminar sus matrículas asociadas.
     * Por simplicidad, esta implementación solo elimina el alumno.
     * @param dni El DNI del alumno a eliminar.
     * @return true si el alumno se eliminó con éxito, false si no se encontró.
     */
    public boolean eliminarAlumno(String dni) {
        // Opcional: Lógica para eliminar matrículas asociadas antes de eliminar el alumno.
        // Ejemplo:
        // listaMatriculas.removeIf(m -> m.getAlumno() != null && m.getAlumno().getDni().equalsIgnoreCase(dni));

        return listaAlumnos.removeIf(alumno -> alumno.getDni().equalsIgnoreCase(dni));
    }

    /**
     * Obtiene una copia de la lista de todos los Alumnos.
     * Se devuelve una copia para proteger la lista interna de modificaciones externas directas.
     * @return Un ArrayList de Alumno.
     */
    public ArrayList<Alumno> getTodosLosAlumnos() {
        return new ArrayList<>(listaAlumnos);
    }

    /**
     * Obtiene el número total de Alumnos registrados.
     * @return El tamaño del ArrayList de alumnos.
     */
    public int getNumeroTotalAlumnos() {
        return listaAlumnos.size();
    }

    // --- Métodos de Gestión de Asignaturas ---

    /**
     * Añade una Asignatura a la colección.
     * Se asegura de que no se añadan asignaturas duplicadas (basado en el código, usando el método equals de Asignatura).
     * @param asignatura El objeto Asignatura a añadir.
     * @return true si la asignatura se añadió con éxito, false si ya existía.
     */
    public boolean añadirAsignatura(Asignatura asignatura) {
        if (asignatura == null) {
            System.err.println("Error: No se puede añadir una asignatura nula.");
            return false;
        }
        if (!listaAsignaturas.contains(asignatura)) {
            listaAsignaturas.add(asignatura);
            return true;
        }
        System.out.println("Advertencia: La asignatura con código " + asignatura.getCodigo() + " ya existe.");
        return false;
    }

    /**
     * Busca una Asignatura por su código.
     * @param codigo El código de la asignatura a buscar.
     * @return El objeto Asignatura si se encuentra, o null si no existe.
     */
    public Asignatura buscarAsignatura(String codigo) {
        for (Asignatura asignatura : listaAsignaturas) {
            if (asignatura.getCodigo().equalsIgnoreCase(codigo)) {
                return asignatura;
            }
        }
        return null; // No se encontró la asignatura
    }

    /**
     * Elimina una Asignatura por su código.
     * Similar a los alumnos, se recomienda revisar y posiblemente eliminar matrículas asociadas.
     * @param codigo El código de la asignatura a eliminar.
     * @return true si la asignatura se eliminó con éxito, false si no se encontró.
     */
    public boolean eliminarAsignatura(String codigo) {
        // Opcional: Lógica para eliminar matrículas asociadas antes de eliminar la asignatura.
        // Ejemplo:
        // listaMatriculas.removeIf(m -> m.getAsignatura() != null && m.getAsignatura().getCodigo().equalsIgnoreCase(codigo));

        return listaAsignaturas.removeIf(asignatura -> asignatura.getCodigo().equalsIgnoreCase(codigo));
    }

    /**
     * Obtiene una copia de la lista de todas las Asignaturas.
     * @return Un ArrayList de Asignatura.
     */
    public ArrayList<Asignatura> getTodasLasAsignaturas() {
        return new ArrayList<>(listaAsignaturas);
    }

    /**
     * Obtiene el número total de Asignaturas registradas.
     * @return El tamaño del ArrayList de asignaturas.
     */
    public int getNumeroTotalAsignaturas() {
        return listaAsignaturas.size();
    }

    // --- Métodos de Gestión de Ciclos Formativos ---

    /**
     * Añade un Ciclo Formativo a la colección.
     * Se asegura de que no se añadan ciclos duplicados (basado en el código, usando el método equals de CicloFormativo).
     * @param ciclo El objeto CicloFormativo a añadir.
     * @return true si el ciclo se añadió con éxito, false si ya existía.
     */
    public boolean añadirCicloFormativo(CicloFormativo ciclo) {
        if (ciclo == null) {
            System.err.println("Error: No se puede añadir un ciclo formativo nulo.");
            return false;
        }
        if (!listaCiclosFormativos.contains(ciclo)) {
            listaCiclosFormativos.add(ciclo);
            return true;
        }
        System.out.println("Advertencia: El ciclo formativo con código " + ciclo.getCodigo() + " ya existe.");
        return false;
    }

    /**
     * Busca un Ciclo Formativo por su código.
     * @param codigo El código del ciclo formativo a buscar.
     * @return El objeto CicloFormativo si se encuentra, o null si no existe.
     */
    public CicloFormativo buscarCicloFormativo(String codigo) {
        for (CicloFormativo ciclo : listaCiclosFormativos) {
            if (ciclo.getCodigo().equalsIgnoreCase(codigo)) {
                return ciclo;
            }
        }
        return null; // No se encontró el ciclo formativo
    }

    /**
     * Elimina un Ciclo Formativo por su código.
     * Similar a los anteriores, se recomienda revisar y posiblemente eliminar matrículas asociadas.
     * @param codigo El código del ciclo formativo a eliminar.
     * @return true si el ciclo se eliminó con éxito, false si no se encontró.
     */
    public boolean eliminarCicloFormativo(String codigo) {
        // Opcional: Lógica para eliminar matrículas asociadas antes de eliminar el ciclo.
        // Si tienes la relación en Matricula que referencia a CicloFormativo, aquí iría la limpieza.
        return listaCiclosFormativos.removeIf(ciclo -> ciclo.getCodigo().equalsIgnoreCase(codigo));
    }

    /**
     * Obtiene una copia de la lista de todos los Ciclos Formativos.
     * @return Un ArrayList de CicloFormativo.
     */
    public ArrayList<CicloFormativo> getTodosLosCiclosFormativos() {
        return new ArrayList<>(listaCiclosFormativos);
    }

    /**
     * Obtiene el número total de Ciclos Formativos registrados.
     * @return El tamaño del ArrayList de ciclos formativos.
     */
    public int getNumeroTotalCiclosFormativos() {
        return listaCiclosFormativos.size();
    }

    // --- Métodos de Gestión de Matrículas ---

    /**
     * Añade una nueva Matrícula al sistema.
     * Realiza validaciones para asegurar que el alumno y la asignatura existan antes de matricular.
     * @param idMatricula El ID único de la matrícula.
     * @param dniAlumno El DNI del alumno a matricular.
     * @param codigoAsignatura El código de la asignatura.
     * @param fechaMatriculacion La fecha de la matrícula.
     * @return true si la matrícula se añadió con éxito, false si el alumno/asignatura no existen o la matrícula ya existe.
     */
    public boolean añadirMatricula(String idMatricula, String dniAlumno, String codigoAsignatura, LocalDate fechaMatriculacion) {
        return añadirMatricula(idMatricula, dniAlumno, codigoAsignatura, fechaMatriculacion, null); // Llama a la sobrecarga con nota nula
    }

    /**
     * Añade una nueva Matrícula al sistema, incluyendo una nota inicial.
     * @param idMatricula El ID único de la matrícula.
     * @param dniAlumno El DNI del alumno a matricular.
     * @param codigoAsignatura El código de la asignatura.
     * @param fechaMatriculacion La fecha de la matrícula.
     * @param nota La nota inicial de la matrícula (puede ser null).
     * @return true si la matrícula se añadió con éxito, false si el alumno/asignatura no existen o la matrícula ya existe.
     */
    public boolean añadirMatricula(String idMatricula, String dniAlumno, String codigoAsignatura, LocalDate fechaMatriculacion, Double nota) {
        // Primero, verifica si la matrícula con ese ID ya existe
        if (buscarMatricula(idMatricula) != null) {
            System.out.println("Advertencia: Ya existe una matrícula con el ID " + idMatricula);
            return false;
        }

        // Busca el alumno y la asignatura para asegurar su existencia
        Alumno alumno = buscarAlumno(dniAlumno);
        Asignatura asignatura = buscarAsignatura(codigoAsignatura);

        if (alumno != null && asignatura != null) {
            Matricula nuevaMatricula = new Matricula(idMatricula, alumno, asignatura, fechaMatriculacion, nota);
            listaMatriculas.add(nuevaMatricula);
            return true;
        } else {
            System.err.println("Error: No se pudo añadir la matrícula. Verifique el DNI del alumno o el código de la asignatura.");
            return false;
        }
    }

    /**
     * Busca una Matrícula por su ID.
     * @param idMatricula El ID de la matrícula a buscar.
     * @return El objeto Matricula si se encuentra, o null si no existe.
     */
    public Matricula buscarMatricula(String idMatricula) {
        for (Matricula matricula : listaMatriculas) {
            if (matricula.getIdMatricula().equalsIgnoreCase(idMatricula)) {
                return matricula;
            }
        }
        return null; // No se encontró la matrícula
    }

    /**
     * Elimina una Matrícula por su ID.
     * @param idMatricula El ID de la matrícula a eliminar.
     * @return true si la matrícula se eliminó con éxito, false si no se encontró.
     */
    public boolean eliminarMatricula(String idMatricula) {
        return listaMatriculas.removeIf(matricula -> matricula.getIdMatricula().equalsIgnoreCase(idMatricula));
    }

    /**
     * Actualiza la nota de una matrícula existente.
     * @param idMatricula El ID de la matrícula cuya nota se desea actualizar.
     * @param nuevaNota La nueva nota a establecer (puede ser null).
     * @return true si la nota se actualizó con éxito, false si la matrícula no se encontró.
     */
    public boolean actualizarNotaMatricula(String idMatricula, Double nuevaNota) {
        Matricula matricula = buscarMatricula(idMatricula);
        if (matricula != null) {
            matricula.setNota(nuevaNota);
            System.out.println("Nota de la matrícula " + idMatricula + " actualizada a: " + (nuevaNota != null ? nuevaNota : "Sin asignar"));
            return true;
        }
        System.err.println("Error: Matrícula con ID " + idMatricula + " no encontrada para actualizar la nota.");
        return false;
    }

    /**
     * Obtiene una copia de la lista de todas las Matrículas.
     * @return Un ArrayList de Matricula.
     */
    public ArrayList<Matricula> getTodasLasMatriculas() {
        return new ArrayList<>(listaMatriculas);
    }

    /**
     * Obtiene el número total de Matrículas registradas.
     * @return El tamaño del ArrayList de matrículas.
     */
    public int getNumeroTotalMatriculas() {
        return listaMatriculas.size();
    }

    /**
     * Obtiene una lista de matrículas asociadas a un Alumno específico.
     * @param dniAlumno El DNI del alumno cuyas matrículas se desean obtener.
     * @return Un ArrayList de Matricula que pertenecen al alumno.
     */
    public ArrayList<Matricula> getMatriculasDeAlumno(String dniAlumno) {
        ArrayList<Matricula> matriculasDelAlumno = new ArrayList<>();
        for (Matricula matricula : listaMatriculas) {
            if (matricula.getAlumno() != null && matricula.getAlumno().getDni().equalsIgnoreCase(dniAlumno)) {
                matriculasDelAlumno.add(matricula);
            }
        }
        return matriculasDelAlumno;
    }

    /**
     * Obtiene una lista de matrículas asociadas a una Asignatura específica.
     * @param codigoAsignatura El código de la asignatura cuyas matrículas se desean obtener.
     * @return Un ArrayList de Matricula que pertenecen a la asignatura.
     */
    public ArrayList<Matricula> getMatriculasDeAsignatura(String codigoAsignatura) {
        ArrayList<Matricula> matriculasDeLaAsignatura = new ArrayList<>();
        for (Matricula matricula : listaMatriculas) {
            if (matricula.getAsignatura() != null && matricula.getAsignatura().getCodigo().equalsIgnoreCase(codigoAsignatura)) {
                matriculasDeLaAsignatura.add(matricula);
            }
        }
        return matriculasDeLaAsignatura;
    }

    // --- Métodos de Ordenación (pueden ser útiles si el Modelo también los expone) ---
    // Aunque la ordenación final para la visualización se hace en la Vista,
    // el Modelo podría tener métodos para ordenar sus colecciones internas si la lógica de negocio lo requiere.

    public void ordenarAlumnosPorDNI() {
        listaAlumnos.sort(Comparator.comparing(Alumno::getDni));
    }

    public void ordenarAsignaturasPorNombre() {
        listaAsignaturas.sort(Comparator.comparing(Asignatura::getNombre));
    }

    public void ordenarCiclosFormativosPorNombre() {
        listaCiclosFormativos.sort(Comparator.comparing(CicloFormativo::getNombre));
    }

    public void ordenarMatriculasPorId() {
        listaMatriculas.sort(Comparator.comparing(Matricula::getIdMatricula));
    }
}