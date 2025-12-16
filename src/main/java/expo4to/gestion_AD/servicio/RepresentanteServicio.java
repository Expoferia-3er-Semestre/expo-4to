package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.EstudianteDTO;
import expo4to.gestion_AD.dto.RepresentanteDTO;
import expo4to.gestion_AD.mapper.EstudianteMapper;
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
            dtos.add(EstudianteMapper.toRepresentanteDTO(representante));
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
        RepresentanteDTO dto = EstudianteMapper.toRepresentanteDTO(representante);

        if (representante.getEstudiantes().isEmpty()) {
            throw new NoSuchElementException("Este representante no tiene estudiantes inscritos");
        }

        for (Estudiante estudiante : representante.getEstudiantes()) {
            dto.addEstudiante(EstudianteMapper.toEstudianteDTO(estudiante));
        }

        return dto;

    }

    @Override
    public void guardarRepresentante(RepresentanteDTO representanteDTO) {

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

        Representante representante = EstudianteMapper.toRepresentanteEntidad(representanteDTO);

        // Si tiene estudiantes dentro, entonces es un nuevo representante con su estudiante
        if (!representanteDTO.getEstudiantes().isEmpty()) {
            EstudianteDTO estudianteDTO = representanteDTO.getEstudiantes().getLast();

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
            Estudiante estudiante = EstudianteMapper.toEstudianteEntidad(estudianteDTO);
            representante.addEstudiante(estudiante);
        }


        try {
            representanteRepositorio.save(representante);
        } catch (Exception e) {
            throw new RuntimeException("Ocurrió un error inesperado:" + e.getMessage());
        }

    }

    @Override
    public void eliminarRepresentante(Integer id) {
        representanteRepositorio.deleteById(id);
    }

}
