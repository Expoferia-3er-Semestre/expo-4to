package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.DetallesPago;
import expo4to.gestion_AD.repositorio.DetallesPagoRepositorio;
import expo4to.gestion_AD.repositorio.PagoReciboRepositorio;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallesPagoServicio implements IDetallesPagoServicio {

    @Autowired
    PagoReciboRepositorio prRepo;
    @Autowired
    DetallesPagoRepositorio dpRepo;

    @Override
    public List<DetallesPago> listarPagosSinMensualidades(Integer idEstudiante) {

        List<Integer> idsPagos = prRepo.findByIdEstudiante(idEstudiante);

        return dpRepo.findByIdPagoReciboInAndMesCorrespondienteIsNull(idsPagos);

    }

    @Override
    public List<DetallesPago> listarTodos(Integer idEstudiante) {
        List<Integer> idsPagos = prRepo.findByIdEstudiante(idEstudiante);

        return dpRepo.findByIdPagoReciboIn(idsPagos);
    }

    @Override
    public List<DetallesPago> listarMensualidades(Integer idEstudiante) {
        List<Integer> idsPagos = prRepo.findByIdEstudiante(idEstudiante);

        return dpRepo.findByIdPagoReciboInAndMesCorrespondienteNotNull(idsPagos);
    }

    @Override
    public void eliminarDetallesPago(DetallesPago dp) {

    }

    @Override
    public void guardarDetallesPago(DetallesPago dp) {

    }



}
