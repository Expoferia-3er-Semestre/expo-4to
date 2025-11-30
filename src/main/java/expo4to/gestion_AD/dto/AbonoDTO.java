package expo4to.gestion_AD.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class AbonoDTO {

    private String montoAbonado;

    private String descripcion;
    private String metodoPago;
    private String numTrans;

}
