package expo4to.gestion_AD.controlador;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    // Guarda la referencia estática al contexto
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // Este método es llamado automáticamente por Spring
        context = applicationContext;
    }

    /**
     * Permite obtener cualquier bean de Spring por su tipo de clase.
     * @param beanClass La clase del bean que se desea obtener (ej. TrabajadorControlador.class)
     * @return Una instancia del bean.
     */
    public static <T> T getBean(Class<T> beanClass) {
        if (context == null) {
            throw new IllegalStateException("El contexto de Spring no ha sido inicializado.");
        }
        // Devuelve el componente solicitado
        return context.getBean(beanClass);
    }
}