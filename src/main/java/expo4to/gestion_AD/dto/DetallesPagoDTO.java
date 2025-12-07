package expo4to.gestion_AD.dto;

import expo4to.gestion_AD.modelo.AnosEscolares;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class DetallesPagoDTO {

    private Integer id;
    private TipoPagoDTO tipoPagoDTO;
    private AnosEscolaresDTO anoEscolar;

    private String metodoPago;
    private String numTrans;
    private String descripcion;
    private Integer mesCorrespondiente;

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

            if (this.montoPagado == null){
                return BigDecimal.ZERO;
            }
            return montoPagado;
        }

        // Usamos Streams de Java para calcular la suma de manera eficiente
        this.montoPagado = abonoDTOList.stream()
                .map(AbonoDTO::getMontoAbonado)
                .filter(monto -> monto != null) // Ignora abonos con monto nulo
                .reduce(BigDecimal.ZERO, BigDecimal::add);// Suma todos los montos

        return montoPagado;
    }

    public BigDecimal calcularPendiente() {

        // Es crucial asegurarse de que montoTotal y montoPagado no sean nulos antes de restar.
        BigDecimal total = (this.montoTotal != null) ? this.montoTotal : BigDecimal.ZERO;
        BigDecimal pagado = (this.montoPagado != null) ? this.montoPagado : BigDecimal.ZERO;

        // Realiza la resta: Total - Pagado
        return total.subtract(pagado);

    }

    public boolean tieneSaldoPendiente() {
        // Obtener valores seguros (no nulos)
        BigDecimal total = (montoTotal != null) ? montoTotal : BigDecimal.ZERO;
        BigDecimal pagado = (montoPagado != null) ? montoPagado : BigDecimal.ZERO;

        // Realizar la resta de forma segura
        BigDecimal pendiente = total.subtract(pagado);

        // Redondeo y comparación
        BigDecimal pendienteRound = pendiente.setScale(2, RoundingMode.HALF_UP);
        return pendienteRound.compareTo(BigDecimal.ZERO) > 0;
    }

}
