package expo4to.gestion_AD.mapper;

import expo4to.gestion_AD.dto.TipoPagoDTO;
import expo4to.gestion_AD.modelo.TipoPago;
import java.util.List;
import java.util.stream.Collectors;

public class TipoPagoMapper {

    // ----------------------------------------------------
    // DTO (Transferencia) -> ENTITY (Persistencia)
    // ----------------------------------------------------
    /**
     * Convierte un TipoPagoDTO a la entidad TipoPago.
     */
    public static TipoPago toEntidad(TipoPagoDTO dto) {
        if (dto == null) {
            return null;
        }

        TipoPago entidad = new TipoPago();

        // Mapeo de campos simples
        entidad.setId(dto.getId());
        entidad.setCategoria(dto.getCategoria());
        entidad.setCosto(dto.getCosto()); // Asumiendo BigDecimal
        entidad.setEstado(dto.getEstado());

        // No hay relaciones complejas que mapear aquí

        return entidad;
    }

    // ----------------------------------------------------
    // ENTITY (Persistencia) -> DTO (Transferencia)
    // ----------------------------------------------------
    /**
     * Convierte la entidad TipoPago a TipoPagoDTO.
     */
    public static TipoPagoDTO toDTO(TipoPago entidad) {
        if (entidad == null) {
            return null;
        }

        TipoPagoDTO dto = new TipoPagoDTO();

        // Mapeo de campos simples
        dto.setId(entidad.getId());
        dto.setCategoria(entidad.getCategoria());
        dto.setCosto(entidad.getCosto()); // Asumiendo BigDecimal
        dto.setEstado(entidad.getEstado());

        return dto;
    }

    // ----------------------------------------------------
    // Métodos para colecciones
    // ----------------------------------------------------
    public static List<TipoPago> toEntidadList(List<TipoPagoDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream().map(TipoPagoMapper::toEntidad).collect(Collectors.toList());
    }

    public static List<TipoPagoDTO> toDTOList(List<TipoPago> entidadList) {
        if (entidadList == null) {
            return null;
        }
        return entidadList.stream().map(TipoPagoMapper::toDTO).collect(Collectors.toList());
    }
}