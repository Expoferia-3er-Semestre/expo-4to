package expo4to.gestion_AD.repositorio;

import expo4to.gestion_AD.modelo.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RepresentanteRepositorio extends JpaRepository<Representante, Integer> {

    // Esto genera un SELECT que une (JOIN) las tablas de Representante y Estudiante
    @Query("SELECT r FROM Representante r JOIN FETCH r.estudiantes WHERE r.cedula = :cedula")
    Optional<Representante> findByCedulaWithEstudiantes(@Param("cedula") String cedula);

    Optional<Representante> findByCedula(String cedula);
}
