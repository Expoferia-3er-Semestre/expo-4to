package expo4to.gestion_AD.modelo;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Representante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cedula;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private Date fechaN;
    private String direccion;
    private boolean estado;
    @OneToMany(mappedBy = "representante")
    private List<Estudiante> estudiantes;

}
