package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.AbonoDTO;
import expo4to.gestion_AD.dto.DetallesPagoDTO;
import expo4to.gestion_AD.dto.PagoReciboDTO;
import expo4to.gestion_AD.modelo.DetallesPago;
import expo4to.gestion_AD.modelo.PagoRecibo;

import java.util.List;

public interface IPagoServicio {

    public void registrarNuevoPago(PagoReciboDTO prDTO, List<DetallesPagoDTO> dpsDTO, List<AbonoDTO> absDTO);

}
