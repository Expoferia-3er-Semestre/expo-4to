package expo4to.gestion_AD.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class RepresentanteDTO {

    private Integer id;
    private String cedula;

    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;

    private String telefono;
    private Date fechaN;
    private String direccion;

    private Boolean estado;

}
