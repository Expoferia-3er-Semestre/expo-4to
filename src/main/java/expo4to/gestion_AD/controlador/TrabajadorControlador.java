package expo4to.gestion_AD.controlador;

import expo4to.gestion_AD.modelo.Representante;
import expo4to.gestion_AD.servicio.ITrabajadorServicio;
import expo4to.gestion_AD.servicio.TrabajadorServicio;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import expo4to.gestion_AD.modelo.Trabajador;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class TrabajadorControlador {

    private final ITrabajadorServicio trabajadorServicio;

    public List<Trabajador> listarTrabajadores() {

        try {
            return trabajadorServicio.listarTrabajadores();
        } catch (Exception e) {
            System.err.println("WARN: Error al listar: " + e.getMessage());
            return null;
        }

    }

    public String guardarTrabajador(Trabajador trabajador) {

        try {
            trabajadorServicio.guardarTrabajador(trabajador);
            return "Éxito: Trabajador guardado/actualizado.";
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error de Validación: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al guardar/actualizar: " + e.getMessage());
        }

    }

    public Trabajador buscarTrabajador(Integer id) {
        try {
            return trabajadorServicio.buscarTrabajadorPorId(id);
        } catch (NoSuchElementException e) {
            System.err.println("WARN: Trabajador no encontrado: " + e.getMessage());
            return null;
        }
    }

    public void eliminarRepresentante(Trabajador trabajador) {

        try {
            trabajadorServicio.eliminarTrabajador(trabajador);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar: " + e.getMessage());
        }

    }


}
