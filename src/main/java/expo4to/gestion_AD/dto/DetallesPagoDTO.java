package expo4to.gestion_AD.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class DetallesPagoDTO {

    private Integer idTipoPago;
    private Integer idAnoEscolar;

    private String metodoPago;
    private String numTrans;
    private String descripcion;
    private String mesCorrespondiente;

    private String montoTotal;
    private String montoPagado;

    @ToString.Exclude
    private List<AbonoDTO> abonoDTOList = new ArrayList<>();

    public void addAbonoDTO(AbonoDTO dto) {
        if (abonoDTOList == null) {
            abonoDTOList = new ArrayList<>();
        }
        abonoDTOList.add(dto);
    }

}
