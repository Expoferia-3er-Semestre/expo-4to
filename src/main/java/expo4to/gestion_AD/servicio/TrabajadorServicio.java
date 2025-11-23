package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.Trabajador;
import expo4to.gestion_AD.repositorio.TrabajadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorServicio implements ITrabajadorServicio{

    @Autowired
    TrabajadorRepositorio trabajadorRepositorio;

    @Override
    public List<Trabajador> listarTrabajadores() {
        return trabajadorRepositorio.findAll();
    }

    @Override
    public Trabajador buscarTrabajadorPorId(Integer id) {
        return trabajadorRepositorio.findById(id).orElse(null);
    }

    @Override
    public void guardarTrabajador(Trabajador trabajador) {
        trabajadorRepositorio.save(trabajador);
    }

    @Override
    public void eliminarTrabajador(Trabajador trabajador) {
        trabajadorRepositorio.delete(trabajador);
    }
}
