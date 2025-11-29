package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.RepresentanteDTO;
import expo4to.gestion_AD.modelo.Representante;

import java.util.List;

public interface IRepresentanteServicio {

    public List<Representante> listarRepresentantes();

    public Representante buscarRepresentantePorId(Integer id);

    public Representante buscarRepresentantePorCedula(String cedula);

    //Funciona tanto para actualizar o registrar segun el valor de id (null o not null)
    public void guardarRepresentante(RepresentanteDTO representanteDTO);

    public void eliminarRepresentante(Integer id);

}
