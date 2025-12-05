package expo4to.gestion_AD.repositorio;

import expo4to.gestion_AD.dto.EstudianteDTO;
import expo4to.gestion_AD.dto.MateriaDTO;
import expo4to.gestion_AD.dto.ProfesorDTO;
import expo4to.gestion_AD.modelo.AnosEscolares;
import expo4to.gestion_AD.modelo.Materia;
import expo4to.gestion_AD.modelo.MateriaCursando;
import expo4to.gestion_AD.modelo.PeriodoAcademico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaCursandoRepositorio extends JpaRepository <MateriaCursando, Integer> {

    List<MateriaCursando> findByEstudiante(EstudianteDTO estudiante);
    List<MateriaCursando> findByPeriodoAcademico(PeriodoAcademico periodoAcademico);
    List<MateriaCursando> findByMateria(Materia materia);
    List<MateriaCursando> findByMateriaAndEstudianteAndAnoEscolar (MateriaDTO materia, EstudianteDTO estudiante, AnosEscolares anosEscolares);
}
