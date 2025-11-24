package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.Profesor;
import expo4to.gestion_AD.repositorio.ProfesorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorServicio implements IProfesorServicio {

    @Autowired
    ProfesorRepositorio profesorRepositorio;

    @Override
    public List<Profesor> listarProfesores() {
        return profesorRepositorio.findAll();
    }

    @Override
    public Profesor buscarProfesorPorId(Integer id) {
        return profesorRepositorio.findById(id).orElse(null);
    }

    @Override
    public void guardarProfesor(Profesor profesor) {
        profesorRepositorio.save(profesor);
    }

    @Override
    public void eliminarProfesor(Profesor profesor) {
        profesorRepositorio.delete(profesor);
    }
}
