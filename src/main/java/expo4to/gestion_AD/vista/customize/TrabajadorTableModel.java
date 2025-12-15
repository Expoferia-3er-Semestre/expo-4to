package expo4to.gestion_AD.vista.customize;

import expo4to.gestion_AD.dto.TrabajadorDTO;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TrabajadorTableModel extends AbstractTableModel{

    private List<TrabajadorDTO> trabajadores;
    private final String[] nombresColumnas = {"ID", "Cedula", "Nombres", "Apellidos", "Teléfono", "Dirección", "Estado", "Rol"};

    public TrabajadorTableModel(List<TrabajadorDTO> trabajadores){
        this.trabajadores = trabajadores;
    }

    // Métodos para actualizar los datos de la tabla.
    public  void setTrabajadores(List<TrabajadorDTO> trabajadores){
        this.trabajadores = trabajadores;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount(){ return trabajadores.size();}

    @Override
    public int getColumnCount(){ return nombresColumnas.length;}

    @Override
    public String getColumnName(int columna) { return nombresColumnas[columna];}

    @Override
    public Object getValueAt(int fila, int columna){
        TrabajadorDTO r = trabajadores.get(fila);

        switch (columna){
            case 0: return r.getId();
            case 1: return r.getCedula();
            case 2: return r.getNombre1() + " " + r.getNombre2();
            case 3: return r.getApellido1() + " " + r.getApellido2();
            case 4: return r.getTelefono();
            case 5: return r.getDireccion();
            case 6: return r.getEstado();
            case 7: return r.getRol();
            default: return null;
        }

    }

    public TrabajadorDTO getTrabajadorAt(int fila) {return trabajadores.get(fila);}

}
