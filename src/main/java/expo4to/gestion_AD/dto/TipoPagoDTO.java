package expo4to.gestion_AD.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TipoPagoDTO {

    private Integer id;
    private String categoria;
    private BigDecimal costo;
    private Boolean estado;

}
