package co.ucoshop.pqrsprocesadorms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "procesadorpqrs.mispreguntas")
public class MyQuestionsConfig {

    private String crearQn;
    private String crearEx;
    private String crearRk;

    private String actualizarQn;
    private String actualizarEx;
    private String actualizarRk;

    private String corregirQn;
    private String corregirEx;
    private String corregirRk;

    private String borrarQn;
    private String borrarEx;
    private String borrarRk;
}
