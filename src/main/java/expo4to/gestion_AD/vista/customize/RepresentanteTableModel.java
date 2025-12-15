package expo4to.gestion_AD.vista.customize;

import expo4to.gestion_AD.dto.RepresentanteDTO;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class RepresentanteTableModel extends AbstractTableModel {

    private List<RepresentanteDTO> representantes;
    private final String[] nombresColumnas = { "Cédula", "Nombre", "Apellido", "Teléfono"};

    public RepresentanteTableModel(List<RepresentanteDTO> representantes) {
        this.representantes = representantes;
    }

    // Método para actualizar los datos de la tabla
    public void setRepresentantes(List<RepresentanteDTO> representantes) {
        this.representantes = representantes;
        fireTableDataChanged(); // Notifica a la tabla que los datos han cambiado
    }

    @Override
    public int getRowCount() {
        return representantes.size();
    }

    @Override
    public int getColumnCount() {
        return nombresColumnas.length;
    }

    @Override
    public String getColumnName(int columna) {
        return nombresColumnas[columna];
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        RepresentanteDTO r = representantes.get(fila);

        // Determina qué campo mostrar basado en el índice de la columna
        switch (columna) {
            case 0: return r.getCedula();
            case 1: return r.getNombre1();
            case 2: return r.getApellido1();
            case 3: return r.getTelefono();
            default: return null;
        }
    }

    // Método CRUCIAL para obtener el objeto completo
    public RepresentanteDTO getRepresentanteAt(int fila) {
        return representantes.get(fila);
    }
}