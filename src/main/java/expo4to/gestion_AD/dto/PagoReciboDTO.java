package expo4to.gestion_AD.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class PagoReciboDTO {

    private Integer id;
    private Integer idEstudiante;

    private BigDecimal montoTotal;
    private BigDecimal montoPagado;

    private Date fechaPago;
    private Boolean estado;

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
            //    (Este calcularMontoPagado() es el que internamente suma los AbonosDTO)
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

    public boolean tienependiente() {

        // Obtener valores seguros (no nulos)
        BigDecimal total = (montoTotal != null) ? montoTotal : BigDecimal.ZERO;
        BigDecimal pagado = (montoPagado != null) ? montoPagado : BigDecimal.ZERO;

        BigDecimal pendiente = total.subtract(pagado);

        //Se redondea según estandares bancarios
        BigDecimal pendienteRound = pendiente.setScale(2, RoundingMode.HALF_UP);
        return pendienteRound.compareTo(BigDecimal.ZERO) > 0;

    }

    public void borrarRegistros() {

        // 1. Limpiar la lista de Abonos en cada Detalle (opcional, si los Abonos no son Listas)
        if (detallesPagoDTOList != null) {
            for (DetallesPagoDTO dto : detallesPagoDTOList) {

                // Si AbonoDTOList es una lista (List<AbonoDTO>), usa clear()
                if (dto.getAbonoDTOList() != null) {
                    dto.getAbonoDTOList().clear();
                }
            }
        }

        // 2. Limpiar la lista principal de Detalles de Pago
        if (detallesPagoDTOList != null) {
            detallesPagoDTOList.clear();
        }

        this.montoTotal = BigDecimal.ZERO;
        this.montoPagado = BigDecimal.ZERO;
        this.idEstudiante = null;
        this.fechaPago = null;
        this.id = null;

    }

}
