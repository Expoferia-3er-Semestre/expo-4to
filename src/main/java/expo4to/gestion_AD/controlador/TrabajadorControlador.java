package expo4to.gestion_AD.controlador;

import expo4to.gestion_AD.dto.TrabajadorDTO;
import expo4to.gestion_AD.servicio.ITrabajadorServicio;
import expo4to.gestion_AD.util.CifradorContrasenas;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class TrabajadorControlador {

    private final ITrabajadorServicio trabajadorServicio;

    @Autowired
    CifradorContrasenas cifrador;

    public List<TrabajadorDTO> listarTrabajadores() {

        try {
            return trabajadorServicio.listarTrabajadores();
        } catch (Exception e) {
            System.err.println("WARN: Error al listar: " + e.getMessage());
            return null;
        }

    }

    public String guardarTrabajador(TrabajadorDTO trabajadorDTO) {

        try {
            trabajadorServicio.guardarTrabajador(trabajadorDTO);
            return "Éxito: Trabajador guardado/actualizado.";
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error de Validación: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al guardar/actualizar: " + e.getMessage());
        }

    }

    public TrabajadorDTO buscarTrabajador(Integer id) {
        try {
            return trabajadorServicio.buscarTrabajadorPorId(id);
        } catch (NoSuchElementException e) {
            System.err.println("WARN: Trabajador no encontrado: " + e.getMessage());
            return null;
        }
    }

    public void eliminarTrabajador(Integer id) {

        try {
            trabajadorServicio.eliminarTrabajador(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar: " + e.getMessage());
        }

    }

    public TrabajadorDTO loginTrabajador(String correo, JPasswordField campoContrasena) {

        String contraIngresada = new String(campoContrasena.getPassword());

        try {
            TrabajadorDTO dto = trabajadorServicio.buscarTrabajadorPorCorreo(correo);

            if (dto != null) {

                // 2. Comprobar la contraseña
                if (cifrador.compararContrasenas(contraIngresada, dto.getContrasena())) {

                    // Limpiar la contraseña en memoria inmediatamente después de usarla
                    // Nota: new String(char[]) debe ser manejada con cuidado, pero es el estándar en Swing.
                    campoContrasena.setText("");

                    return dto; // Éxito en el login

                } else {
                    // 3. 🛑 ERROR: Contraseña incorrecta (Credenciales inválidas)
                    throw new IllegalArgumentException("Contraseña incorrecta para el usuario: " + correo);
                }

            } else {
                // 4. 🛑 ERROR: Trabajador no encontrado
                throw new NoSuchElementException("Trabajador con correo " + correo + " no encontrado.");
            }

        } catch (NoSuchElementException e) {
            // Captura si el usuario no existe
            System.err.println("LOGIN FALLIDO: " + e.getMessage());
            throw new IllegalArgumentException("Usuario o contraseña incorrectos.");

        } catch (IllegalArgumentException e) {
            // Captura si la contraseña fue incorrecta
            System.err.println("LOGIN FALLIDO: " + e.getMessage());
            throw new IllegalArgumentException("Usuario o contraseña incorrectos.");

        } catch (Exception e) {
            // Captura cualquier otro error inesperado (conexión DB, NullPointer, etc.)
            System.err.println("ERROR INESPERADO en login: " + e.getMessage());
            throw new RuntimeException("Error interno al intentar iniciar sesión.");
        }
    }

}
