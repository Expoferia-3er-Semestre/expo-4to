package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.Representante;
import expo4to.gestion_AD.repositorio.RepresentanteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepresentanteServicio implements IRepresentanteServicio{

    @Autowired
    private RepresentanteRepositorio representanteRepositorio;

    @Override
    public List<Representante> listarRepresentantes() {
        return representanteRepositorio.findAll();
    }

    @Override
    public Representante buscarRepresentantePorId(Integer id) {
        return representanteRepositorio.findById(id).orElse(null);
    }

    @Override
    public void guardarRepresentante(Representante representante) {
        representanteRepositorio.save(representante);
    }

    @Override
    public void eliminarRepresentante(Representante representante) {
        representanteRepositorio.delete(representante);
    }
}
