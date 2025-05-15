package co.ucoshop.pqrsprocesadorms.messaging.myquestion;


import co.ucoshop.pqrsprocesadorms.domain.myQuestion.MyQuestion;
import co.ucoshop.pqrsprocesadorms.services.myquestion.MyQuestionService;
import co.ucoshop.pqrsprocesadorms.util.gson.MapperJsonObjeto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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

    @RabbitListener(queues = "${procesadorpqrs.mispreguntas.crear-qn}")
    public void receiveMessageCreateMyQuestion(String message) {
        try {
            obtenerObjetoDeMensaje(message)
                    .ifPresent(myquestion -> myQuestionService.saveMyQuestion(myquestion));
            System.out.println(message);
        } catch (Exception e) {
            System.out.println(e);

        }
    }


    private Optional<MyQuestion> obtenerObjetoDeMensaje(String mensaje) {
        return mapperJsonObjeto.ejecutar(mensaje, MyQuestion.class);
    }
}
