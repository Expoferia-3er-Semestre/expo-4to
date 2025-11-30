package expo4to.gestion_AD.modelo;

import jakarta.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "id_detalle_pagos") // Mapea a la columna física
    private DetallesPago detallesPago;
    private Date fechaAbono;
    private BigDecimal montoAbonado;
    private String descripcion;
    private String metodoPago;
    private String numTrans;

}
