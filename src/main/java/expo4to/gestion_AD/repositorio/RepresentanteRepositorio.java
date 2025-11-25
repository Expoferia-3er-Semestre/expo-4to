package expo4to.gestion_AD.repositorio;

import expo4to.gestion_AD.modelo.Representante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepresentanteRepositorio extends JpaRepository<Representante, Integer> {

    Optional<Representante> findByCedula(String cedula);
}
