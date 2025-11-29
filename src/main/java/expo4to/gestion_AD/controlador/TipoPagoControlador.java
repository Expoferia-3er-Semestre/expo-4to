package expo4to.gestion_AD.controlador;

import expo4to.gestion_AD.dto.TipoPagoDTO;
import expo4to.gestion_AD.servicio.ITipoPagoServicio;
import expo4to.gestion_AD.servicio.TipoPagoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import expo4to.gestion_AD.modelo.TipoPago;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TipoPagoControlador {

    private final ITipoPagoServicio tpServicio;

    public List<TipoPago> listarTipoTapos() {

        try {
            return tpServicio.listarTipoPagos();
        } catch (Exception e) {
            System.err.println("WARN: Error al listar: " + e.getMessage());
            return null;
        }
    }

    public String guardarTipoPago(TipoPagoDTO tipoPagoDTO, String monto) {

        try {
            tpServicio.guardarTipoPago(tipoPagoDTO, monto);
            return "Éxito: Tipo de pago guardado/actualizado.";
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error de Validación: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al guardar: " + e.getMessage());
        }

    }

    public void eliminarTipoPago(Integer id) {

        try {
            tpServicio.eliminarTipoPago(id);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar: " + e.getMessage());
        }

    }

}
