package expo4to.gestion_AD.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "detalles_pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class DetallesPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_pago_recibo")
    private PagoRecibo pagoRecibo;
    @ManyToOne
    @JoinColumn(name = "id_tipo_pago")
    private TipoPago tipoPago;
    private String metodoPago;
    private String numTrans;
    @ManyToOne
    @JoinColumn(name = "id_ano_escolar")
    private AnosEscolares anoEscolar;
    private String descripcion;
    private String mesCorrespondiente;
    private BigDecimal montoTotal;
    private BigDecimal montoPagado;
    @OneToMany(mappedBy = "detallesPago", cascade = CascadeType.ALL)
    private List<Abono> abonos = new ArrayList<>();

    public void addAbono(Abono abono) {
        if (this.abonos == null) {
            this.abonos = new ArrayList<>();
        }
        this.abonos.add(abono);
        abono.setDetallesPago(this);
    }

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
