package expo4to.gestion_AD.repositorio;

import expo4to.gestion_AD.dto.ProfesorDTO;
import expo4to.gestion_AD.modelo.Materia;
import expo4to.gestion_AD.modelo.PagoRecibo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaRepositorio extends JpaRepository<Materia, Integer> {
    List<Materia> findByProfesor(ProfesorDTO profesor);
}
