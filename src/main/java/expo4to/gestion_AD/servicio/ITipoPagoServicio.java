package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.TipoPagoDTO;
import expo4to.gestion_AD.modelo.TipoPago;
import java.util.List;

public interface ITipoPagoServicio {

    public List<TipoPagoDTO> listarTipoPagos();

    public TipoPagoDTO buscarTipoPago(Integer id);

    public void guardarTipoPago(TipoPagoDTO tipoPagoDTO, String costo);

    public void eliminarTipoPago(Integer id);
}
