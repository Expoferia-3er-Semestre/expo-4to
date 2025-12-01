package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.AbonoDTO;
import expo4to.gestion_AD.dto.DetallesPagoDTO;
import expo4to.gestion_AD.dto.MontosDTO;
import expo4to.gestion_AD.dto.PagoReciboDTO;
import expo4to.gestion_AD.modelo.*;
import expo4to.gestion_AD.repositorio.*;
import expo4to.gestion_AD.util.Verificador;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class PagoServicio implements IPagoServicio{

    @Autowired
    PagoReciboRepositorio reciboRepositorio;
    @Autowired
    EstudianteRepositorio estudianteRepositorio;
    @Autowired
    TipoPagoRepositorio tipoPagoRepositorio;
    @Autowired
    AnosEscolaresRepositorio anosEscolaresRepositorio;
    @Autowired
    Verificador verificador;

    @Transactional
    @Override
    public void registrarNuevoPago( PagoReciboDTO datosPago) {

        expo4to.gestion_AD.modelo.PagoRecibo recibo = transformarDatosPago(datosPago);

        reciboRepositorio.save(recibo);

    }

    private MontosDTO contarMontos(List<DetallesPago> detalles) {

        if (detalles == null || detalles.isEmpty()) {
            throw new RuntimeException();
        }

        BigDecimal total = BigDecimal.ZERO;
        BigDecimal pagado = BigDecimal.ZERO;

        for (DetallesPago dp : detalles) {
            total = total.add(dp.getMontoTotal());
            pagado = pagado.add(dp.getMontoPagado());
        }

        return new MontosDTO(total, pagado);

    }

    public PagoRecibo transformarDatosPago(PagoReciboDTO datosPago) {

        Estudiante estudiante = estudianteRepositorio.findById(datosPago.getIdEstudiante()).orElseThrow(null);

        List<DetallesPagoDTO> detallesDTO = datosPago.getDetallesPagoDTOList();
        PagoRecibo recibo = new PagoRecibo();

        for (DetallesPagoDTO dto : detallesDTO) {

            TipoPago tipoPago = tipoPagoRepositorio.findById(dto.getIdTipoPago()).orElseThrow(null);
            AnosEscolares anoEscolar = anosEscolaresRepositorio.findById(dto.getIdAnoEscolar()).orElseThrow(null);

            DetallesPago detalle = new DetallesPago();

            if (dto.getAbonoDTOList() != null) {
                for (AbonoDTO ab : dto.getAbonoDTOList()) {

                    BigDecimal montoAbonado = new BigDecimal(ab.getMontoAbonado());

                    Abono abono = new Abono(
                            null,
                            null,
                            new Date(System.currentTimeMillis()),
                            montoAbonado,
                            ab.getDescripcion(),
                            ab.getMetodoPago(),
                            ab.getNumTrans()
                    );

                    detalle.addAbono(abono);

                }
            }

            String montoPagadoString = dto.getMontoPagado();
            String montoTotalString = dto.getMontoTotal();
            BigDecimal montoPagado;
            BigDecimal montoTotal;

            try {
                montoPagado = new BigDecimal(montoPagadoString);
                montoTotal = new BigDecimal(montoTotalString);

                if (montoPagado.compareTo(BigDecimal.ZERO) <= 0 || montoTotal.compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("El monto debe ser mayor que 0.");
                }

                detalle.setTipoPago(tipoPago);
                detalle.setAnoEscolar(anoEscolar);
                detalle.setNumTrans(dto.getNumTrans());
                detalle.setDescripcion(dto.getDescripcion());
                detalle.setMesCorrespondiente(dto.getMesCorrespondiente());
                detalle.setMontoTotal(montoTotal);
                detalle.setMontoPagado(montoPagado);
                recibo.addDetalle(detalle);

            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error de formato en un ítem de pago");
            }

        }

        MontosDTO montos = contarMontos(recibo.getDetalles());
        recibo.setMontoPagado(montos.getPagadoTotal());
        recibo.setMontoTotal(montos.getMontoTotal());
        recibo.setFechaPago(new Date(System.currentTimeMillis()));
        recibo.setEstudiante(estudiante);
        recibo.setEstado(montos.tienependiente());
        return recibo;

    }

}
