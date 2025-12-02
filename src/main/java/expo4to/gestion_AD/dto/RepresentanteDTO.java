package expo4to.gestion_AD.dto;

import lombok.Data;
import lombok.ToString;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class RepresentanteDTO {

    private String cedula;

    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;

    private String telefono;
    private Date fechaN;
    private String direccion;

    @ToString.Exclude
    private List<EstudianteDTO> estudiantes = new ArrayList<>();

    private Boolean estado;

    public void addEstudiante(EstudianteDTO dto) {

        if (estudiantes == null){
            estudiantes = new ArrayList<>();
        }
        if (dto != null) {
            estudiantes.add(dto);
            dto.setRepresentante(this);
        }
    }

}
