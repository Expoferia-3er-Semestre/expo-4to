package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.EstudianteDTO;
import expo4to.gestion_AD.dto.RepresentanteDTO;
import expo4to.gestion_AD.modelo.Estudiante;
import expo4to.gestion_AD.modelo.Representante;
import expo4to.gestion_AD.repositorio.EstudianteRepositorio;
import expo4to.gestion_AD.repositorio.RepresentanteRepositorio;
import expo4to.gestion_AD.util.Verificador;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EstudianteServicio implements IEstudianteServicio{

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;
    @Autowired
    private RepresentanteRepositorio representanteRepositorio;
    @Autowired
    private Verificador verificador;

    @Override
    public List<EstudianteDTO> listarEstudiantes() {

        List<Estudiante> lista = estudianteRepositorio.findAll();
        List<EstudianteDTO> dtos = new ArrayList<>();

        for (Estudiante estudiante : lista) {
            dtos.add(transformarEstudiante(estudiante));
        }

        return dtos;

    }

    @Override
    public EstudianteDTO buscarEstudiantePorId(Integer id) {

        Optional<Estudiante> optional = estudianteRepositorio.findById(id);

        if (optional.isEmpty()) {
            return null;
        }

        return transformarEstudiante(optional.get());
    }

    @Override
    public void guardarEstudiante(EstudianteDTO estudianteDTO) {

        String cedulaRep = estudianteDTO.getRepresentante().getCedula();

        Optional<Representante> representante = representanteRepositorio.
                findByCedula(cedulaRep);

        if (representante.isEmpty()) {
            // Se usa IllegalArgumentException para que el Controlador pueda informar a la UI.
            throw new NoSuchElementException("Error: No se puede guardar el estudiante. " +
                    "La cédula de representante (" + cedulaRep + ") no existe en la base de datos.");
        }

        if (!verificador.esNombreOApellidoValido(estudianteDTO.getNombre1()) ||
                !verificador.esNombreOApellidoValido(estudianteDTO.getNombre2())) {
            throw new IllegalArgumentException("El nombre ingresado no es valido.");
        }
        if (!verificador.esNombreOApellidoValido(estudianteDTO.getApellido1()) ||
                !verificador.esNombreOApellidoValido(estudianteDTO.getApellido2())) {
            throw new IllegalArgumentException("El apellido ingresado no es valido.");
        }
        if (!verificador.esCedulaValida(estudianteDTO.getRepresentante().getCedula())) {
            throw new IllegalArgumentException("La cédula ingresada no es valida.");
        }
        if (!verificador.esDireccionValida(estudianteDTO.getDireccion())) {
            throw new IllegalArgumentException("La dirección ingresada no es valida.");
        }

        Estudiante estudiante = transformarDto(estudianteDTO, representante.get());

        estudianteRepositorio.save(estudiante);
    }

    @Override
    public void eliminarEstudiante(Integer id) {
        estudianteRepositorio.deleteById(id);
    }

    public Estudiante transformarDto(EstudianteDTO estudianteDTO, Representante representante) {

        Boolean estado = Objects.requireNonNullElse(estudianteDTO.getEstado(), true);

        return new Estudiante(
                estudianteDTO.getId(),
                representante,
                estudianteDTO.getNombre1(),
                estudianteDTO.getNombre2(),
                estudianteDTO.getApellido1(),
                estudianteDTO.getApellido2(),
                estudianteDTO.getFechaNacimiento(),
                estudianteDTO.getDireccion(),
                estudianteDTO.getGrado(),
                estudianteDTO.getNivelAcademico(),
                estado
        );
    }

    public EstudianteDTO transformarEstudiante(Estudiante estudiante) {

        EstudianteDTO dto = new EstudianteDTO();

        dto.setId(estudiante.getId());

        RepresentanteDTO representanteDTO = new RepresentanteDTO();

        Representante representante = estudiante.getRepresentante();

        representanteDTO.setCedula(representante.getCedula());
        representanteDTO.setEstado(representante.getEstado());
        representanteDTO.setNombre1(representante.getNombre1());
        representanteDTO.setNombre2(representante.getNombre2());
        representanteDTO.setApellido1(representante.getApellido1());
        representanteDTO.setApellido2(representante.getApellido2());
        representanteDTO.setDireccion(representante.getDireccion());
        representanteDTO.setFechaN(representante.getFechaN());
        representanteDTO.setTelefono(representante.getTelefono());

        dto.setRepresentante(representanteDTO);
        dto.setNombre1(estudiante.getNombre1());
        dto.setNombre2(estudiante.getNombre2());
        dto.setApellido1(estudiante.getApellido1());
        dto.setApellido2(estudiante.getApellido2());
        dto.setFechaNacimiento(estudiante.getFechaNacimiento());
        dto.setNivelAcademico(estudiante.getNivelAcademico());
        dto.setGrado(estudiante.getGrado());
        dto.setDireccion(estudiante.getDireccion());
        dto.setEstado(estudiante.getEstado());

        return dto;
    }

}
