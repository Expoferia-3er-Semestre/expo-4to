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

    public List<PagoReciboDTO> buscarPagos(Integer idEstudiante) {

        List<PagoReciboDTO> pagos = new ArrayList<>();

        try {

        } catch (NoSuchElementException e) {

        }

        return pagos;
    }


}
