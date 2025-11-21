package expo4to.gestion_AD.modelo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.sql.Date;

public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date fechaFact;
    private Integer idTrabajador;
    private Integer idAlumno;
    private Integer idTasa;
    private Float montoBs;
    private Float montoPagoMovil;
    private Float montoDolares;

}
