package co.ucoshop.pqrsprocesadorms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "procesadorpqrs.pqrs")
public class PqrsConfig {

    private String crearQn;
    private String crearEx;
    private String crearRk;

    private String deleteQn;
    private String deleteEx;
    private String deleteRk;


    private String addmessageQn;
    private String addmessageEx;
    private String addmessageRk;
}
