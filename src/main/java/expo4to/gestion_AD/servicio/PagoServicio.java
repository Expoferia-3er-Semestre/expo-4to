package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.*;
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
import java.util.NoSuchElementException;

@Service
public class PagoServicio implements IPagoServicio{

    @Autowired
    PagoReciboRepositorio reciboRepositorio;
    @Autowired
    EstudianteRepositorio estudianteRepositorio;
    @Autowired
    TipoPagoRepositorio tipoPagoRepositorio;
    @Autowired
    IAnosEscolaresServicio anosEscolaresServicio;
    @Autowired
    Verificador verificador;


    @Override
    public List<PagoReciboDTO> listarPagosPorIdEstudiante(Integer id) {

        List<PagoRecibo> pagos = reciboRepositorio.findByEstudianteId(id);
        List<PagoReciboDTO> pagosDTO = new ArrayList<>();

        if (pagos.isEmpty()) {
            throw new NoSuchElementException();
        }
        for (PagoRecibo pago : pagos) {
            pagosDTO.add(transformarDatosPago(pago));
        }

        return pagosDTO;

    }

    @Transactional
    @Override
    public void registrarNuevoPago(PagoReciboDTO datosPago) {

        expo4to.gestion_AD.modelo.PagoRecibo recibo = transformarDatosPagoDTO(datosPago);

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

    public PagoRecibo transformarDatosPagoDTO(PagoReciboDTO datosPagoDTO) {

        Estudiante estudiante = estudianteRepositorio.findById(datosPagoDTO.getIdEstudiante()).orElseThrow(null);
        AnosEscolaresDTO anoActual = anosEscolaresServicio.buscarAnoActivo();
        datosPagoDTO.actualizarMontosPagados();

        List<DetallesPagoDTO> detallesDTO = datosPagoDTO.getDetallesPagoDTOList();
        PagoRecibo recibo = new PagoRecibo();

        for (DetallesPagoDTO dto : detallesDTO) {

            DetallesPago detalle = new DetallesPago();

            if (dto.getAbonoDTOList() != null) {
                for (AbonoDTO ab : dto.getAbonoDTOList()) {

                    Abono abono = new Abono(
                            null,
                            null,
                            new Date(System.currentTimeMillis()),
                            ab.getMontoAbonado(),
                            ab.getDescripcion(),
                            ab.getMetodoPago(),
                            ab.getNumTrans()
                    );

                    detalle.addAbono(abono);

                }
            }

            try {

                if (dto.getMontoPagado().compareTo(BigDecimal.ZERO) <= 0 || dto.getMontoTotal().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("El monto debe ser mayor que 0.");
                }

                TipoPago tipoPago = new TipoPago(
                        dto.getTipoPagoDTO().getId(),
                        dto.getTipoPagoDTO().getCategoria(),
                        dto.getTipoPagoDTO().getCosto(),
                        dto.getTipoPagoDTO().getEstado()
                );

                AnosEscolares anoEscolar = new AnosEscolares(
                        anoActual.getId(),
                        anoActual.getPeriodoInicio(),
                        anoActual.getPeriodoFin(),
                        anoActual.getEstado()
                );

                detalle.setTipoPago(tipoPago);
                detalle.setAnoEscolar(anoEscolar);
                detalle.setNumTrans(dto.getNumTrans());
                detalle.setDescripcion(dto.getDescripcion());
                detalle.setMetodoPago(dto.getMetodoPago());
                detalle.setMesCorrespondiente(dto.getMesCorrespondiente());
                detalle.setMontoTotal(dto.getMontoTotal());
                detalle.setMontoPagado(dto.getMontoPagado());
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

    public PagoReciboDTO transformarDatosPago(PagoRecibo datosPago) {
        PagoReciboDTO pagoReciboDTO = new PagoReciboDTO();



        return pagoReciboDTO;
    }

}
