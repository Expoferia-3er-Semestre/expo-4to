package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.AbonoDTO;
import expo4to.gestion_AD.dto.DetallesPagoDTO;
import expo4to.gestion_AD.dto.PagoReciboDTO;
import expo4to.gestion_AD.modelo.Abono;
import expo4to.gestion_AD.modelo.DetallesPago;
import expo4to.gestion_AD.modelo.PagoRecibo;
import expo4to.gestion_AD.repositorio.AbonoRepositorio;
import expo4to.gestion_AD.repositorio.DetallesPagoRepositorio;
import expo4to.gestion_AD.repositorio.PagoReciboRepositorio;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PagoServicio implements IPagoServicio{

    @Autowired
    PagoReciboRepositorio reciboRepositorio;
    @Autowired
    DetallesPagoRepositorio DetallesRepositorio;
    @Autowired
    AbonoRepositorio abonoRepositorio;

    @Override
    public void registrarNuevoPago(PagoReciboDTO prDTO, List<DetallesPagoDTO> dpsDTO, List<AbonoDTO> absDTO) {

    }

    public DetallesPago transformarDetallesDTO(DetallesPagoDTO dto){

        return new DetallesPago(
                null,
                dto.getIdPagoRecibo(),
                dto.getIdTipoPago(),
                dto.getMetodoPago(),
                dto.getNumTrans(),
                dto.getIdAnoEscolar(),
                dto.getDescripcion(),
                dto.getMesCorrespondiente(),
                dto.getMontoTotal(),
                dto.getMontoPagado()
        );

    }

    public PagoRecibo transformarReciboDTO(PagoReciboDTO dto){

        BigDecimal pendiente = dto.getMontoTotal().subtract(dto.getMontoPagado());

        //Se redondea según estandares bancarios
        BigDecimal pendienteRound = pendiente.setScale(2, RoundingMode.HALF_UP);
        Boolean estado = pendienteRound.compareTo(BigDecimal.ZERO) > 0;

        return new PagoRecibo(
                null,
                dto.getIdEstudiante(),
                dto.getMontoTotal(),
                dto.getMontoPagado(),
                estado,
                dto.getFechaPago()
        );

    }

    public Abono transformarAbonoDTO(AbonoDTO dto) {

        return new Abono(
                null,
                dto.getIdDetallesPagos(),
                dto.getFechaAbono(),
                dto.getMontoAbonado(),
                dto.getDescripcion(),
                dto.getMetodoPago(),
                dto.getNumTrans()
        );

    }
}
