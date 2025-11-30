package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.DetallesPago;
import expo4to.gestion_AD.modelo.Estudiante;
import expo4to.gestion_AD.modelo.PagoRecibo;
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

        List<PagoRecibo> idsPagos = prRepo.findByEstudianteId(idEstudiante);

        return dpRepo.findByPagoReciboInAndMesCorrespondienteIsNull(idsPagos);

    }

    @Override
    public List<DetallesPago> listarTodos(Integer idEstudiante) {

        List<PagoRecibo> idsPagos = prRepo.findByEstudianteId(idEstudiante);

        return dpRepo.findByPagoReciboIn(idsPagos);
    }

    @Override
    public List<DetallesPago> listarMensualidades(Integer idEstudiante) {
        List<PagoRecibo> idsPagos = prRepo.findByEstudianteId(idEstudiante);

        return dpRepo.findByPagoReciboInAndMesCorrespondienteNotNull(idsPagos);
    }

    @Override
    public void eliminarDetallesPago(DetallesPago dp) {

    }

    @Override
    public void guardarDetallesPago(DetallesPago dp) {

    }



}
