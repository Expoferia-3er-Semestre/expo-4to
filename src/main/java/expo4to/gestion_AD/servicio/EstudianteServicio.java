package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.Estudiante;
import expo4to.gestion_AD.repositorio.EstudianteRepositorio;
import expo4to.gestion_AD.repositorio.RepresentanteRepositorio;
import expo4to.gestion_AD.util.Verificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void guardarEstudiante(Estudiante estudiante) {

        String cedulaRep = estudiante.getCedula_rep();

        expo4to.gestion_AD.modelo.Representante representante = representanteRepositorio.
                findByCedula(cedulaRep).orElse(null);

        if (representante == null) {
            // Se usa IllegalArgumentException para que el Controlador pueda informar a la UI.
            throw new IllegalArgumentException("Error: No se puede guardar el estudiante. " +
                    "La cédula de representante (" + cedulaRep + ") no existe en la base de datos.");
        }

        if (!verificador.esNombreOApellidoValido(estudiante.getNombre1()) ||
                !verificador.esNombreOApellidoValido(estudiante.getNombre2())) {
            throw new IllegalArgumentException("El nombre ingresado no es valido.");
        }
        if (!verificador.esNombreOApellidoValido(estudiante.getApellido1()) ||
                !verificador.esNombreOApellidoValido(estudiante.getApellido2())) {
            throw new IllegalArgumentException("El apellido ingresado no es valido.");
        }
        if (!verificador.esCedulaValida(estudiante.getCedula_rep())) {
            throw new IllegalArgumentException("La cédula ingresada no es valida.");
        }
        if (!verificador.esDireccionValida(estudiante.getDireccion())) {
            throw new IllegalArgumentException("La dirección ingresada no es valida.");
        }

        estudianteRepositorio.save(estudiante);
    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        estudianteRepositorio.delete(estudiante);
    }
}
