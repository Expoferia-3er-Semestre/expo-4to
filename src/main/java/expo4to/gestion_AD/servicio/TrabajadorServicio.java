package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.modelo.Trabajador;
import expo4to.gestion_AD.repositorio.TrabajadorRepositorio;
import expo4to.gestion_AD.util.CifradorContrasenas;
import expo4to.gestion_AD.util.Verificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorServicio implements ITrabajadorServicio{

    @Autowired
    TrabajadorRepositorio trabajadorRepositorio;
    @Autowired
    CifradorContrasenas cifrador;
    @Autowired
    Verificador verificador;

    @Override
    public List<Trabajador> listarTrabajadores() {
        return trabajadorRepositorio.findAll();
    }

    @Override
    public Trabajador buscarTrabajadorPorId(Integer id) {
        return trabajadorRepositorio.findById(id).orElse(null);
    }

    @Override
    public void guardarTrabajador(Trabajador trabajador) {

        String passwordPlana = trabajador.getContrasena();
        if (passwordPlana != null) {
            String passwordHash = cifrador.cifrarContrasena(passwordPlana);

            trabajador.setContrasena(passwordHash);
        } else {
            throw new IllegalArgumentException("La contraseña no puede quedar vacía.");
        }

        if (!verificador.esNombreOApellidoValido(trabajador.getNombre1()) ||
                !verificador.esNombreOApellidoValido(trabajador.getNombre2())) {
            throw new IllegalArgumentException("El nombre ingresado no es valido.");
        }
        if (!verificador.esNombreOApellidoValido(trabajador.getApellido1()) ||
                !verificador.esNombreOApellidoValido(trabajador.getApellido2())) {
            throw new IllegalArgumentException("El apellido ingresado no es valido.");
        }
        if (!verificador.esCedulaValida(trabajador.getCedula())) {
            throw new IllegalArgumentException("La cédula ingresada no es valida.");
        }
        if (!verificador.esDireccionValida(trabajador.getDireccion())) {
            throw new IllegalArgumentException("La dirección ingresada no es valida.");
        }

        trabajadorRepositorio.save(trabajador);

    }

    @Override
    public void eliminarTrabajador(Trabajador trabajador) {
        trabajadorRepositorio.delete(trabajador);
    }
}
