package expo4to.gestion_AD.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "materias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "anio_materia")
    private String anioMateria;
    private Profesor idProfesor;

}
