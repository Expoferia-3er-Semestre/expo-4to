package expo4to.gestion_AD.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "estudiantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "cedula_rep", referencedColumnName = "cedula")
    private Representante representante;
    @Column(name = "nombre_1")
    private String nombre1;
    @Column(name = "nombre_2")
    private String nombre2;
    @Column(name = "apellido_1")
    private String apellido1;
    @Column(name = "apellido_2")
    private String apellido2;
    @Column(name = "fecha_n")
    private Date fechaNacimiento;
    private String direccion;
    private String grado;
    @Column(name = "nivel_academico")
    private Boolean nivelAcademico;
    private Boolean estado;

}
