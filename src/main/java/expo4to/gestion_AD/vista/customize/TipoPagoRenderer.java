package expo4to.gestion_AD.vista.customize;

import expo4to.gestion_AD.dto.EstudianteDTO;
import expo4to.gestion_AD.dto.TipoPagoDTO;

import javax.swing.*;
import java.awt.*;

// Clase Renderer Personalizada (Mejor como clase interna o separada)
public class TipoPagoRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        // El método padre maneja el estilo (selección, foco, etc.)
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof TipoPagoDTO) {
            TipoPagoDTO tp = (TipoPagoDTO) value;
            // Definimos el texto que se mostrará
            setText(tp.getCategoria());
        } else {
            // Si el valor es null (típico para el primer item vacío)
            setText("");
        }
        return this;
    }
}