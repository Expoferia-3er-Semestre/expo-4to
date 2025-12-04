package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.Nota;
import expo4to.gestion_AD.repositorio.NotaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaServicio implements INotaServicio{

    @Autowired
    NotaRepositorio notaRepositorio;

    @Override
    public List<Nota> listarNotas() {
        return notaRepositorio.findAll();
    }

    @Override
    public Nota buscarNotaPorId(Integer id) {
        return notaRepositorio.findById(id).orElse(null);
    }

    @Override
    public void guardarNota(Nota nota) {
        notaRepositorio.save(nota);
    }
}
