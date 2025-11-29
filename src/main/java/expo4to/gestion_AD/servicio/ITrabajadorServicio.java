package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.TrabajadorDTO;
import expo4to.gestion_AD.modelo.Trabajador;

import java.util.List;

public interface ITrabajadorServicio {

    public List<Trabajador> listarTrabajadores();

    public Trabajador buscarTrabajadorPorId(Integer id);

    public void guardarTrabajador(TrabajadorDTO trabajadorDTO);

    public void eliminarTrabajador(Integer id);

}
