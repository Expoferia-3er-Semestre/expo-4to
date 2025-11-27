package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.TipoPago;
import expo4to.gestion_AD.repositorio.TipoPagoRepositorio;
import expo4to.gestion_AD.util.Verificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TipoPagoServicio implements ITipoPagoServicio{

    @Autowired
    TipoPagoRepositorio tpRepositorio;
    @Autowired
    Verificador verificador;

    @Override
    public List<TipoPago> listarTipoPagos() {
        return tpRepositorio.findAll();
    }

    @Override
    public TipoPago buscarTipoPago(Integer id) {
        return tpRepositorio.findById(id).orElse(null);
    }

    @Override
    public void guardarTipoPago(TipoPago tp, String costo) {

        if (verificador.esNumeroDecimalValido(costo)) {
            throw new IllegalArgumentException("El formato del monto no es un número válido.");
        }
        if (verificador.esMontoPositivo(tp.getCosto())) {
            throw new IllegalArgumentException("El monto del pago debe ser positivo.");
        }
        if (verificador.esNombreOApellidoValido(tp.getCategoria())) {
            throw new IllegalArgumentException("La categoría ingresada no es valida.");
        }
        if (verificador.tieneMaximoDecimales(costo, 2)) {
            throw new IllegalArgumentException("El monto no debe tener mas de dos decimales.");
        }

        try {
            BigDecimal monto = new BigDecimal(costo);
            tp.setCosto(monto);
            tpRepositorio.save(tp);
        } catch (NumberFormatException e) {
            System.err.println("Error de formato: La cadena no es un número válido.");
        }

    }

    @Override
    public void eliminarTipoPago(TipoPago tp) {
        /*
        RECORDATORIO
        en TipoPagoServicio no permitir eliminar
        un tp si tiene pagos vinculados

        Implementar las validaciones necesarias mas adelante
         */
        tpRepositorio.delete(tp);
    }
}
