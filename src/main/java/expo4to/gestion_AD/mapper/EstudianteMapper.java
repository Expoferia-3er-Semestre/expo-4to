package expo4to.gestion_AD.mapper;

import expo4to.gestion_AD.dto.EstudianteDTO;
import expo4to.gestion_AD.dto.RepresentanteDTO;
import expo4to.gestion_AD.modelo.Estudiante;
import expo4to.gestion_AD.modelo.Representante;

import java.util.List;
import java.util.stream.Collectors;

public class EstudianteMapper {

    // --------------------------------------------------------------------------
    // MAPPERS DE REPRESENTANTE
    // --------------------------------------------------------------------------

    public static RepresentanteDTO toRepresentanteDTO(Representante representante) {
        if (representante == null) {
            return null;
        }

        RepresentanteDTO dto = new RepresentanteDTO();
        dto.setCedula(representante.getCedula());
        dto.setNombre1(representante.getNombre1());
        dto.setNombre2(representante.getNombre2());
        dto.setApellido1(representante.getApellido1());
        dto.setApellido2(representante.getApellido2());
        dto.setTelefono(representante.getTelefono());
        dto.setFechaN(representante.getFechaN());
        dto.setDireccion(representante.getDireccion());
        dto.setEstado(representante.getEstado());

        // NO mapeamos la lista de Estudiantes aquí para evitar problemas de Lazy Loading y recursión infinita
        // Si necesitas la lista, se haría un mapeo explícito en el servicio.

        return dto;
    }

    public static Representante toRepresentanteEntidad(RepresentanteDTO dto) {
        if (dto == null) {
            return null;
        }

        Representante entidad = new Representante();
        entidad.setCedula(dto.getCedula());
        entidad.setNombre1(dto.getNombre1());
        entidad.setNombre2(dto.getNombre2());
        entidad.setApellido1(dto.getApellido1());
        entidad.setApellido2(dto.getApellido2());
        entidad.setTelefono(dto.getTelefono());
        entidad.setFechaN(dto.getFechaN());
        entidad.setDireccion(dto.getDireccion());
        entidad.setEstado(dto.getEstado());

        // La lista de estudiantes no se mapea automáticamente para evitar sobreescritura accidental.

        return entidad;
    }

    // --------------------------------------------------------------------------
    // MAPPERS DE ESTUDIANTE
    // --------------------------------------------------------------------------

    public static EstudianteDTO toEstudianteDTO(Estudiante estudiante) {
        if (estudiante == null) {
            return null;
        }

        EstudianteDTO dto = new EstudianteDTO();
        dto.setId(estudiante.getId());
        dto.setNombre1(estudiante.getNombre1());
        dto.setNombre2(estudiante.getNombre2());
        dto.setApellido1(estudiante.getApellido1());
        dto.setApellido2(estudiante.getApellido2());
        dto.setFechaNacimiento(estudiante.getFechaNacimiento());
        dto.setDireccion(estudiante.getDireccion());
        dto.setGrado(estudiante.getGrado());
        dto.setNivelAcademico(estudiante.getNivelAcademico());
        dto.setEstado(estudiante.getEstado());

        // Mapea la relación: Incluye el DTO del Representante
        dto.setRepresentante(toRepresentanteDTO(estudiante.getRepresentante()));

        return dto;
    }

    public static Estudiante toEstudianteEntidad(EstudianteDTO dto) {
        if (dto == null) {
            return null;
        }

        Estudiante entidad = new Estudiante();
        entidad.setId(dto.getId()); // ¡Importante para operaciones de MERGE/UPDATE!
        entidad.setNombre1(dto.getNombre1());
        entidad.setNombre2(dto.getNombre2());
        entidad.setApellido1(dto.getApellido1());
        entidad.setApellido2(dto.getApellido2());
        entidad.setFechaNacimiento(dto.getFechaNacimiento());
        entidad.setDireccion(dto.getDireccion());
        entidad.setGrado(dto.getGrado());
        entidad.setNivelAcademico(dto.getNivelAcademico());
        entidad.setEstado(dto.getEstado());

        // Mapea la relación: Convierte el RepresentanteDTO a la entidad Representante
        // Nota: Asegúrate de que el Representante exista en la DB si estás guardando un Estudiante
        entidad.setRepresentante(toRepresentanteEntidad(dto.getRepresentante()));

        return entidad;
    }

    // Método para mapear listas (útil para la visualización en JTable)
    public static List<EstudianteDTO> toEstudianteDTOList(List<Estudiante> estudiantes) {
        return estudiantes.stream()
                .map(EstudianteMapper::toEstudianteDTO)
                .collect(Collectors.toList());
    }
}