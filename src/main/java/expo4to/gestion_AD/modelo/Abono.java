package expo4to.gestion_AD.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Abono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAbono;
    private Integer idDetallesPagos;
    private Date fechaAbono;
    private BigDecimal montoAbonado;
    private String descripcion;
    private String metodoPago;
    private String numTrans;

}
