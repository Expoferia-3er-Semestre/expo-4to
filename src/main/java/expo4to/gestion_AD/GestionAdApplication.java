package expo4to.gestion_AD;

import expo4to.gestion_AD.controlador.EstudianteControlador;
import expo4to.gestion_AD.modelo.Estudiante; // Necesitas la entidad
import expo4to.gestion_AD.modelo.Representante;
import expo4to.gestion_AD.servicio.EstudianteServicio;
import expo4to.gestion_AD.servicio.RepresentanteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner; // Interfaz para ejecutar código al iniciar
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionAdApplication {

    public static void main(String[] args) {
        // Inicia Spring y ejecuta el CommandLineRunner
        SpringApplication.run(GestionAdApplication.class, args);
    }

    // Define un Bean que implementa CommandLineRunner
    // Spring ejecutará el método 'run' después de inicializar el contexto.
    @Bean
    public CommandLineRunner runControllerTest(EstudianteControlador controlador) {

        return args -> {
            System.out.println("--- PRUEBAS DEL CONTROLADOR SIN UI SWING ---");



        };
    }
}
