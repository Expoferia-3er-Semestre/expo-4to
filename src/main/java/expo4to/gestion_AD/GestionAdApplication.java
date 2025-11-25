package expo4to.gestion_AD;

import expo4to.gestion_AD.controlador.EstudianteControlador;
import expo4to.gestion_AD.modelo.Estudiante; // Necesitas la entidad
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

            // --- PRUEBA 1: Éxito ---
            Estudiante e1 = new Estudiante();
            e1.setNombre1("Ana");
            e1.setNombre2("Llana");
            e1.setApellido1("Bermudez");
            e1.setApellido2("Cáceres");
            e1.setDireccion("La Coromoto, calle 4, Casa 45-B");
            e1.setCedula_rep("10000000");
//
//
            System.out.println("\nIntentando guardar Estudiante VÁLIDO...");
            controlador.guardarEstudiante(e1);
//
//            // --- PRUEBA 2: Error de Validación ---
//            // Asume que tu servicio lanza IllegalArgumentException si la cédula es mala
//            Estudiante e2 = new Estudiante();
//            e2.setNombre1("Beto");
//            e2.setNombre2("Andrés");
//            e2.setApellido1("Colina");
//            e2.setApellido2("Feliz");
//            e2.setCedula_rep("12345"); // Cédula inválida (muy corta)
//            e2.setDireccion("Via Perija, Frente al Cementerio La Chinita, Villa Chinita");
//
//
//            System.out.println("\nIntentando guardar Estudiante INVÁLIDO...");
//            controlador.guardarEstudiante(e2); // Esto llamará al JOptionPane con el error

            System.out.println(controlador.buscarEstudiante(1));

            // Si no quieres que la aplicación se quede abierta, puedes cerrarla
            // System.exit(0);
        };
    }
}