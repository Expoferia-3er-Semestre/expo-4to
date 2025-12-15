package expo4to.gestion_AD.controlador;

import expo4to.gestion_AD.dto.RepresentanteDTO;
import expo4to.gestion_AD.modelo.Estudiante;
import expo4to.gestion_AD.modelo.Representante;
import expo4to.gestion_AD.servicio.IRepresentanteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class RepresentanteControlador {

    private final IRepresentanteServicio representanteServicio;

    public List<RepresentanteDTO> listarRepresentantes() {

        try {
            return representanteServicio.listarRepresentantes();
        } catch (Exception e) {
            System.err.println("WARN: Error al listar: " + e.getMessage());
            return null;
        }
    }

    public String guardarRepresentante(RepresentanteDTO representanteDTO) {
        try {

            representanteServicio.guardarRepresentante(representanteDTO);
            return "Éxito: Representante guardado/actualizado.";

        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException("Error inesperado al guardar: " + e.getMessage());
        }
    }

    public RepresentanteDTO buscarRepresentantePorCedula(String cedula) {

        try {

            return representanteServicio.buscarRepresentantePorCedula(cedula);

        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Error: El representante no existe en el sistema.");
        } catch (Exception e) {
            System.err.println("WARN: Ocurrió un error al buscar al representante: " + e.getMessage());
            return null;
        }

    }

    public void eliminarRepresentante(Integer id) {

        try {
            representanteServicio.eliminarRepresentante(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar: " + e.getMessage());
        }

    }

}
