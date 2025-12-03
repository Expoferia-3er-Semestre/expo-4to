package expo4to.gestion_AD.repositorio;

import expo4to.gestion_AD.modelo.AnosEscolares;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

import java.util.Optional;

public interface AnosEscolaresRepositorio extends JpaRepository<AnosEscolares, Integer> {

    @Query("SELECT a FROM AnosEscolares a WHERE :fechaActual BETWEEN a.periodoInicio AND a.periodoFin AND a.estado = true")
    Optional<AnosEscolares> findActivoVigente(@Param("fechaActual") Date fechaActual);

}
