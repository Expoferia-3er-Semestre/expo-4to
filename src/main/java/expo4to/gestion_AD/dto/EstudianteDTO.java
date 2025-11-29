package expo4to.gestion_AD.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class EstudianteDTO {

    private Integer id;
    private String cedulaRep;

    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;

    private Date fechaNacimiento;
    private String direccion;

    private String grado;
    private Boolean nivelAcademico;
    private Boolean estado;

}
