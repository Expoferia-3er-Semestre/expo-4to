package expo4to.gestion_AD.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class AnosEscolaresDTO {

    private Integer id;
    private Date periodoInicio;
    private Date periodoFin;
    private Boolean estado;

}
