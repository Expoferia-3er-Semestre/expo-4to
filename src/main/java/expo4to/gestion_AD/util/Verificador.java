package expo4to.gestion_AD.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Component
public class Verificador {

    // Regex para validar Nombres y Apellidos (admite letras, tildes, ñ, espacios y apóstrofes)
    private static final String REGEX_NOMBRE_APELLIDO = "^[\\p{L}\\s']+$";
    private static final int LONGITUD_MINIMA = 2;
    private static final int LONGITUD_MAXIMA = 100;

    // Regex para validar Cédula (asumiendo formato de 8 a 12 dígitos numéricos en este ejemplo genérico)
    private static final String REGEX_CEDULA_GENERICA = "^\\d{8,12}$";

    // --- Métodos de Verificación ---

    /**
     * Verifica si un nombre o apellido es válido.
     * @param valor El nombre o apellido a verificar.
     * @return true si es válido (formato y longitud), false en caso contrario.
     */
    public boolean esNombreOApellidoValido(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return false;
        }
        String valorLimpio = valor.trim();

        // 1. Verificar Longitud
        if (valorLimpio.length() < LONGITUD_MINIMA || valorLimpio.length() > LONGITUD_MAXIMA) {
            System.out.println("Error: La longitud debe estar entre " + LONGITUD_MINIMA + " y " + LONGITUD_MAXIMA + " caracteres.");
            return false;
        }

        // 2. Verificar Formato (solo letras, espacios, guiones, apóstrofes)
        Pattern pattern = Pattern.compile(REGEX_NOMBRE_APELLIDO);
        Matcher matcher = pattern.matcher(valorLimpio);

        if (!matcher.matches()) {
            System.out.println("Error: El nombre contiene caracteres inválidos (solo letras, espacios, ' son permitidos).");
            return false;
        }

