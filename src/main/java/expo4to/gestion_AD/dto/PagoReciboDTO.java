package expo4to.gestion_AD.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class PagoReciboDTO {

    private Integer idEstudiante;

    private String montoTotal;
    private String montoPagado;

    @ToString.Exclude
    private List<DetallesPagoDTO> detallesPagoDTOList = new ArrayList<>();

    public void addDetalleDTO(DetallesPagoDTO dto) {
        if (detallesPagoDTOList == null) {
            detallesPagoDTOList = new ArrayList<>();
        }
        detallesPagoDTOList.add(dto);
    }

}
