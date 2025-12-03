package expo4to.gestion_AD.controlador;

import expo4to.gestion_AD.dto.AnosEscolaresDTO;
import expo4to.gestion_AD.servicio.AnoEscolarServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnoEscolarControlador {

    @Autowired
    AnoEscolarServicio anoEscolarServicio;

    public AnosEscolaresDTO buscarAnoActivo() {

        return anoEscolarServicio.buscarAnoActivo();

    }

}
