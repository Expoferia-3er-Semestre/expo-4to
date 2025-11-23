package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.Estudiante;

import java.util.List;

public interface IEstudianteServicio {

    public List<Estudiante> listarEstudiantes();

    public Estudiante buscarEstudiantePorId(Integer id);

    public void guardarEstudiante(Estudiante estudiante);

    public void eliminarEstudiante(Estudiante estudiante);

}
