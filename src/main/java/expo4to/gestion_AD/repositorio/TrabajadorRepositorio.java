package expo4to.gestion_AD.repositorio;

import expo4to.gestion_AD.modelo.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrabajadorRepositorio extends JpaRepository<Trabajador, Integer> {
    public Optional<Trabajador> findByCorreo(String correo);
}
