package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.EstudianteDTO;
import expo4to.gestion_AD.modelo.Estudiante;
import expo4to.gestion_AD.repositorio.EstudianteRepositorio;
import expo4to.gestion_AD.repositorio.RepresentanteRepositorio;
import expo4to.gestion_AD.util.Verificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EstudianteServicio implements IEstudianteServicio{

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;
    @Autowired
    private RepresentanteRepositorio representanteRepositorio;
    @Autowired
    private Verificador verificador;

    @Override
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepositorio.findAll();
    }

    @Override
    public Estudiante buscarEstudiantePorId(Integer id) {
        // NoSuchElementException es una excepción estándar de Java
        // que se usa para indicar que un elemento no existe.
        return estudianteRepositorio.findById(id).orElseThrow(
                () -> new java.util.NoSuchElementException("Estudiante con ID " + id + " no encontrado.")
        );
    }

    @Override
    public void guardarEstudiante(EstudianteDTO estudianteDTO) {

        String cedulaRep = estudianteDTO.getCedulaRep();

        expo4to.gestion_AD.modelo.Representante representante = representanteRepositorio.
                findByCedula(cedulaRep).orElse(null);

        if (representante == null) {
            // Se usa IllegalArgumentException para que el Controlador pueda informar a la UI.
            throw new IllegalArgumentException("Error: No se puede guardar el estudiante. " +
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
        if (!verificador.esCedulaValida(estudianteDTO.getCedulaRep())) {
            throw new IllegalArgumentException("La cédula ingresada no es valida.");
        }
        if (!verificador.esDireccionValida(estudianteDTO.getDireccion())) {
            throw new IllegalArgumentException("La dirección ingresada no es valida.");
        }

        Estudiante estudiante = transformarDto(estudianteDTO);

        estudianteRepositorio.save(estudiante);
    }

    @Override
    public void eliminarEstudiante(Integer id) {
        estudianteRepositorio.deleteById(id);
    }

    public Estudiante transformarDto(EstudianteDTO estudianteDTO) {

        Boolean estado = Objects.requireNonNullElse(estudianteDTO.getEstado(), true);

        return new Estudiante(
                estudianteDTO.getId(),
                estudianteDTO.getCedulaRep(),
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

}
