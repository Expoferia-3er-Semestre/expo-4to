package expo4to.gestion_AD.vista.customize;

import expo4to.gestion_AD.dto.EstudianteDTO;
import expo4to.gestion_AD.dto.TrabajadorDTO;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EmpleadoTableModel extends AbstractTableModel {

    private List<TrabajadorDTO> trabajadores;
    private final String[] nombresColumnas = { "Cedula", "Nombre", "Apellido", "Rol"};

    public EmpleadoTableModel(List<TrabajadorDTO> trabajadores) {
        this.trabajadores = trabajadores;
    }

    // Método para actualizar los datos de la tabla
    public void setTrabajadores(List<TrabajadorDTO> trabajadores) {
        this.trabajadores = trabajadores;
        fireTableDataChanged(); // Notifica a la tabla que los datos han cambiado
    }

    @Override
    public int getRowCount() {
        return trabajadores.size();
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
        TrabajadorDTO t = trabajadores.get(fila);

        // Determina qué campo mostrar basado en el índice de la columna
        switch (columna) {
            case 0: return t.getCedula();
            case 1: return t.getNombre1();
            case 2: return t.getApellido1();
            case 3: 
                if (t.getRol() == 1) return "Cajero";
                else if (t.getRol() == 2) return "Control Estudios";
                else if (t.getRol() == 3) return "Profesor";
            default: return null;
        }
    }

    // Método CRUCIAL para obtener el objeto completo
    public TrabajadorDTO getTrabajadoresAt(int fila) {
        return trabajadores.get(fila);
    }
}