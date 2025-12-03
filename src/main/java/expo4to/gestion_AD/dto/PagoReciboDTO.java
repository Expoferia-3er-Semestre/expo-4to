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

    private BigDecimal montoTotal;
    private BigDecimal montoPagado;

    @ToString.Exclude
    private List<DetallesPagoDTO> detallesPagoDTOList = new ArrayList<>();

    public void addDetalleDTO(DetallesPagoDTO dto) {
        if (detallesPagoDTOList == null) {
            detallesPagoDTOList = new ArrayList<>();
        }
        detallesPagoDTOList.add(dto);
    }

    public void actualizarMontosPagados() {
        if (detallesPagoDTOList == null || detallesPagoDTOList.isEmpty()) {
            this.montoPagado = BigDecimal.ZERO;
            return;
        }

        // 1. Inicia el acumulador en cero.
        BigDecimal totalPagado = BigDecimal.ZERO;

        // 2. Itera sobre la lista de detalles de pago.
        for (DetallesPagoDTO detalle : detallesPagoDTOList) {

            // 3. Obtiene el monto pagado del detalle.
            //    (Este getMontoPagado() es el que internamente suma los AbonosDTO)
            BigDecimal montoDetalle = detalle.calcularMontoPagado();

            // 4. Suma el monto al acumulador general, verificando que no sea nulo.
            if (montoDetalle != null) {
                totalPagado = totalPagado.add(montoDetalle);
            }
        }

        // 5. Asigna el resultado final al campo de este DTO.
        this.montoPagado = totalPagado;
    }

    public void actualizarMontoTotal() {
        if (detallesPagoDTOList == null || detallesPagoDTOList.isEmpty()) {
            this.montoTotal = BigDecimal.ZERO;
            return;
        }

        BigDecimal totalAdeudado = BigDecimal.ZERO;

        // Itera sobre la lista de detalles de pago.
        for (DetallesPagoDTO detalle : detallesPagoDTOList) {

            // Obtiene el monto total individual del detalle.
            BigDecimal montoDetalle = detalle.getMontoTotal();

            // Suma el monto al acumulador general.
            if (montoDetalle != null) {
                totalAdeudado = totalAdeudado.add(montoDetalle);
            }
        }

        // Asigna el resultado final al campo de este DTO.
        this.montoTotal = totalAdeudado;
    }

}
