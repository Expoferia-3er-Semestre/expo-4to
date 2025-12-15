package expo4to.gestion_AD.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {

    /**
     * Convierte una cadena de fecha en formato "yy/MM/dd" a un objeto java.sql.Date.
     * * @param fechaString La cadena de fecha ingresada por el usuario (ej: "95/12/31").
     * @return El objeto java.sql.Date, o null si la conversión falla.
     */
    public static Date convertirStringA_SqlDate(String fechaString) {

        // 1. Definir el formato de entrada
        // Nota: 'yy' asume el siglo 2000 si el año es 00-49 y el siglo 1900 si es 50-99.
        final String FORMATO_ENTRADA = "yyyy/MM/dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_ENTRADA);

        if (fechaString.isEmpty()) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede quedar vacía.");
        }

        try {
            // 2. Parsear la cadena a LocalDate (el tipo moderno de fecha)
            LocalDate localDate = LocalDate.parse(fechaString, formatter);

            // 3. Convertir LocalDate a java.sql.Date
            return Date.valueOf(localDate);

        } catch (DateTimeParseException e) {
            // Manejo de errores: Si la cadena no coincide con el formato
            throw new IllegalArgumentException("Asegúrese que el formato de la fecha sea yyyy/MM/dd.");
            // Puedes lanzar una excepción de aplicación aquí o retornar null/fecha por defecto.
        }
    }

    public static boolean determinarNivelAcademicoPorSufijo(String gradoString) throws IllegalArgumentException {

        if (gradoString == null || gradoString.trim().isEmpty()) {
            throw new IllegalArgumentException("El grado no puede estar vacío.");
        }

        String gradoLimpio = gradoString.trim().toLowerCase(); // Trabajar con minúsculas y sin espacios

        // 1. Detección y Clasificación por Sufijo

        boolean esPrimaria;

        if (gradoLimpio.contains("°")) {
            esPrimaria = true; // Primaria: Contiene el símbolo de grado (ej: 1°, 6°)
        } else if (gradoLimpio.contains("año") || gradoLimpio.contains("er") || gradoLimpio.contains("do") || gradoLimpio.contains("ro")) {
            // Secundaria: Contiene "año" o alguna terminología ordinal de secundaria (ej: 1er, 2do, 5to año)
            esPrimaria = false;
        } else {
            // Si no contiene ninguno de los sufijos de clasificación conocidos
            throw new IllegalArgumentException("Formato de grado no reconocido. Use 'N°' para Primaria o 'N año'/'Nto año' para Secundaria.");
        }

        // 3. Retornar la clasificación basada en el sufijo
        return esPrimaria;
    }

}
