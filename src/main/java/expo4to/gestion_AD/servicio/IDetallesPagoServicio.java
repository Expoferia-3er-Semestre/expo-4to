package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.DetallesPago;
import java.util.List;

public interface IDetallesPagoServicio {

    public List<DetallesPago> listarPagosSinMensualidades(Integer idEstudiante);

    public List<DetallesPago> listarTodos(Integer idEstudiante);

    public List<DetallesPago> listarMensualidades(Integer idEstudiante);

    public void eliminarDetallesPago(DetallesPago dp);

    public void guardarDetallesPago(DetallesPago dp);

}
