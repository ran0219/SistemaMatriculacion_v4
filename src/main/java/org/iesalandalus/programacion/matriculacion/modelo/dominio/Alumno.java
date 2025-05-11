package org.iesalandalus.programacion.matriculacion.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alumno {

    private static final int MAX_ALUMNOS = 100;
    private Alumno[] alumnos;
    private int numAlumnos;

    public void Alumnos() {
        alumnos = new Alumno[MAX_ALUMNOS];
        numAlumnos = 0;
    }

    private String nombre;
    private String telefono;
    private String email;
    private String dni;
    private LocalDate fechaNacimiento;
    private String nia;

    public static final String FORMATO_FECHA = "dd/MM/yyyy";
    public static final int MIN_EDAD_ALUMNADO = 12;
    public static final String REGEX_DNI = "^(\\d{8})([A-Z])$";
    public static final String REGEX_TELEFONO = "^\\d{9}$";
    public static final String REGEX_EMAIL = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public Alumno(String nombre, String telefono, String email, String dni, LocalDate fechaNacimiento) {
        setNombre(nombre);
        setTelefono(telefono);
        setEmail(email);
        setDni(dni);
        setFechaNacimiento(fechaNacimiento);
        this.nia = generaNia();
    }

    public Alumno(Alumno alumno) {
        this(alumno.nombre, alumno.telefono, alumno.email, alumno.dni, alumno.fechaNacimiento);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = formateaNombre(nombre);
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || !telefono.matches(REGEX_TELEFONO)) {
            throw new IllegalArgumentException("El teléfono debe tener 9 dígitos.");
        }
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches(REGEX_EMAIL)) {
            throw new IllegalArgumentException("El email no es válido.");
        }
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni == null || !dni.matches(REGEX_DNI) || !comprobarLetraDni(dni)) {
            throw new IllegalArgumentException("El DNI no es válido.");
        }
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula.");
        }
        if (Period.between(fechaNacimiento, LocalDate.now()).getYears() < MIN_EDAD_ALUMNADO) {
            throw new IllegalArgumentException("El alumno debe tener al menos " + MIN_EDAD_ALUMNADO + " años.");
        }
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNia() {
        return nia;
    }

    private String generaNia() {
        return nombre.substring(0, 4).toLowerCase() + dni.substring(dni.length() - 3);
    }

    public String formateaNombre(String nombre) {
        String[] palabras = nombre.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String palabra : palabras) {
            sb.append(palabra.substring(0, 1).toUpperCase()).append(palabra.substring(1).toLowerCase()).append(" ");
        }
        return sb.toString().trim();
    }

    public boolean comprobarLetraDni(String dni) {
        Pattern pattern = Pattern.compile(REGEX_DNI);
        Matcher matcher = pattern.matcher(dni);
        if (matcher.matches()) {
            int numeroDni = Integer.parseInt(matcher.group(1));
            char letraCalculada = "TRWAGMYFPDXBNJZSQVHLCKE".charAt(numeroDni % 23);
            return letraCalculada == matcher.group(2).charAt(0);
        }
        return false;
    }

    public String getIniciales() {
        String[] palabras = nombre.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String palabra : palabras) {
            sb.append(palabra.charAt(0));
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return dni.equals(alumno.dni);
    }

    @Override
    public int hashCode() {
        return dni.hashCode();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        return String.format("%s, %s, %s, %s, %s, %s", nombre, telefono, email, dni, fechaNacimiento.format(formatter), nia);
    }

    public String imprimir() {
        return toString();
    }
}
