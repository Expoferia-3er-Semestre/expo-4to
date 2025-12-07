package expo4to.gestion_AD.mapper;

import expo4to.gestion_AD.dto.AbonoDTO;
import expo4to.gestion_AD.modelo.Abono;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AbonoMapper {

    // ----------------------------------------------------
    // DTO -> ENTIDAD
    // ----------------------------------------------------
    public static Abono toEntidad(AbonoDTO dto) {
        if (dto == null) {
            return null;
        }

        Abono entidad = new Abono();

        entidad.setId(dto.getId());
        // Se asume que la fecha de abono es la actual al momento de guardar
        entidad.setFechaAbono(new Date(System.currentTimeMillis()));

        entidad.setMontoAbonado(dto.getMontoAbonado());

        entidad.setDescripcion(dto.getDescripcion());
        entidad.setMetodoPago(dto.getMetodoPago());
        entidad.setNumTrans(dto.getNumTrans());

        // Las relaciones (detallePago) se deben asignar en la capa superior (DetallesPagoMapper)

        return entidad;
    }

    // Método para colecciones
    public static List<Abono> toEntidadList(List<AbonoDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream().map(AbonoMapper::toEntidad).collect(Collectors.toList());
    }

    public static AbonoDTO toDTO(Abono entidad) {
        if (entidad == null) {
            return null;
        }

        AbonoDTO dto = new AbonoDTO();

        dto.setId(entidad.getId());

        // Mapeamos el BigDecimal directamente al DTO
        dto.setMontoAbonado(entidad.getMontoAbonado());

        dto.setDescripcion(entidad.getDescripcion());
        dto.setMetodoPago(entidad.getMetodoPago());
        dto.setNumTrans(entidad.getNumTrans());

        // La fecha de abono puede ser mapeada si la necesitas en el DTO
        // dto.setFechaAbono(entidad.getFechaAbono());

        // La relación DetallePago NO se mapea aquí para evitar ciclos (lazy loading)

        return dto;
    }

    // Método para colecciones
    public static List<AbonoDTO> toDTOList(List<Abono> entidadList) {
        if (entidadList == null) {
            return null;
        }
        return entidadList.stream().map(AbonoMapper::toDTO).collect(Collectors.toList());
    }

}