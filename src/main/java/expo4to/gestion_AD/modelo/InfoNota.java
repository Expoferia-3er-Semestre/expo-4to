package expo4to.gestion_AD.modelo;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "info_notas")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class InfoNota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_materia_cursando")
    private MateriaCursando idMateriaCrusando;
    private String lapso;
    private Double nota;

}
