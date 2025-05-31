package org.iesalandalus.programacion.matriculacion.vista;

import org.iesalandalus.programacion.matriculacion.controlador.Controlador;
import org.iesalandalus.programacion.matriculacion.modelo.Modelo;

// Archivo: Consola.java
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Consola {
    private Scanner scanner;

    public Consola() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Lee una cadena de texto desde la consola.
     * @param mensaje El mensaje a mostrar al usuario.
     * @return La cadena de texto introducida por el usuario.
     */
    public String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    /**
     * Lee un número entero desde la consola, con validación de entrada.
     * @param mensaje El mensaje a mostrar al usuario.
     * @return El número entero introducido por el usuario.
     */
    public int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea
                return valor;
            } catch (InputMismatchException e) {
                System.err.println("Error: Por favor, introduzca un número entero válido.");
                scanner.nextLine(); // Limpiar el buffer del scanner
            }
        }
    }

    /**
     * Lee un número double desde la consola, con validación de entrada.
     * Permite introducir "null" para un valor nulo (útil para notas, etc.).
     * @param mensaje El mensaje a mostrar al usuario.
     * @return El número double introducido, o null si el usuario introduce "null".
     */
    public Double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("null") || input.isEmpty()) {
                return null;
            }
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.err.println("Error: Por favor, introduzca un número decimal válido o 'null'.");
            }
        }
    }

    /**
     * Lee una fecha en formato AAAA-MM-DD desde la consola, con validación.
     * @param mensaje El mensaje a mostrar al usuario.
     * @return Un objeto LocalDate.
     */
    public LocalDate leerFecha(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String fechaStr = scanner.nextLine();
            try {
                return LocalDate.parse(fechaStr);
            } catch (DateTimeParseException e) {
                System.err.println("Error: Formato de fecha incorrecto. Use AAAA-MM-DD.");
            }
        }
    }

    /**
     * Cierra el objeto Scanner para liberar los recursos del sistema.
     * Es crucial llamarlo al finalizar la aplicación.
     */
    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}