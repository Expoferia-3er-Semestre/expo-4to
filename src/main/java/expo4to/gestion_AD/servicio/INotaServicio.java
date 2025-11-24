package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.Nota;
import java.util.List;

public interface INotaServicio {

    public List<Nota> listarNotas();

    public Nota buscarNotaPorId(Integer id);

    public void guardarNota(Nota nota);

    public void eliminarNota(Nota nota);

}
