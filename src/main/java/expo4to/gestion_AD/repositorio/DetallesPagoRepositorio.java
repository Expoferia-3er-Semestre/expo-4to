package expo4to.gestion_AD.repositorio;

import expo4to.gestion_AD.modelo.DetallesPago;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetallesPagoRepositorio extends JpaRepository<DetallesPago, Integer> {

    List<DetallesPago> findByIdPagoReciboIn(List<Integer> idPagosRecibo);

    List<DetallesPago> findByIdPagoReciboInAndMesCorrespondienteIsNull(List<Integer> idPagosRecibo);

    List<DetallesPago> findByIdPagoReciboInAndMesCorrespondienteNotNull (List<Integer> idPagosRecibo);

}
