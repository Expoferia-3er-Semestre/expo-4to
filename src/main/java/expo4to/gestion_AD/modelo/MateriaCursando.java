package expo4to.gestion_AD.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "materia_cursando")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MateriaCursando {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Materia idMateria;
    @ManyToOne
    @JoinColumn(name = "id_periodo")
    private PeriodoAcademico idPeriodoAcademico;
    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante idEstudiante;

}
