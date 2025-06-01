package org.iesalandalus.programacion.matriculacion.modelo.dominio;

public class Grado {
    public String nombre;
    private String iniciales;
    private int numAnios; // Suponiendo que se añade aquí

    public Grado(String nombre) throws IllegalArgumentException {
        setNombre(nombre);
    }

    public Grado() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws IllegalArgumentException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del grado no puede ser nulo o vacío.");
        }
        this.nombre = nombre.trim();
        setIniciales(); // Actualizar iniciales al cambiar el nombre
    }

    public String getIniciales() {
        return iniciales;
    }

    // Se establece como private ya que se llama internamente al cambiar el nombre
    private void setIniciales() {
        if (this.nombre != null && !this.nombre.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String palabra : this.nombre.split(" ")) {
                if (!palabra.isEmpty()) {
                    sb.append(palabra.charAt(0));
                }
            }
            this.iniciales = sb.toString().toUpperCase();
        } else {
            this.iniciales = "";
        }
    }

    public int getNumAnios() {
        return numAnios;
    }

    public void setNumAnios(int numAnios) {
        this.numAnios = numAnios;
    }

    @Override
    public String toString() {
        return "(" + iniciales + ") - " + nombre;
    }
}