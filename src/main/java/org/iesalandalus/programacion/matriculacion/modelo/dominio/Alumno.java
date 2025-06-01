package org.iesalandalus.programacion.matriculacion.modelo.dominio;

// Archivo: Alumno.java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Clase que representa a un alumno en el sistema.
 */
public class Alumno extends Asignatura {
    private  int id;
    private String dni;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     * Constructor para crear un nuevo objeto Alumno.
     * @param dni El DNI único del alumno.
     * @param nombre El nombre del alumno.
     * @param apellidos Los apellidos del alumno.
     * @param fechaNacimiento La fecha de nacimiento del alumno.
     */
    public Alumno(String dni, String nombre, String apellidos, LocalDate fechaNacimiento) {
        super();
        // Validación básica de parámetros no nulos
        if (dni == null || nombre == null || apellidos == null || fechaNacimiento == null) {
            throw new IllegalArgumentException("Todos los campos del alumno deben ser no nulos.");
        }
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Alumno(int id, String dni, String nombre, String apellidos) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Alumno(String dni, String nombre, String apellidos) {
        this(0, dni, nombre, apellidos); // Llama al constructor de 4 argumentos para reutilizar código
    }


    // --- Getters ---
    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    // --- Setters (solo si se permite la modificación posterior de estos campos) ---
    // Generalmente, DNI no debería ser modificable.
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        if (apellidos == null || apellidos.trim().isEmpty()) {
            throw new IllegalArgumentException("Los apellidos no pueden ser nulos o vacíos.");
        }
        this.apellidos = apellidos;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Compara dos objetos Alumno por su DNI.
     * @param o El objeto a comparar.
     * @return true si los DNI son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(dni, alumno.dni); // Los alumnos son iguales si tienen el mismo DNI
    }

    /**
     * Genera un código hash basado en el DNI del alumno.
     * @return El código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    /**
     * Devuelve una representación en cadena del objeto Alumno.
     * @return String con la información del alumno.
     */
    @Override
    public String toString() {
        return "Alumno [DNI: " + dni + ", Nombre: " + nombre + ", Apellidos: " + apellidos +
                ", Fecha Nacimiento: " + fechaNacimiento.format(FORMATO_FECHA) + "]";
    }
}