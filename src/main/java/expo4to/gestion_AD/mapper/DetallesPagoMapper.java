package expo4to.gestion_AD.mapper;

import expo4to.gestion_AD.dto.DetallesPagoDTO;
import expo4to.gestion_AD.dto.TipoPagoDTO;
import expo4to.gestion_AD.modelo.Abono;
import expo4to.gestion_AD.modelo.DetallesPago;
import expo4to.gestion_AD.modelo.TipoPago;
import expo4to.gestion_AD.modelo.AnosEscolares;
import java.util.List;
import java.util.stream.Collectors;

public class DetallesPagoMapper {

    // ----------------------------------------------------
    // DTO -> ENTIDAD
    // ----------------------------------------------------
    public static DetallesPago toEntidad(DetallesPagoDTO dto) {
        if (dto == null) {
            return null;
        }

        DetallesPago entidad = new DetallesPago();

        // Mapeo directo
        entidad.setId(dto.getId());
        entidad.setNumTrans(dto.getNumTrans());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setMetodoPago(dto.getMetodoPago());
        entidad.setMesCorrespondiente(dto.getMesCorrespondiente());
        entidad.setMontoTotal(dto.getMontoTotal());
        entidad.setMontoPagado(dto.getMontoPagado());

        // Mapeo de referencias (asumiendo que el DTO viene con la referencia ya poblada)
        if (dto.getTipoPagoDTO() != null) {
            // Mapeo eficiente: solo necesitamos el ID para la referencia
            TipoPago tipoPagoRef = new TipoPago();
            tipoPagoRef.setId(dto.getTipoPagoDTO().getId());
            entidad.setTipoPago(tipoPagoRef);
        }

        if (dto.getAnoEscolar() != null) {
            // Asignamos el objeto de Año Escolar completo que se buscó en el servicio
            AnosEscolares anoEscolarRef = new AnosEscolares();
            anoEscolarRef.setId(dto.getAnoEscolar().getId());
            entidad.setAnoEscolar(anoEscolarRef);
        }

        // Mapeo de la colección de Abonos
        if (dto.getAbonoDTOList() != null) {
            List<Abono> abonos = AbonoMapper.toEntidadList(dto.getAbonoDTOList());
            // Asigna la relación de vuelta: CADA abono debe apuntar a este detalle
            for (Abono abono : abonos) {
                entidad.addAbono(abono);
                abono.setDetallesPago(entidad);
            }
        }

        // Nota: La relación PagoRecibo (many-to-one) se asigna en PagoReciboMapper.

        return entidad;
    }

    // Método para colecciones
    public static List<DetallesPago> toEntidadList(List<DetallesPagoDTO> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            return null;
        }
        return dtoList.stream().map(DetallesPagoMapper::toEntidad).collect(Collectors.toList());
    }

    public static DetallesPagoDTO toDTO(DetallesPago entidad) {
        if (entidad == null) {
            return null;
        }

        DetallesPagoDTO dto = new DetallesPagoDTO();

        dto.setId(entidad.getId());
        dto.setNumTrans(entidad.getNumTrans());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setMetodoPago(entidad.getMetodoPago());
        dto.setMesCorrespondiente(entidad.getMesCorrespondiente());

        // Mapeo de montos (asumiendo que los cálculos ya están hechos en la entidad)
        dto.setMontoTotal(entidad.getMontoTotal());
        dto.setMontoPagado(entidad.getMontoPagado());

        // Mapeo de referencias (solo el ID, o un DTO simple si es necesario)
        if (entidad.getTipoPago() != null) {
            // Asume que tienes un TipoPagoMapper o lo creas aquí para obtener el DTO
            dto.setTipoPagoDTO(TipoPagoMapper.toDTO(entidad.getTipoPago()));
        }

        // Mapeamos el objeto AnosEscolares (que ya fue cargado por el servicio)
        // O lo mapeamos a su DTO correspondiente (si lo tienes)
        dto.setAnoEscolar(AnoEscolarMapper.toDTO(entidad.getAnoEscolar())); // Si tu DTO acepta la entidad

        // Mapeo de la colección de Abonos (RECURSIVO)
        if (entidad.getAbonos() != null) {
            dto.setAbonoDTOList(AbonoMapper.toDTOList(entidad.getAbonos()));
        }

        // La relación PagoRecibo (many-to-one) NO se mapea para evitar ciclos

        return dto;
    }

    // Método para colecciones
    public static List<DetallesPagoDTO> toDTOList(List<DetallesPago> entidadList) {
        if (entidadList == null) {
            return null;
        }
        return entidadList.stream().map(DetallesPagoMapper::toDTO).collect(Collectors.toList());
    }

}