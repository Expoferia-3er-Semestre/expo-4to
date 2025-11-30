package expo4to.gestion_AD.controlador;

import expo4to.gestion_AD.servicio.IPagoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PagoControlador {

    private final IPagoServicio pagoServicio;



}
