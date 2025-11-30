package expo4to.gestion_AD.repositorio;

import expo4to.gestion_AD.modelo.DetallesPago;
import expo4to.gestion_AD.modelo.PagoRecibo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetallesPagoRepositorio extends JpaRepository<DetallesPago, Integer> {

    // 1. Buscar todos los detalles asociados a la lista de entidades PagoRecibo
    List<DetallesPago> findByPagoReciboIn(List<PagoRecibo> pagosRecibo);

    // 2. Buscar detalles asociados a los recibos donde el mes es nulo
    List<DetallesPago> findByPagoReciboInAndMesCorrespondienteIsNull(List<PagoRecibo> pagosRecibo);

    // 3. Buscar detalles asociados a los recibos donde el mes NO es nulo
    List<DetallesPago> findByPagoReciboInAndMesCorrespondienteNotNull(List<PagoRecibo> pagosRecibo);
}
