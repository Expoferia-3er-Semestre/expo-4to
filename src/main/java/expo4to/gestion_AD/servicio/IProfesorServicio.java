package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.Profesor;

import java.util.List;

public interface IProfesorServicio {

    public List<Profesor> listarProfesores();

    public Profesor buscarProfesorPorId(Integer id);

    public void guardarProfesor(Profesor profesor);

    public void eliminarProfesor(Profesor profesor);

}
