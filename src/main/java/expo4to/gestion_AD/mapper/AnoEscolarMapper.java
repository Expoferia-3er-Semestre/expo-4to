package expo4to.gestion_AD.mapper;

import expo4to.gestion_AD.dto.AnosEscolaresDTO;
import expo4to.gestion_AD.modelo.AnosEscolares;
import java.util.List;
import java.util.stream.Collectors;

public class AnoEscolarMapper {

    // ----------------------------------------------------
    // DTO (Transferencia) -> ENTITY (Persistencia)
    // ----------------------------------------------------
    /**
     * Convierte un AnosEscolaresDTO a la entidad AnosEscolares.
     */
    public static AnosEscolares toEntidad(AnosEscolaresDTO dto) {
        if (dto == null) {
            return null;
        }

        AnosEscolares entidad = new AnosEscolares();

        // Mapeo de campos
        entidad.setId(dto.getId());
        entidad.setPeriodoInicio(dto.getPeriodoInicio());
        entidad.setPeriodoFin(dto.getPeriodoFin());
        entidad.setEstado(dto.getEstado());

        return entidad;
    }

    // ----------------------------------------------------
    // ENTITY (Persistencia) -> DTO (Transferencia)
    // ----------------------------------------------------
    /**
     * Convierte la entidad AnosEscolares a AnosEscolaresDTO.
     */
    public static AnosEscolaresDTO toDTO(AnosEscolares entidad) {
        if (entidad == null) {
            return null;
        }

        AnosEscolaresDTO dto = new AnosEscolaresDTO();

        // Mapeo de campos
        dto.setId(entidad.getId());
        dto.setPeriodoInicio(entidad.getPeriodoInicio());
        dto.setPeriodoFin(entidad.getPeriodoFin());
        dto.setEstado(entidad.getEstado());

        return dto;
    }

    // ----------------------------------------------------
    // Métodos para colecciones (Listas)
    // ----------------------------------------------------
    public static List<AnosEscolares> toEntidadList(List<AnosEscolaresDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream().map(AnoEscolarMapper::toEntidad).collect(Collectors.toList());
    }

    public static List<AnosEscolaresDTO> toDTOList(List<AnosEscolares> entidadList) {
        if (entidadList == null) {
            return null;
        }
        return entidadList.stream().map(AnoEscolarMapper::toDTO).collect(Collectors.toList());
    }
}