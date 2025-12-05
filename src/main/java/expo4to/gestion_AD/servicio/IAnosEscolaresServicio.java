package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.AnosEscolaresDTO;
import expo4to.gestion_AD.modelo.AnosEscolares;

import java.sql.Date;

public interface IAnosEscolaresServicio {

    public AnosEscolares buscarAnoActivo();

    public void guardarAno(AnosEscolaresDTO anosEscolaresDTO);

}
