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
public class Trabajador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cedula;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String correo;
    @Column(name = "fechaN")
    private Date fechaN;
    private String direccion;
    private Boolean estado;
    private String contrasena;
    private Integer rol;

}
