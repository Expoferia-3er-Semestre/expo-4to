package expo4to.gestion_AD.servicio;

import expo4to.gestion_AD.dto.AnosEscolaresDTO;
import expo4to.gestion_AD.modelo.AnosEscolares;
import expo4to.gestion_AD.repositorio.AnosEscolaresRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AnoEscolarServicio implements IAnosEscolaresServicio{

    @Autowired
    AnosEscolaresRepositorio anosRepositorio;

    @Override
    public AnosEscolares buscarAnoActivo() {

        java.sql.Date fechaHoy = new java.sql.Date(System.currentTimeMillis());

        Optional<AnosEscolares> optional = anosRepositorio.findActivoVigente(fechaHoy);

        if (optional.isEmpty()) {
            throw new NoSuchElementException();
        }

        return optional.get();
    }

    @Override
    public void guardarAno(AnosEscolaresDTO anoEscolar) {
        anosRepositorio.save(transformarDTO(anoEscolar));
    }

    public AnosEscolaresDTO transformarAno(AnosEscolares ano) {
        AnosEscolaresDTO dto = new AnosEscolaresDTO();

                dto.setId(ano.getId());
                dto.setPeriodoInicio(ano.getPeriodoInicio());
                dto.setPeriodoFin(ano.getPeriodoFin());
                dto.setEstado(ano.getEstado());

        return dto;

    }

    public AnosEscolares transformarDTO(AnosEscolaresDTO ano) {
        return new AnosEscolares(
                ano.getId(),
                ano.getPeriodoInicio(),
                ano.getPeriodoFin(),
                ano.getEstado()
        );

    }

}
