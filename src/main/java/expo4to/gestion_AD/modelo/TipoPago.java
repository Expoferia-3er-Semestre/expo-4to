package expo4to.gestion_AD.modelo;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tipos_pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TipoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String categoria;
    private BigDecimal costo;
    private Boolean estado;

}
