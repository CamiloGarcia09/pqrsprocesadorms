package co.ucoshop.pqrsprocesadorms.messaging.pqrs;

import co.ucoshop.pqrsprocesadorms.domain.pqrs.Pqrs;
import co.ucoshop.pqrsprocesadorms.services.pqrs.PqrsService;
import co.ucoshop.pqrsprocesadorms.util.gson.MapperJsonObjeto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReceiverMessagesPqrs {

    private final PqrsService pqrsService;
    private final MapperJsonObjeto mapperJsonObjeto;

    public ReceiverMessagesPqrs(PqrsService pqrsService, MapperJsonObjeto mapperJsonObjeto) {
        this.pqrsService = pqrsService;
        this.mapperJsonObjeto = mapperJsonObjeto;
    }

    @RabbitListener(queues = "${mensaje.pqrs.crear.queue-name}")
    public void receiveMessageCreatePqrs(String message) {
        try {
            obtenerObjetoDeMensaje(message)
                    .ifPresent(pqrs -> pqrsService.savePqrs(pqrs));
            System.out.println(message);
        } catch (Exception e) {
            System.out.println(e);

        }
    }
    private Optional<Pqrs> obtenerObjetoDeMensaje(String mensaje) {
        return mapperJsonObjeto.ejecutar(mensaje, Pqrs.class);
    }
}
