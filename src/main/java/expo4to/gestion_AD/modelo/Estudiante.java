package expo4to.gestion_AD.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cedula_rep;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private Date fecha_nacimiento;
    private String direccion;
    private String grado;
    private Boolean nivel_academico;
    private Boolean estado;

}
