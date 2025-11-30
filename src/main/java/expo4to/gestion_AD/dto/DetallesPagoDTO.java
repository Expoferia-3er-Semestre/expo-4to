package expo4to.gestion_AD.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class DetallesPagoDTO {

    private Integer idTipoPago;
    private Integer idAnoEscolar;

    private String metodoPago;
    private String numTrans;
    private String descripcion;
    private String mesCorrespondiente;

    private String montoTotal;
    private String montoPagado;

    private List<AbonoDTO> abonoDTOList;

    public void addAbonoDTO(AbonoDTO dto) {
        abonoDTOList.add(dto);
    }

}
