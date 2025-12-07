package expo4to.gestion_AD.mapper;

import expo4to.gestion_AD.dto.PagoReciboDTO;
import expo4to.gestion_AD.modelo.DetallesPago;
import expo4to.gestion_AD.modelo.PagoRecibo;
import expo4to.gestion_AD.modelo.Estudiante;
import java.util.List;
import java.util.stream.Collectors;

public class PagoReciboMapper {

    // ----------------------------------------------------
    // DTO -> ENTIDAD
    // ----------------------------------------------------
    public static PagoRecibo toEntidad(PagoReciboDTO dto) {
        if (dto == null) {
            return null;
        }

        PagoRecibo entidad = new PagoRecibo();

        // Mapeo de campos simples y calculados
        entidad.setId(dto.getId());
        entidad.setMontoTotal(dto.getMontoTotal());
        entidad.setMontoPagado(dto.getMontoPagado());
        entidad.setFechaPago(dto.getFechaPago()); // Asumiendo que la fecha se setea antes de mapear
        entidad.setEstado(dto.getEstado());       // Asumiendo que el estado se calcula antes de mapear

        // Mapeo de la referencia a Estudiante (solo se necesita el ID)
        Estudiante estudianteRef = new Estudiante();
        estudianteRef.setId(dto.getIdEstudiante());
        entidad.setEstudiante(estudianteRef);

        // Mapeo de la colección de Detalles de Pago
        if (!dto.getDetallesPagoDTOList().isEmpty()) {
            List<DetallesPago> detalles = DetallesPagoMapper.toEntidadList(dto.getDetallesPagoDTOList());

            // Asigna la relación de vuelta: CADA detalle debe apuntar a este recibo
            for (DetallesPago detalle : detalles) {
                detalle.setPagoRecibo(entidad);
                entidad.addDetalle(detalle);
            }
        }

        return entidad;
    }

    public static PagoReciboDTO toDTO(PagoRecibo entidad) {
        if (entidad == null) {
            return null;
        }

        PagoReciboDTO dto = new PagoReciboDTO();

        dto.setId(entidad.getId());
        dto.setMontoTotal(entidad.getMontoTotal());
        dto.setMontoPagado(entidad.getMontoPagado());
        dto.setFechaPago(entidad.getFechaPago());
        dto.setEstado(entidad.getEstado());

        // Mapeo de la referencia a Estudiante (solo se necesita el ID)
        if (entidad.getEstudiante() != null) {
            dto.setIdEstudiante(entidad.getEstudiante().getId());
        }

        // Mapeo de la colección de Detalles de Pago (RECURSIVO)
        if (entidad.getDetalles() != null) {
            dto.setDetallesPagoDTOList(DetallesPagoMapper.toDTOList(entidad.getDetalles()));
        }

        return dto;
    }

    // Método para colecciones
    public static List<PagoReciboDTO> toDTOList(List<PagoRecibo> entidadList) {
        if (entidadList == null) {
            return null;
        }
        return entidadList.stream().map(PagoReciboMapper::toDTO).collect(Collectors.toList());
    }

    // Aquí puedes agregar un toDTO si lo necesitas para la vista
}