package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.AbonoDTO;
import expo4to.gestion_AD.dto.DetallesPagoDTO;
import expo4to.gestion_AD.dto.PagoReciboDTO;
import expo4to.gestion_AD.modelo.DetallesPago;

import java.util.List;

public interface IPagoServicio {

    public List<PagoReciboDTO> listarTodoEstudiante(Integer id);

    public List<PagoReciboDTO> listarMensualidadesEstudiante(Integer id);

    public List<PagoReciboDTO> listarNoMensualidadesEstudiante(Integer id);

    public DetallesPagoDTO obtenerMensualidadPendiente(Integer estudianteId);

    public void registrarNuevoPago(PagoReciboDTO prDTOs);

}
