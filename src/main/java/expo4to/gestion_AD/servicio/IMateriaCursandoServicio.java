package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.EstudianteDTO;
import expo4to.gestion_AD.dto.ProfesorDTO;
import expo4to.gestion_AD.modelo.*;

import java.util.List;

public interface IMateriaCursandoServicio {

    public List<MateriaCursando> listarMateriasCursando();

    public MateriaCursando buscarMateriaCursandoPorId (Integer id);

    public void guardarMateriaCursando (MateriaCursando materia);

    List<MateriaCursando> buscarMateriaCursandoPorEstudiante(EstudianteDTO estudianteDTO);

    public  List<MateriaCursando> buscarMateriaCursandoPorMateria(Materia materia);

    public List<MateriaCursando> buscarMateriaCursandoPorPeriodoAcademico(PeriodoAcademico periodoAcademico);

}
