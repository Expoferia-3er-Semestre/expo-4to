package expo4to.gestion_AD.controlador;

import expo4to.gestion_AD.dto.PagoReciboDTO;
import expo4to.gestion_AD.servicio.IPagoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class PagoControlador {

    private final IPagoServicio pagoServicio;

    public List<PagoReciboDTO> listarPagos(Integer idEstudiante) {

        List<PagoReciboDTO> pagos = new ArrayList<>();

        try {

        } catch (NoSuchElementException e) {

        }

        return pagos;
    }

    public void guardarPago(PagoReciboDTO recibo) {

        try {

            pagoServicio.registrarNuevoPago(recibo);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error: ocurrió un error al guardar el pago: " + e.getMessage());
        }

    }

}
