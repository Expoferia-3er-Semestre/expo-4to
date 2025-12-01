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
        try {
            TrabajadorDTO dto = trabajadorServicio.buscarTrabajadorPorCorreo(correo);

            if (dto != null) {
                String contraIngresada = new String(campoContrasena.getPassword());
                if (cifrador.compararContrasenas(contraIngresada, dto.getContrasena())) {
                    return dto;
                } else {
                    throw new NoSuchElementException("Correo o contraseña incorrecta.");
                }
            } else {
                throw new IllegalArgumentException("Correo o contraseña incorrecta.");
            }


        } catch (Exception e) {
            System.err.println("ERROR: Ocurrió un error al intentar iniciar sesión.");
            return null;
        }

    }

}
