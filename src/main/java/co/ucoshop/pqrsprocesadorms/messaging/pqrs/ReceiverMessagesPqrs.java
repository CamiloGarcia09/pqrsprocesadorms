package co.ucoshop.pqrsprocesadorms.messaging.pqrs;

import co.ucoshop.pqrsprocesadorms.domain.pqrs.Pqrs;
import co.ucoshop.pqrsprocesadorms.domain.pqrs.PqrsNoteMessage;
import co.ucoshop.pqrsprocesadorms.services.pqrs.PqrsService;
import co.ucoshop.pqrsprocesadorms.util.gson.MapperJsonObjeto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ReceiverMessagesPqrs {

    private final PqrsService pqrsService;
    private final MapperJsonObjeto mapperJsonObjeto;

    public ReceiverMessagesPqrs(PqrsService pqrsService, MapperJsonObjeto mapperJsonObjeto) {
        this.pqrsService = pqrsService;
        this.mapperJsonObjeto = mapperJsonObjeto;
    }

    @RabbitListener(queues = "${procesadorpqrs.pqrs.crear-qn}")
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

    @RabbitListener(queues = "${procesadorpqrs.pqrs.delete-qn}")
    public void receiveMessageDeletePqrs(String message) {
        try {
            UUID idPqrs = UUID.fromString(message.trim());
            pqrsService.deletePqrs(idPqrs);
            System.out.println(message);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @RabbitListener(queues = "${procesadorpqrs.pqrs.agregarmensaje-qn}")
    public void receiveMessageAddMessagePqrs(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            PqrsNoteMessage dto = objectMapper.readValue(message, PqrsNoteMessage.class);

            Optional<Pqrs> optionalPqrs = pqrsService.findById(dto.getIdPqrs());

            if (optionalPqrs.isPresent()) {
                Pqrs pqrs = optionalPqrs.get();
                pqrsService.addNoteToPqrs(pqrs, dto.getMensaje());
            } else {
                System.err.println("PQRS no encontrada con id: " + dto.getIdPqrs());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String extraerTextoMensaje(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(json);
            return node.get("mensaje").asText();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}