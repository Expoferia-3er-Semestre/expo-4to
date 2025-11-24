package expo4to.gestion_AD.servicio;

import java.util.List;
import expo4to.gestion_AD.modelo.Materia;

public interface IMateriaServicio {

    public List<Materia> listarMaterias();

    public Materia buscarMateriaPorId(Integer id);

    public void guardarMateria(Materia materia);

    public void eliminarMateria(Materia materia);

}
