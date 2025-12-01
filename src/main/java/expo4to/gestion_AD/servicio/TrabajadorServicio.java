package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.TrabajadorDTO;
import expo4to.gestion_AD.modelo.Trabajador;
import expo4to.gestion_AD.repositorio.TrabajadorRepositorio;
import expo4to.gestion_AD.util.CifradorContrasenas;
import expo4to.gestion_AD.util.Verificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TrabajadorServicio implements ITrabajadorServicio{

    @Autowired
    TrabajadorRepositorio trabajadorRepositorio;
    @Autowired
    CifradorContrasenas cifrador;
    @Autowired
    Verificador verificador;

    @Override
    public List<TrabajadorDTO> listarTrabajadores() {

        List<TrabajadorDTO> trabajadoresDTO = new ArrayList<>();
        List<Trabajador> trabajadores = trabajadorRepositorio.findAll();
        for (Trabajador trabajador : trabajadores) {
            TrabajadorDTO trabajadorDTO = transformarTrabajador(trabajador);
            trabajadoresDTO.add(trabajadorDTO);
        }
        return trabajadoresDTO;
    }

    @Override
    public TrabajadorDTO buscarTrabajadorPorId(Integer id) {
        return transformarTrabajador(trabajadorRepositorio.findById(id).orElseThrow(null));
    }

    @Override
    public TrabajadorDTO buscarTrabajadorPorCorreo(String correo) {
        return transformarTrabajador(trabajadorRepositorio.findByCorreo(correo).orElseThrow(null));
    }

    @Override
    public void guardarTrabajador(TrabajadorDTO trabajadorDTO) {

        String passwordPlana = trabajadorDTO.getContrasena();
        if (passwordPlana != null) {
            String passwordHash = cifrador.cifrarContrasena(passwordPlana);

            trabajadorDTO.setContrasena(passwordHash);
        } else {
            throw new IllegalArgumentException("La contraseña no puede quedar vacía.");
        }

        if (!verificador.esNombreOApellidoValido(trabajadorDTO.getNombre1()) ||
                !verificador.esNombreOApellidoValido(trabajadorDTO.getNombre2())) {
            throw new IllegalArgumentException("El nombre ingresado no es valido.");
        }
        if (!verificador.esNombreOApellidoValido(trabajadorDTO.getApellido1()) ||
                !verificador.esNombreOApellidoValido(trabajadorDTO.getApellido2())) {
            throw new IllegalArgumentException("El apellido ingresado no es valido.");
        }
        if (!verificador.esCedulaValida(trabajadorDTO.getCedula())) {
            throw new IllegalArgumentException("La cédula ingresada no es valida.");
        }
        if (!verificador.esDireccionValida(trabajadorDTO.getDireccion())) {
            throw new IllegalArgumentException("La dirección ingresada no es valida.");
        }

        Trabajador trabajador = transformarDTO(trabajadorDTO);

        trabajadorRepositorio.save(trabajador);

    }

    @Override
    public void eliminarTrabajador(Integer id) {
        trabajadorRepositorio.deleteById(id);
    }

    public Trabajador transformarDTO(TrabajadorDTO trabajadorDTO) {

        Boolean estado = Objects.requireNonNullElse(trabajadorDTO.getEstado(), true);

        return new Trabajador(
                trabajadorDTO.getId(),
                trabajadorDTO.getCedula(),
                trabajadorDTO.getNombre1(),
                trabajadorDTO.getNombre2(),
                trabajadorDTO.getApellido1(),
                trabajadorDTO.getApellido2(),
                trabajadorDTO.getTelefono(),
                trabajadorDTO.getCorreo(),
                trabajadorDTO.getFechaN(),
                trabajadorDTO.getDireccion(),
                estado,
                trabajadorDTO.getContrasena(),
                trabajadorDTO.getRol()
        );


    }

    public TrabajadorDTO transformarTrabajador(Trabajador trabajador) {

        if (trabajador == null) {
            return null;
        }

        TrabajadorDTO trabajadorDTO = new TrabajadorDTO();

        trabajadorDTO.setContrasena(trabajador.getContrasena());
        trabajadorDTO.setId(trabajador.getId());
        trabajadorDTO.setCorreo(trabajador.getCorreo());
        trabajadorDTO.setCedula(trabajador.getCedula());
        trabajadorDTO.setEstado(trabajador.getEstado());
        trabajadorDTO.setNombre1(trabajador.getNombre1());
        trabajadorDTO.setNombre2(trabajador.getNombre2());
        trabajadorDTO.setApellido1(trabajador.getApellido1());
        trabajadorDTO.setApellido2(trabajador.getApellido2());
        trabajadorDTO.setDireccion(trabajador.getDireccion());
        trabajadorDTO.setFechaN(trabajador.getFechaN());
        trabajadorDTO.setTelefono(trabajador.getTelefono());
        trabajadorDTO.setRol(trabajador.getRol());

        return trabajadorDTO;

    }

}
