package expo4to.gestion_AD.repositorio;

import expo4to.gestion_AD.modelo.PagoRecibo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagoReciboRepositorio extends JpaRepository<PagoRecibo, Integer> {
    List<PagoRecibo> findByEstudianteId(Integer idEstudiante);
}
