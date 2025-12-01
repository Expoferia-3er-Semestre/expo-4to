package expo4to.gestion_AD;

import expo4to.gestion_AD.controlador.EstudianteControlador;
import expo4to.gestion_AD.vista.login;
import org.springframework.boot.CommandLineRunner; // Interfaz para ejecutar código al iniciar
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

@SpringBootApplication
public class GestionAdApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(GestionAdApplication.class)
                .headless(false) // IMPORTANTE: Permite que aparezcan ventanas
                .run(args);        // Inicia Spring y ejecuta el CommandLineRunner

        // 2. Obtenemos el Bean de la ventana del contexto
        EventQueue.invokeLater(() -> {
                    // Es buena práctica de Swing iniciar la UI en el Event Dispatch Thread
                    login frame = context.getBean(login.class);
                    frame.setVisible(true);
        SpringApplication.run(GestionAdApplication.class, args);
        });
    }
}
