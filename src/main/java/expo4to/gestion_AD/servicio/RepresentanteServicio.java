package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.Representante;
import expo4to.gestion_AD.repositorio.RepresentanteRepositorio;
import expo4to.gestion_AD.util.Verificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepresentanteServicio implements IRepresentanteServicio{

    @Autowired
    private RepresentanteRepositorio representanteRepositorio;

    @Autowired
    private Verificador verificador;

    @Override
    public List<Representante> listarRepresentantes() {
        return representanteRepositorio.findAll();
    }

    @Override
    public Representante buscarRepresentantePorId(Integer id) {
        return representanteRepositorio.findById(id).orElseThrow(
                () -> new java.util.NoSuchElementException("Representante con ID "+ id + "no encontrado.")
        );
    }

    @Override
    public Representante buscarRepresentantePorCedula(String cedula) {
        return representanteRepositorio.findByCedula(cedula).orElseThrow(
                () -> new java.util.NoSuchElementException("Representante con cedula " + cedula + " no encontrado.")
        );
    }

    @Override
    public void guardarRepresentante(Representante representante) {

        String cedulaABuscar = representante.getCedula();
        Representante repreEncontrado = representanteRepositorio.findByCedula(cedulaABuscar).orElse(null);
        if (repreEncontrado != null) {
            throw new IllegalArgumentException("Error: No se puede guardar el representante. " +
                    "La cédula de representante (" + cedulaABuscar + ") ya existe en el sistema.");
        }

        if (!verificador.esNombreOApellidoValido(representante.getNombre1()) ||
                !verificador.esNombreOApellidoValido(representante.getNombre2())) {
            throw new IllegalArgumentException("El nombre ingresado no es valido.");
        }
        if (!verificador.esNombreOApellidoValido(representante.getApellido1()) ||
                !verificador.esNombreOApellidoValido(representante.getApellido2())) {
            throw new IllegalArgumentException("El apellido ingresado no es valido.");
        }
        if (!verificador.esCedulaValida(representante.getCedula())) {
            throw new IllegalArgumentException("La cédula ingresada no es valida.");
        }
        if (!verificador.esDireccionValida(representante.getDireccion())) {
            throw new IllegalArgumentException("La dirección ingresada no es valida.");
        }
        if (!verificador.esTelefonoValido(representante.getTelefono())) {
            throw new IllegalArgumentException("El teléfono ingresado no es valido.");
        }

        representanteRepositorio.save(representante);
    }

    @Override
    public void eliminarRepresentante(Representante representante) {
        representanteRepositorio.delete(representante);
    }
}
