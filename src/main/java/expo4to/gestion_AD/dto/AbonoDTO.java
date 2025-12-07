package expo4to.gestion_AD.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@ToString
public class AbonoDTO {

    private Integer id;
    private BigDecimal montoAbonado;

    private String descripcion;
    private String metodoPago;
    private String numTrans;

}
