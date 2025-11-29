package expo4to.gestion_AD.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetallesPagoDTO {

    private Integer idPagoRecibo;
    private Integer idTipoPago;
    private Integer idAnoEscolar;

    private String metodoPago;
    private String numTrans;
    private String descripcion;
    private String mesCorrespondiente;

    private BigDecimal montoTotal;
    private BigDecimal montoPagado;

}
