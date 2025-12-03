package expo4to.gestion_AD.dto;

import expo4to.gestion_AD.modelo.AnosEscolares;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class DetallesPagoDTO {

    private TipoPagoDTO tipoPagoDTO;
    private AnosEscolares anoEscolar;

    private String metodoPago;
    private String numTrans;
    private String descripcion;
    private String mesCorrespondiente;

    private BigDecimal montoTotal;
    private BigDecimal montoPagado;

    @ToString.Exclude
    private List<AbonoDTO> abonoDTOList = new ArrayList<>();

    public void addAbonoDTO(AbonoDTO dto) {
        if (abonoDTOList == null) {
            abonoDTOList = new ArrayList<>();
        }
        abonoDTOList.add(dto);
    }

    public BigDecimal calcularMontoPagado() {

        // Si la lista está vacía, el monto pagado es 0
        if (abonoDTOList == null || abonoDTOList.isEmpty()) {
            return BigDecimal.ZERO;
        }

        // Usamos Streams de Java para calcular la suma de manera eficiente
        this.montoPagado = abonoDTOList.stream()
                .map(AbonoDTO::getMontoAbonado)
                .filter(monto -> monto != null) // Ignora abonos con monto nulo
                .reduce(BigDecimal.ZERO, BigDecimal::add);// Suma todos los montos

        return montoPagado;
    }
}
