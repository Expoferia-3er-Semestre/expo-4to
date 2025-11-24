package expo4to.gestion_AD.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import expo4to.gestion_AD.modelo.Nota;

public interface NotaRepositorio extends JpaRepository<Nota, Integer> {
}
