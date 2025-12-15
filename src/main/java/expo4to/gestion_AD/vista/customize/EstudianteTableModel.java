package expo4to.gestion_AD.vista.customize;

import expo4to.gestion_AD.dto.EstudianteDTO;
import expo4to.gestion_AD.dto.RepresentanteDTO;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EstudianteTableModel extends AbstractTableModel {

    private List<EstudianteDTO> estudiantes;
    private final String[] nombresColumnas = { "Representante", "Nombre", "Apellido", "Grado"};

    public EstudianteTableModel(List<EstudianteDTO> estudiantes) {
        this.estudiantes = estudiantes;
    }

    // Método para actualizar los datos de la tabla
    public void setEstudiantes(List<EstudianteDTO> estudiantes) {
        this.estudiantes = estudiantes;
        fireTableDataChanged(); // Notifica a la tabla que los datos han cambiado
    }

    @Override
    public int getRowCount() {
        return estudiantes.size();
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
        EstudianteDTO e = estudiantes.get(fila);

        // Determina qué campo mostrar basado en el índice de la columna
        switch (columna) {
            case 0: return e.getRepresentante().getCedula();
            case 1: return e.getNombre1();
            case 2: return e.getApellido1();
            case 3: return e.getGrado();
            default: return null;
        }
    }

    // Método CRUCIAL para obtener el objeto completo
    public EstudianteDTO getEstudianteAt(int fila) {
        return estudiantes.get(fila);
    }
}