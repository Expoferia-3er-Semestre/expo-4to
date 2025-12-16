package expo4to.gestion_AD.mapper;

import expo4to.gestion_AD.dto.TrabajadorDTO;
import expo4to.gestion_AD.modelo.Trabajador;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TrabajadorMapper {
    // --------------------------------------------------------------------------
    // MAPPERS DE TRABAJADOR
    // --------------------------------------------------------------------------

    public static TrabajadorDTO toTrabajadorDTO(Trabajador trabajador) {

        if (trabajador == null){
            return null;
        }

        TrabajadorDTO dto = new TrabajadorDTO();
        dto.setId(trabajador.getId());
        dto.setCedula(trabajador.getCedula());
        dto.setNombre1(trabajador.getNombre1());
        dto.setNombre2(trabajador.getNombre2());
        dto.setApellido1(trabajador.getApellido1());
        dto.setApellido2(trabajador.getApellido2());
        dto.setTelefono(trabajador.getTelefono());
        dto.setFechaN(trabajador.getFechaN());
        dto.setDireccion(trabajador.getDireccion());
        dto.setEstado(trabajador.getEstado());
        dto.setCorreo(trabajador.getCorreo());
        dto.setRol(trabajador.getRol());

        return dto;
    }

    public static Trabajador toTrabajadorEntidad(TrabajadorDTO dto){
        if (dto == null){
            return null;
        }

        Trabajador entidad = new Trabajador();
        entidad.setId(dto.getId());
        entidad.setCedula(dto.getCedula());
        entidad.setNombre1(dto.getNombre1());
        entidad.setNombre2(dto.getNombre2());
        entidad.setApellido1(dto.getApellido1());
        entidad.setApellido2(dto.getApellido2());
        entidad.setTelefono(dto.getTelefono());
        entidad.setFechaN(dto.getFechaN());
        entidad.setDireccion(dto.getDireccion());
        entidad.setEstado(dto.getEstado());
        entidad.setCorreo(dto.getCorreo());
        entidad.setContrasena(dto.getContrasena());
        entidad.setRol(dto.getRol());


        return entidad;
    }

    public static List<TrabajadorDTO> toTrabajadorDTOList (List<Trabajador> trabajador) {
        return trabajador.stream()
                .map(TrabajadorMapper::toTrabajadorDTO)
                .collect(Collectors.toList());
    }
}
