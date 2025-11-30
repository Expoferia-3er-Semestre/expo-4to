package expo4to.gestion_AD.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Value; // Ideal para DTOs inmutables

@Value
public class MontosDTO {

    // Total de la deuda acumulada de los ítems
    private final BigDecimal montoTotal;

    // Total que el cliente ha pagado en abonos (si aplica)
    private final BigDecimal pagadoTotal;

    public boolean tienependiente() {

        BigDecimal pendiente = montoTotal.subtract(pagadoTotal);

        //Se redondea según estandares bancarios
        BigDecimal pendienteRound = pendiente.setScale(2, RoundingMode.HALF_UP);
        return pendienteRound.compareTo(BigDecimal.ZERO) > 0;

    }
}