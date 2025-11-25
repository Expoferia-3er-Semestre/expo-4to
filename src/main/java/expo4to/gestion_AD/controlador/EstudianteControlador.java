package expo4to.gestion_AD.controlador;

import expo4to.gestion_AD.modelo.Estudiante;
import expo4to.gestion_AD.servicio.IEstudianteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class EstudianteControlador {

    private final IEstudianteServicio estudianteServicio;

    public String guardarEstudiante(Estudiante estudiante) {
        try {

            estudianteServicio.guardarEstudiante(estudiante);
            return "Éxito: Estudiante guardado/actualizado."; // Retorna el mensaje de éxito

        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error de Validación: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al guardar.", e);
        }
    }

    public Estudiante buscarEstudiante(Integer id) {

        try {
            return estudianteServicio.buscarEstudiantePorId(id);
        } catch (NoSuchElementException e) {
            System.err.println("WARN: Estudiante no encontrado: " + e.getMessage());
            return null;
        }
    }



}