        return true;
    }

    /**
     * Verifica si una edad está dentro de un rango lógico.
     * @param edad La edad a verificar.
     * @return true si es válida (entre 0 y 90), false en caso contrario.
     */
    public boolean esEdadValida(int edad) {
        final int EDAD_MINIMA = 0;
        final int EDAD_MAXIMA = 90;

        if (edad < EDAD_MINIMA || edad > EDAD_MAXIMA) {
            System.out.println("Error: La edad debe estar entre " + EDAD_MINIMA + " y " + EDAD_MAXIMA + ".");
            return false;
        }
        return true;
    }

    /**
     * Verifica la validez de una Cédula/ID (Ejemplo Genérico).
     * NOTA: Para una validación estricta (p. ej., el dígito verificador) se necesita
     * el algoritmo específico del país (p. ej., Venezuela, Colombia, etc.).
     * @param cedula El número de cédula a verificar.
     * @return true si cumple el formato básico de longitud y números, false en caso contrario.
     */
    public boolean esCedulaValida(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            return false;
        }

        // 1. Verificar Formato y Longitud (solo números, entre 8 y 12 dígitos)
        if (!cedula.matches(REGEX_CEDULA_GENERICA)) {
            System.out.println("Error: La cédula debe contener solo números y tener entre 8 y 12 dígitos.");
            return false;
        }

        // 2. Aquí iría la validación del DÍGITO VERIFICADOR específica del país.
        // if (!validarDigitoVerificador(cedula)) {
        //     System.out.println("Error: El dígito verificador de la cédula no es válido.");
        //     return false;
        // }

        return true;
    }

    /**
     * Regex estándar de Internet para un formato de correo electrónico robusto y común.
     * Permite letras, números, puntos, guiones y guiones bajos en el nombre de usuario y subdominios.
     * Requisitos:
     * 1. Comienza con uno o más caracteres válidos.
     * 2. Le sigue un '@'.
     * 3. Le sigue uno o más subdominios y el dominio principal.
     * 4. Termina con la extensión (ej. .com, .net) de 2 a 6 letras/dígitos.
     */
    private static final String REGEX_CORREO =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    /**
     * Verifica si una cadena tiene el formato estándar de correo electrónico.
     * @param correo El correo electrónico a verificar.
     * @return true si cumple con el formato estándar, false en caso contrario.
     */
    public boolean esCorreoValido(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            System.out.println("Error: El correo no puede estar vacío.");
            return false;
        }

        String correoLimpio = correo.trim();

        // El método matches() de la clase String es un atajo para Pattern.compile(regex).matcher(input).matches()
        if (!correoLimpio.matches(REGEX_CORREO)) {
            System.out.println("Error: El formato del correo electrónico es incorrecto.");
            return false;
        }

        // Validación adicional simple para evitar que comience o termine con punto o arroba
        if (correoLimpio.startsWith(".") || correoLimpio.endsWith(".") ||
                correoLimpio.startsWith("@") || correoLimpio.endsWith("@")) {
            System.out.println("Error: El correo no puede empezar o terminar con '.' o '@'.");
            return false;
        }

        return true;
    }

    /**
     * Regex para números móviles de 11 dígitos en Venezuela.
     * Permite los formatos: 04XX-XXXXXXX, 04XXXXXXXXX, (04XX) XXX-XXXX, etc.
     * La parte principal de la regex es ^0(412|414|416|424|426)\d{7}$
     */
    private static final String REGEX_TELEFONO_VE =
            "^\\s*(?:\\+?58)?\\s*\\(?(?:0412|0414|0416|0424|0426)\\)?[-.\\s]?\\d{3}[-.\\s]?\\d{4}\\s*$";

    /**
     * Verifica si una cadena corresponde a un número de teléfono móvil válido en Venezuela.
     * La verificación se basa en la longitud (11 dígitos) y los prefijos de operador.
     * @param telefono El número de teléfono a verificar.
     * @return true si cumple con el formato venezolano, false en caso contrario.
     */
    public boolean esTelefonoValido(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            System.out.println("Error: El número de teléfono no puede estar vacío.");
            return false;
        }

        // 1. Limpiar el número de formato: Deja solo dígitos y el prefijo internacional (+58 si existe)
        // Eliminamos todo lo que no sea dígito ni el '+'
        String telefonoLimpio = telefono.replaceAll("[^0-9+]", "");

        // 2. Normalizar el prefijo internacional si existe (+58)
        if (telefonoLimpio.startsWith("+58")) {
            // Quitamos el +58, dejando el número en el formato 04XXYYYYYYY (11 dígitos)
            telefonoLimpio = "0" + telefonoLimpio.substring(3);
        }

        // 3. Verificar si el número normalizado cumple con el patrón 04XX... y tiene 11 dígitos.
        // Patrón estricto: comienza con 0, seguido de un código de operador válido, seguido de 7 dígitos.
        if (!telefonoLimpio.matches("^0(412|414|416|424|426)\\d{7}$")) {
            System.out.println("Error: El formato de teléfono es incorrecto o el código de operador es inválido. Debe ser 04XX-XXXXXXX (11 dígitos).");
            return false;
        }

        return true;
    }

    // Regex permisiva para direcciones. Permite letras, números, espacios,
    // y los símbolos comunes en direcciones (#, -, /, ,, .).
    private static final String REGEX_DIRECCION =
            "^[a-zA-Z0-9#\\s\\-,./ñÑáéíóúÁÉÍÓÚ]+$";
    private static final int LONGITUD_MINIMA_DIRECCION = 15;
    private static final int LONGITUD_MAXIMA_DIRECCION = 255;

    /**
     * Verifica la validez de un campo de dirección física.
     * La verificación se basa en longitud y un conjunto de caracteres seguros.
     * @param direccion La dirección a verificar.
     * @return true si cumple con los estándares, false en caso contrario.
     */
    public boolean esDireccionValida(String direccion) {
        if (direccion == null || direccion.trim().isEmpty()) {
            System.out.println("Error: La dirección no puede estar vacía.");
            return false;
        }

        String direccionLimpia = direccion.trim();

        // 1. Verificar Longitud
        int longitud = direccionLimpia.length();
        if (longitud < LONGITUD_MINIMA_DIRECCION || longitud > LONGITUD_MAXIMA_DIRECCION) {
            System.out.println("Error: La dirección debe tener entre " + LONGITUD_MINIMA_DIRECCION + " y " + LONGITUD_MAXIMA_DIRECCION + " caracteres.");
            return false;
        }

        // 2. Verificar Formato (Caracteres Seguros)
        if (!direccionLimpia.matches(REGEX_DIRECCION)) {
            System.out.println("Error: La dirección contiene caracteres inválidos. Solo se permiten letras, números, espacios, y los símbolos #, -, /, ,, .");
            return false;
        }

        return true;
    }

    // --- 2. VALIDACIÓN ECONÓMICA Y DE LÍMITES ---

    /**
     * Verifica que un monto numérico NO sea negativo.
     * Se recomienda usar BigDecimal para dinero para evitar errores de punto flotante.
     * @param monto El valor a verificar.
     * @return true si el monto es cero o positivo (>= 0).
     */
    public boolean esMontoPositivo(BigDecimal monto) {
        if (monto == null) {
            return false; // El monto no debe ser nulo en transacciones
        }
        // Compara si el monto es mayor o igual a cero (BigDecimal.ZERO)
        return monto.compareTo(BigDecimal.ZERO) >= 0;
    }

    // Acepta números enteros o decimales (con o sin signo)
    private static final Pattern NUMERO_DECIMAL_PATTERN = Pattern.compile("^-?\\d+(\\.\\d+)?$");

    // --- 1. VALIDACIÓN DE FORMATO GENERAL Y PRECISIÓN ---

    /**
     * Verifica si una cadena de texto es un número decimal válido (ej. "100.50").
     * @param cadena El string a verificar (ej. "100.50").
     * @return true si es un número con el formato correcto.
     */
    public boolean esNumeroDecimalValido(String cadena) {
        if (cadena == null || cadena.trim().isEmpty()) {
            return false;
        }
        return NUMERO_DECIMAL_PATTERN.matcher(cadena.trim()).matches();
    }

    /**
     * Verifica que el texto de un monto no exceda el número máximo de decimales.
     * Crucial para manejar dinero (generalmente 2 decimales).
     * @param montoString La cadena del monto a verificar.
     * @param maxDecimales El número máximo de decimales permitidos (ej. 2).
     * @return true si el número de decimales es igual o menor a maxDecimales.
     */
    public boolean tieneMaximoDecimales(String montoString, int maxDecimales) {
        if (!esNumeroDecimalValido(montoString)) {
            return false;
        }

        String texto = montoString.trim();
        int indicePunto = texto.indexOf('.');

        // Si no hay punto, siempre es válido (ej. "100")
        if (indicePunto == -1) {
            return true;
        }

        // Contar los dígitos después del punto
        int decimalesActuales = texto.length() - 1 - indicePunto;

        return decimalesActuales <= maxDecimales;
    }

    // --- 2. VALIDACIÓN DE REGLA DE NEGOCIO (POST-CONVERSIÓN) ---

    /**
     * Verifica que un monto BigDecimal NO sea negativo.
     * @param monto El valor BigDecimal a verificar.
     * @return true si el monto es cero o positivo (>= 0).
     */
    public boolean esMontoPositivoOCero(BigDecimal monto) {
        if (monto == null) {
            return false;
        }
        // Compara si el monto es mayor o igual a cero (BigDecimal.ZERO)
        return monto.compareTo(BigDecimal.ZERO) >= 0;
    }

    // --- Ejemplo de Uso ---
    public static void main(String[] args) {
        Verificador verificador = new Verificador();

        System.out.println("\n--- Verificación de Nombres ---");
        System.out.println("Javier: " + verificador.esNombreOApellidoValido("Javier")); // true
        System.out.println("O'Connell: " + verificador.esNombreOApellidoValido("O'Connell")); // true
        System.out.println("Pedro123: " + verificador.esNombreOApellidoValido("Pedro123")); // false
        System.out.println("   Ana   : " + verificador.esNombreOApellidoValido("   Ana   ")); // true (maneja espacios)

        System.out.println("\n--- Verificación de Edad ---");
        System.out.println("25: " + verificador.esEdadValida(25)); // true
        System.out.println("-5: " + verificador.esEdadValida(-5)); // false
        System.out.println("130: " + verificador.esEdadValida(130)); // false

        System.out.println("\n--- Verificación de Cédula (Genérica) ---");
        System.out.println("12345678: " + verificador.esCedulaValida("12345678")); // true
        System.out.println("12345: " + verificador.esCedulaValida("12345")); // false
        System.out.println("123-456: " + verificador.esCedulaValida("123-456")); // false
    }
}
