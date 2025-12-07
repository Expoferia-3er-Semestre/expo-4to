package expo4to.gestion_AD.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pagos_recibos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PagoRecibo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;
    private BigDecimal montoTotal;
    private BigDecimal montoPagado;
    private Boolean estado;
    private Date fechaPago;
    @OneToMany(mappedBy = "pagoRecibo", cascade = {
            CascadeType.PERSIST, // Para los nuevos detalles
            CascadeType.MERGE // ⬅️ ¡ESTE ES EL NECESARIO para actualizar los existentes!
    }, orphanRemoval = true)
    private List<DetallesPago> detalles;

    public void addDetalle(DetallesPago detallesPago) {
        if (this.detalles == null) {
            this.detalles = new ArrayList<>();
        }
        this.detalles.add(detallesPago);
        detallesPago.setPagoRecibo(this);
    }

    public boolean tienependiente() {

        BigDecimal pendiente = montoTotal.subtract(montoPagado);

        //Se redondea según estandares bancarios
        BigDecimal pendienteRound = pendiente.setScale(2, RoundingMode.HALF_UP);
        return pendienteRound.compareTo(BigDecimal.ZERO) > 0;

    }


}
