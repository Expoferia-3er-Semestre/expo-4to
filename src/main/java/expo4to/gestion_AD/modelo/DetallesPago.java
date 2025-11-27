package expo4to.gestion_AD.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DetallesPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idPagoRecibo;
    private Integer idTipoPago;
    private String metodoPago;
    private String numTrans;
    private Integer idAnoEscolar;
    private String descripcion;
    private String mesCorrespondiente;
    private BigDecimal montoTotal;
    private BigDecimal montoPagado;

    public boolean tieneSaldoPendiente() {
        BigDecimal pendiente = montoTotal.subtract(montoPagado);

        //Se redondea según estandares bancarios
        BigDecimal pendienteRound = pendiente.setScale(2, RoundingMode.HALF_UP);
        return pendienteRound.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean esMensualidad() {
        return mesCorrespondiente != null && !mesCorrespondiente.isEmpty();
    }



}
