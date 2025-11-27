package expo4to.gestion_AD.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PagoRecibo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPagoRecibo;
    private Integer idEstudiante;
    private BigDecimal montoTotal;
    private BigDecimal montoPagado;
    private Boolean estado;
    private Date fechaPago;

}
