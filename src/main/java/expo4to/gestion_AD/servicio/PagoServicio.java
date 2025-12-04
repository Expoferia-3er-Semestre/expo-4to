package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.*;
import expo4to.gestion_AD.mapper.AnoEscolarMapper;
import expo4to.gestion_AD.mapper.DetallesPagoMapper;
import expo4to.gestion_AD.mapper.PagoReciboMapper;
import expo4to.gestion_AD.mapper.TipoPagoMapper;
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
import java.util.Optional;

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


    @Override
    public List<PagoReciboDTO> listarTodoEstudiante(Integer id) {

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

    @Override
    public List<PagoReciboDTO> listarMensualidadesEstudiante(Integer id) {

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

    @Override
    public List<PagoReciboDTO> listarNoMensualidadesEstudiante(Integer id) {
        return List.of();
    }

    @Override
    @Transactional
    public DetallesPagoDTO obtenerMensualidadPendiente(Integer estudianteId) {

        Optional<AnosEscolares> optional1 = anosEscolaresRepositorio.findActivoVigente(
                new Date(System.currentTimeMillis()));

        if (optional1.isEmpty()) {
            throw new RuntimeException("No hay años escolares activos.");
        }

        List<DetallesPago> mensualidades = reciboRepositorio.findMensualidadesPorEstudianteYAno(
                estudianteId,
                optional1.get().getId()
        );

        if (!mensualidades.isEmpty()) {
            // I. Prioridad: Buscar Mensualidad ya creada, pero NO PAGADA totalmente
            for (DetallesPago detalle : mensualidades) {
                // Debes implementar una lógica que compare montoTotal vs montoPagado
                // Si el detalle aún no está totalmente pagado:
                if (detalle.tieneSaldoPendiente()) {
                    detalle.getAbonos().isEmpty();
                    return DetallesPagoMapper.toDTO(detalle); // <-- Retorna la que falta por abonar
                }
            }
        }

        // II. Si no hay pendientes de abonar: Buscar el próximo mes pendiente

        // 1. Identificar el último mes registrado.
        int ultimoMesRegistrado = mensualidades.isEmpty() ? 0 : mensualidades.get(mensualidades.size() - 1).getMesCorrespondiente();

        // 2. Definir el próximo mes a registrar.
        int proximoMes = ultimoMesRegistrado + 1;
        // 3. Verificar si el próximo mes está dentro del rango escolar/lógico (ej. 1 a 12)
        if (proximoMes <= 12) { // Asumiendo que solo hay 12 meses
            // Aquí NO se retorna un DetallesPago existente, sino un placeholder
            // o se crea una nueva entidad/DTO para ese mes.

            // **OPCIÓN: Devuelve null o un DTO especial que indique CREAR NUEVO PAGO.**

            Optional<TipoPago> optional = tipoPagoRepositorio.findById(1);



            // Lógica para crear el DTO del nuevo mes (ej. con el monto base de la mensualidad)
            DetallesPagoDTO detalleAPagar = new DetallesPagoDTO();
            optional.ifPresent(tipoPago -> detalleAPagar.setTipoPagoDTO(TipoPagoMapper.toDTO(tipoPago)));
            optional1.ifPresent(anosEscolares -> detalleAPagar.setAnoEscolar(AnoEscolarMapper.toDTO(anosEscolares)));
            detalleAPagar.setMesCorrespondiente(proximoMes);
            detalleAPagar.setMontoPagado(BigDecimal.ZERO);
            detalleAPagar.setMontoTotal(detalleAPagar.getTipoPagoDTO().getCosto());
            return detalleAPagar;

        } else {
            // Ya pagó todas las mensualidades posibles en el año
            return null;
        }
    }

    @Transactional
    @Override
    public void registrarNuevoPago(PagoReciboDTO datosPago) {

        expo4to.gestion_AD.modelo.PagoRecibo recibo = transformarDatosPagoDTO(datosPago);

        reciboRepositorio.save(recibo);

    }

    public PagoRecibo transformarDatosPagoDTO(PagoReciboDTO datosPagoDTO) {

        Optional<Estudiante> estudiante = estudianteRepositorio.findById(datosPagoDTO.getIdEstudiante());

        if (estudiante.isEmpty()) {
            throw new NoSuchElementException();
        }
        datosPagoDTO.actualizarMontosPagados();
        datosPagoDTO.actualizarMontoTotal();

        PagoRecibo recibo = PagoReciboMapper.toEntidad(datosPagoDTO);

        recibo.setMontoPagado(datosPagoDTO.getMontoPagado());
        recibo.setMontoTotal(datosPagoDTO.getMontoTotal());
        recibo.setFechaPago(new Date(System.currentTimeMillis()));
        recibo.setEstudiante(estudiante.get());
        recibo.setEstado(datosPagoDTO.tienependiente());
        return recibo;

    }

    public PagoReciboDTO transformarDatosPago(PagoRecibo datosPago) {
        return PagoReciboMapper.toDTO(datosPago);
    }

}
