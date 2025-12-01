package expo4to.gestion_AD.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PagoReciboDTO {

    private Integer idEstudiante;

    private String montoTotal;
    private String montoPagado;

    private List<DetallesPagoDTO> detallesPagoDTOList;

    public void addDetalleDTO(DetallesPagoDTO dto) {
        detallesPagoDTOList.add(dto);
    }

}
