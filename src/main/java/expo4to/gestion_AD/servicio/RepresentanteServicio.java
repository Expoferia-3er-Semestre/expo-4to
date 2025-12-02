package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.EstudianteDTO;
import expo4to.gestion_AD.dto.RepresentanteDTO;
import expo4to.gestion_AD.modelo.Estudiante;
import expo4to.gestion_AD.modelo.Representante;
import expo4to.gestion_AD.repositorio.RepresentanteRepositorio;
import expo4to.gestion_AD.util.Verificador;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RepresentanteServicio implements IRepresentanteServicio{

    @Autowired
    private RepresentanteRepositorio representanteRepositorio;

    @Autowired
    private EstudianteServicio estudianteServicio;

    @Autowired
    private Verificador verificador;

    @Override
    public List<RepresentanteDTO> listarRepresentantes() {
        List<Representante> lista = representanteRepositorio.findAll();
        List<RepresentanteDTO> dtos = new ArrayList<>();

        for (Representante representante : lista) {
            dtos.add(transformarRepresentante(representante));
        }

        return dtos;
    }

    @Transactional
    @Override
    public RepresentanteDTO buscarRepresentantePorCedula(String cedula) {

        Optional<Representante> optional = representanteRepositorio.findByCedula(cedula);

        if (optional.isEmpty()) {
            throw new NoSuchElementException();
        }
        Representante representante = optional.get();
        representante.getEstudiantes().size();

        return transformarRepresentante(representante);

    }

    @Override
    public void guardarRepresentante(RepresentanteDTO representanteDTO) {

        String cedulaABuscar = representanteDTO.getCedula();

        Optional<Representante> repreEncontrado = representanteRepositorio.findByCedula(cedulaABuscar);

        if (repreEncontrado.isPresent()) {
            throw new IllegalArgumentException("Error: No se puede guardar el representante. " +
                    "La cédula de representante (" + cedulaABuscar + ") ya existe en el sistema.");
        }

        if (!this.verificador.esNombreOApellidoValido(representanteDTO.getNombre1()) ||
                !this.verificador.esNombreOApellidoValido(representanteDTO.getNombre2())) {
            throw new IllegalArgumentException("El nombre ingresado no es valido.");
        }
        if (!this.verificador.esNombreOApellidoValido(representanteDTO.getApellido1()) ||
                !this.verificador.esNombreOApellidoValido(representanteDTO.getApellido2())) {
            throw new IllegalArgumentException("El apellido ingresado no es valido.");
        }
        if (!this.verificador.esCedulaValida(representanteDTO.getCedula())) {
            throw new IllegalArgumentException("La cédula ingresada no es valida.");
        }
        if (!this.verificador.esDireccionValida(representanteDTO.getDireccion())) {
            throw new IllegalArgumentException("La dirección ingresada no es valida.");
        }
        if (!this.verificador.esTelefonoValido(representanteDTO.getTelefono())) {
            throw new IllegalArgumentException("El teléfono ingresado no es valido.");
        }

        Representante representante = transformarDTO(representanteDTO);

        representanteRepositorio.save(representante);
    }

    @Override
    public void eliminarRepresentante(Integer id) {
        representanteRepositorio.deleteById(id);
    }

    public Representante transformarDTO(RepresentanteDTO representanteDTO) {

        Boolean estado = Objects.requireNonNullElse(representanteDTO.getEstado(), true);

        return new Representante(
                representanteDTO.getCedula(),
                representanteDTO.getNombre1(),
                representanteDTO.getNombre2(),
                representanteDTO.getApellido1(),
                representanteDTO.getApellido2(),
                representanteDTO.getTelefono(),
                representanteDTO.getFechaN(),
                representanteDTO.getDireccion(),
                estado,
                null
        );

    }

    public RepresentanteDTO transformarRepresentante(Representante representante) {

        RepresentanteDTO representanteDTO = new RepresentanteDTO();

        representanteDTO.setCedula(representante.getCedula());
        representanteDTO.setEstado(representante.getEstado());
        representanteDTO.setNombre1(representante.getNombre1());
        representanteDTO.setNombre2(representante.getNombre2());
        representanteDTO.setApellido1(representante.getApellido1());
        representanteDTO.setApellido2(representante.getApellido2());
        representanteDTO.setDireccion(representante.getDireccion());
        representanteDTO.setFechaN(representante.getFechaN());
        representanteDTO.setTelefono(representante.getTelefono());

        for (Estudiante estudiante : representante.getEstudiantes()) {
            EstudianteDTO dto = estudianteServicio.transformarEstudiante(estudiante);
            representanteDTO.addEstudiante(dto);
            System.out.println("añadido");
        }

        return representanteDTO;

    }
}
