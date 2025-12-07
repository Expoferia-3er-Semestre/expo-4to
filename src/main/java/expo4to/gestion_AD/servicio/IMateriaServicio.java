package expo4to.gestion_AD.servicio;

import java.util.List;

import expo4to.gestion_AD.dto.MateriaDTO;
import expo4to.gestion_AD.dto.ProfesorDTO;
import expo4to.gestion_AD.modelo.Materia;

public interface IMateriaServicio {

    public List<Materia> listarMaterias();

    public Materia buscarMateriaPorId(Integer id);

    public void guardarMateria(Materia materia);

    List<Materia> buscarMateriaPorProfesor (ProfesorDTO profesor);
}
