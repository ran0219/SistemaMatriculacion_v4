package org.iesalandalus.programacion.matriculacion;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase de utilidad para manejar la entrada por teclado del usuario,
 * incluyendo validación para prevenir excepciones.
 */
public class Entrada {
    private static Scanner teclado = new Scanner(System.in);

    /**
     * Lee una cadena de texto (String) introducida por el usuario.
     * @param mensaje El mensaje o prompt a mostrar al usuario.
     * @return La cadena de texto leída.
     */
    public static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine().trim(); // Usar trim() para eliminar espacios en blanco al inicio/final
    }

    /**
     * Lee un número entero (int) introducido por el usuario, con validación.
     * Repite la petición hasta que se introduzca un entero válido.
     * @param mensaje El mensaje o prompt a mostrar al usuario.
     * @return El número entero leído.
     */
    public static int leerEntero(String mensaje) {
        int valor = 0;
        boolean entradaValida = false;
        do {
            System.out.print(mensaje);
            String linea = teclado.nextLine();
            try {
                valor = Integer.parseInt(linea);
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduce un número entero válido.");
            }
        } while (!entradaValida);
        return valor;
    }

    /**
     * Lee un número decimal (double) introducido por el usuario, con validación.
     * Repite la petición hasta que se introduzca un double válido.
     * @param mensaje El mensaje o prompt a mostrar al usuario.
     * @return El número decimal leído.
     */
    public static double leerDouble(String mensaje) {
        double valor = 0.0;
        boolean entradaValida = false;
        do {
            System.out.print(mensaje);
            String linea = teclado.nextLine();
            try {
                valor = Double.parseDouble(linea);
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduce un número decimal válido.");
            }
        } while (!entradaValida);
        return valor;
    }

    /**
     * Lee una fecha (LocalDate) en formato YYYY-MM-DD, con validación.
     * Repite la petición hasta que se introduzca una fecha válida.
     * @param mensaje El mensaje o prompt a mostrar al usuario.
     * @return La fecha leída como un objeto LocalDate.
     */
    public static LocalDate leerFecha(String mensaje) {
        LocalDate fecha = null;
        boolean entradaValida = false;
        do {
            System.out.print(mensaje + " (formato YYYY-MM-DD): ");
            String fechaStr = teclado.nextLine().trim();
            try {
                fecha = LocalDate.parse(fechaStr);
                entradaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha incorrecto. Por favor, usa YYYY-MM-DD.");
            }
        } while (!entradaValida);
        return fecha;
    }

    /**
     * Cierra el objeto Scanner utilizado para la entrada por teclado.
     * Debe llamarse al finalizar la aplicación para liberar recursos.
     */
    public static void cerrarScanner() {
        if (teclado != null) {
            teclado.close();
            teclado = null; // Evitar que se intente usar un scanner cerrado
        }
    }
}