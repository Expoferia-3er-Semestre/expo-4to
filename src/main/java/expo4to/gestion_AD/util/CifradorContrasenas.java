package expo4to.gestion_AD.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Clase para manejar el hashing seguro de contraseñas usando BCrypt.
 * BCrypt es un algoritmo unidireccional (hashing) diseñado para ser lento, 
 * lo cual lo hace resistente a ataques de fuerza bruta.
 */
@Component
public class CifradorContrasenas {

    // Inicializamos el codificador de BCrypt. 
    // El 'strength' (fuerza, por defecto 10) define cuántas iteraciones se hacen.
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Cifra (hashea) la contraseña plana usando BCrypt.
     * @param passwordPlana La contraseña original ingresada por el usuario.
     * @return El hash de la contraseña, incluyendo el salt.
     */
    public String cifrarContrasena(String passwordPlana) {
        if (passwordPlana == null) {
            return null;
        }
        // El método encode() genera un nuevo salt aleatorio y hashea la contraseña.
        return passwordEncoder.encode(passwordPlana);
    }

    /**
     * Compara una contraseña plana ingresada por el usuario (en el login) 
     * con el hash almacenado en la base de datos.
     * BCrypt rehace el hash de la contraseña plana y compara si coincide con el hash almacenado.
     * @param passwordIngresada La contraseña escrita por el usuario.
     * @param hashAlmacenado El hash de la contraseña guardado en la base de datos.
     * @return true si las contraseñas coinciden, false en caso contrario.
     */
    public boolean compararContrasenas(String passwordIngresada, String hashAlmacenado) {
        if (passwordIngresada == null || hashAlmacenado == null) {
            return false;
        }
        return passwordEncoder.matches(passwordIngresada, hashAlmacenado);
    }

    // --- NOTA IMPORTANTE DE SEGURIDAD ---
    /* * EL MÉTODO 'DESCIFRAR' NO EXISTE EN ESTA CLASE.
     * NUNCA debes descifrar una contraseña almacenada.
     * Si un atacante roba tu base de datos, solo obtendrá hashes,
     * no las contraseñas originales.
     */

    // --- Ejemplo de Uso ---
    public static void main(String[] args) {
        CifradorContrasenas cifrador = new CifradorContrasenas();
        String contrasenaOriginal = "MiContrasenaSegura123";

        // 1. Cifrar (Hashing)
        String hashGenerado = cifrador.cifrarContrasena(contrasenaOriginal);
        System.out.println("Contraseña Original: " + contrasenaOriginal);
        System.out.println("Hash Almacenado (DB): " + hashGenerado);
        System.out.println("Longitud del Hash: " + hashGenerado.length()); // Típicamente 60 caracteres

        // --- Simulación de Login ---

        // 2. Comparación (Ingreso Exitoso)
        String intentoCorrecto = contrasenaOriginal;
        boolean exito = cifrador.compararContrasenas(intentoCorrecto, hashGenerado);
        System.out.println("\nIntento Correcto: " + intentoCorrecto);
        System.out.println("Coincide con el Hash? " + exito); // Debe ser true

        // 3. Comparación (Ingreso Fallido)
        String intentoIncorrecto = "contrasenaIncorrecta";
        boolean fallo = cifrador.compararContrasenas(intentoIncorrecto, hashGenerado);
        System.out.println("Intento Incorrecto: " + intentoIncorrecto);
        System.out.println("Coincide con el Hash? " + fallo); // Debe ser false
    }
}