package expo4to.gestion_AD.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

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
    private Integer id_pago_recibo;
    private Integer id_tipo_pago;
    private String metodo_pago;
    private String num_trans;
    private Integer id_ano_escolar;
    private String descripcion;
    private String mes_correspondiente;
    private Float monto_total;
    private Float monto_pagado;

}
