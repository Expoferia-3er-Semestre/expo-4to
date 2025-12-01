package expo4to.gestion_AD.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "trabajadores")
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
    @Column(name = "nombre_1")
    private String nombre1;
    @Column(name = "nombre_2")
    private String nombre2;
    @Column(name = "apellido_1")
    private String apellido1;
    @Column(name = "apellido_2")
    private String apellido2;
    private String telefono;
    private String correo;
    @Column(name = "fecha_N")
    private Date fechaN;
    private String direccion;
    private Boolean estado;
    private String contrasena;
    private Integer rol;

}
