package co.ucoshop.pqrsprocesadorms.messaging.myquestion;


import co.ucoshop.pqrsprocesadorms.domain.myQuestion.MyQuestion;
import co.ucoshop.pqrsprocesadorms.services.myquestion.MyQuestionService;
import co.ucoshop.pqrsprocesadorms.util.gson.MapperJsonObjeto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class ReceiverMessagesMyQuestion {

    private final MyQuestionService myQuestionService;
    private final MapperJsonObjeto mapperJsonObjeto;

    public ReceiverMessagesMyQuestion(MyQuestionService myQuestionService, MapperJsonObjeto mapperJsonObjeto) {
        this.myQuestionService = myQuestionService;
        this.mapperJsonObjeto = mapperJsonObjeto;
    }

    @RabbitListener(queues = "${procesadorpqrs.mispreguntas.actualizar-qn}")
    public void receiveMessageUpdateMyQuestion(String message) {
        try {
            Optional<MyQuestion> question = obtenerObjetoDeMensaje(message);
            if (question.isPresent()) {
                UUID id = question.get().getId();
                myQuestionService.updateMyQuestion(id, question.get());
            }
            System.out.println(message);
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    @RabbitListener(queues = "${procesadorpqrs.mispreguntas.borrar-qn}")
    public void receiveMessageDeleteMyQuestion(String message) {
        try {
            Optional<UUID> id = obtenerUUIDDeMensaje(message);
            id.ifPresent(myQuestionService::deleteMyQuestion);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @RabbitListener(queues = "${procesadorpqrs.mispreguntas.corregir-qn}")
    public void receiveMessagePatchMyQuestion(String message) {
        try {
            Optional<Map<String, Object>> parametros = obtenerParametrosDeMensaje(message);
            if (parametros.isPresent()) {
                UUID id = UUID.fromString(parametros.get().get("id").toString());
                parametros.get().remove("id");
                myQuestionService.updateMyQuestionByParams(id, parametros.get());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Optional<Map<String, Object>> obtenerParametrosDeMensaje(String message) {
        return mapperJsonObjeto.ejecutar(message, (Class<Map<String, Object>>) (Class<?>) Map.class);
    }

    private Optional<UUID> obtenerUUIDDeMensaje(String message) {
        return mapperJsonObjeto.ejecutar(message, UUID.class);
    }

    private Optional<MyQuestion> obtenerObjetoDeMensaje(String mensaje) {
        return mapperJsonObjeto.ejecutar(mensaje, MyQuestion.class);
    }
}
