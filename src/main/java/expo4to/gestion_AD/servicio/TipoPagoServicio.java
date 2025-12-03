package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.TipoPagoDTO;
import expo4to.gestion_AD.modelo.TipoPago;
import expo4to.gestion_AD.repositorio.TipoPagoRepositorio;
import expo4to.gestion_AD.util.Verificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TipoPagoServicio implements ITipoPagoServicio{

    @Autowired
    TipoPagoRepositorio tpRepositorio;
    @Autowired
    Verificador verificador;

    @Override
    public List<TipoPagoDTO> listarTipoPagos() {
        List<TipoPago> tipos = tpRepositorio.findAll();
        List<TipoPagoDTO> dtos = new ArrayList<>();

        if (tipos.isEmpty()) {
            throw new NoSuchElementException();
        }
        for (TipoPago tp : tipos){
            dtos.add(transformarTipoPago(tp));
        }

        return dtos;

    }

    @Override
    public TipoPagoDTO buscarTipoPago(Integer id) {
        Optional<TipoPago> optional = tpRepositorio.findById(id);

        if (optional.isEmpty()) {
            throw new NoSuchElementException();
        }
        return transformarTipoPago(optional.get());
    }

    @Override
    public void guardarTipoPago(TipoPagoDTO tipoPagoDTO, String costo) {

        if (verificador.esNumeroDecimalValido(costo)) {
            throw new IllegalArgumentException("El formato del monto no es un número válido.");
        }
        if (verificador.esMontoPositivo(tipoPagoDTO.getCosto())) {
            throw new IllegalArgumentException("El monto del pago debe ser positivo.");
        }
        if (verificador.esNombreOApellidoValido(tipoPagoDTO.getCategoria())) {
            throw new IllegalArgumentException("La categoría ingresada no es valida.");
        }
        if (verificador.tieneMaximoDecimales(costo, 2)) {
            throw new IllegalArgumentException("El monto no debe tener mas de dos decimales.");
        }

        try {
            BigDecimal monto = new BigDecimal(costo);
            tipoPagoDTO.setCosto(monto);

            TipoPago tp = transformarDTO(tipoPagoDTO);
            tpRepositorio.save(tp);
        } catch (NumberFormatException e) {
            System.err.println("Error de formato: La cadena no es un número válido.");
        }

    }

    @Override
    public void eliminarTipoPago(Integer id) {
        /*
        RECORDATORIO
        en TipoPagoServicio no permitir eliminar
        un tp si tiene pagos vinculados

        Implementar las validaciones necesarias mas adelante
         */
        tpRepositorio.deleteById(id);
    }

    public TipoPago transformarDTO(TipoPagoDTO tipoPagoDTO) {

        Boolean estado = Objects.requireNonNullElse(tipoPagoDTO.getEstado(), true);

        return new TipoPago(
                tipoPagoDTO.getId(),
                tipoPagoDTO.getCategoria(),
                tipoPagoDTO.getCosto(),
                estado
        );

    }

    public TipoPagoDTO transformarTipoPago(TipoPago tipoPago) {

        TipoPagoDTO dto = new TipoPagoDTO();
        dto.setId(tipoPago.getId());
        dto.setEstado(tipoPago.getEstado());
        dto.setCosto(tipoPago.getCosto());
        dto.setCategoria(tipoPago.getCategoria());

        return dto;

    }

}
