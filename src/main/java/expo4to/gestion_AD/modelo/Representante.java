package expo4to.gestion_AD.modelo;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "representantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Representante {

    @Id
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
    @Column(name = "fecha_n")
    private Date fechaN;
    private String direccion;
    private Boolean estado;
    @OneToMany(mappedBy = "representante", cascade = CascadeType.ALL)
    private List<Estudiante> estudiantes = new ArrayList<>();

    public void addEstudiante(Estudiante estudiante) {

        if (estudiantes == null) {
            estudiantes = new ArrayList<>();
        }
        estudiantes.add(estudiante);
        estudiante.setRepresentante(this);
    }

}
