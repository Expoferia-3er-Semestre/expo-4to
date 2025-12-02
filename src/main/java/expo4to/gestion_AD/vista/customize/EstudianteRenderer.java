package expo4to.gestion_AD.vista.customize;

import expo4to.gestion_AD.dto.EstudianteDTO;

import javax.swing.*;
import java.awt.*;

// Clase Renderer Personalizada (Mejor como clase interna o separada)
public class EstudianteRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        // El método padre maneja el estilo (selección, foco, etc.)
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof EstudianteDTO) {
            EstudianteDTO estudiante = (EstudianteDTO) value;
            // Definimos el texto que se mostrará
            setText(estudiante.getNombre1() + " " + estudiante.getApellido1());
        } else {
            // Si el valor es null (típico para el primer item vacío)
            setText("");
        }
        return this;
    }
}