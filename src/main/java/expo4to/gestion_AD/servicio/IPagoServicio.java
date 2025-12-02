package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.AbonoDTO;
import expo4to.gestion_AD.dto.DetallesPagoDTO;
import expo4to.gestion_AD.dto.PagoReciboDTO;

import java.util.List;

public interface IPagoServicio {

    public List<PagoReciboDTO> listarPagosPorIdEstudiante(Integer id);

    public void registrarNuevoPago(PagoReciboDTO prDTOs);

}
