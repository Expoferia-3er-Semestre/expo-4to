package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.Materia;
import expo4to.gestion_AD.repositorio.MateriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaServicio implements IMateriaServicio{

    @Autowired
    MateriaRepositorio materiaRepositorio;

    @Override
    public List<Materia> listarMaterias() {
        return materiaRepositorio.findAll();
    }

    @Override
    public Materia buscarMateriaPorId(Integer id) {
        return materiaRepositorio.findById(id).orElse(null);
    }

    @Override
    public void guardarMateria(Materia materia) {
        materiaRepositorio.save(materia);
    }

    @Override
    public void eliminarMateria(Materia materia) {
        materiaRepositorio.delete(materia);
    }
}
