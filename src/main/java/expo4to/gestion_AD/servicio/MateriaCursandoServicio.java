package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.EstudianteDTO;
import expo4to.gestion_AD.dto.ProfesorDTO;
import expo4to.gestion_AD.modelo.AnosEscolares;
import expo4to.gestion_AD.modelo.Materia;
import expo4to.gestion_AD.modelo.MateriaCursando;
import expo4to.gestion_AD.modelo.PeriodoAcademico;
import expo4to.gestion_AD.repositorio.MateriaCursandoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaCursandoServicio implements  IMateriaCursandoServicio{

    @Autowired
    MateriaCursandoRepositorio materiaCursandoRepositorio;

    @Override
    public List<MateriaCursando> listarMateriasCursando() {
        return materiaCursandoRepositorio.findAll();
    }

    @Override
    public MateriaCursando buscarMateriaCursandoPorId(Integer id){
        return materiaCursandoRepositorio.findById(id).orElse(null);
    }

    @Override
    public List<MateriaCursando> buscarMateriaCursandoPorEstudiante(EstudianteDTO estudiante){//Listo
        return materiaCursandoRepositorio.findByEstudiante(estudiante);
    }

    @Override
    public List<MateriaCursando> buscarMateriaCursandoPorMateria(Materia materia){ //Listo.
        return materiaCursandoRepositorio.findByMateria(materia);
    }

    @Override
    public List<MateriaCursando> buscarMateriaCursandoPorPeriodoAcademico(PeriodoAcademico periodoAcademico){//Listo
        return materiaCursandoRepositorio.findByPeriodoAcademico(periodoAcademico);
    }

    @Override
    public void guardarMateriaCursando(MateriaCursando materiaCursando){
        materiaCursandoRepositorio.save(materiaCursando);
    }
}
