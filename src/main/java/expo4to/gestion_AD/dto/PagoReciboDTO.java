package expo4to.gestion_AD.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class PagoReciboDTO {

    private Integer idEstudiante;

    private BigDecimal montoTotal;
    private BigDecimal montoPagado;

    private Date fechaPago;

}
