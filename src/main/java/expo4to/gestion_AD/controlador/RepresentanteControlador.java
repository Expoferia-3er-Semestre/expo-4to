package expo4to.gestion_AD.controlador;

import expo4to.gestion_AD.modelo.Estudiante;
import expo4to.gestion_AD.modelo.Representante;
import expo4to.gestion_AD.servicio.IRepresentanteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class RepresentanteControlador {

    private final IRepresentanteServicio representanteServicio;

    public String guardarRepresentante(Representante representante) {
        try {

            representanteServicio.guardarRepresentante(representante);
            return "Éxito: Representante guardado/actualizado.";

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error de Validación: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al guardar: " + e.getMessage());
        }
    }

    public Representante buscarRepresentante(Integer id) {

        try {
            return representanteServicio.buscarRepresentantePorId(id);
        } catch (NoSuchElementException e) {
            System.err.println("WARN: Representante no encontrado: " + e.getMessage());
            return null;
        }

    }

}
