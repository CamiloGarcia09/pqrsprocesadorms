package co.ucoshop.pqrsprocesadorms.messaging.myquestion;


import co.ucoshop.pqrsprocesadorms.domain.myQuestion.MyQuestion;
import co.ucoshop.pqrsprocesadorms.services.myquestion.MyQuestionService;
import co.ucoshop.pqrsprocesadorms.util.gson.MapperJsonObjeto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReceiverMessagesMyQuestion {

    private final MyQuestionService myQuestionService;
    private final MapperJsonObjeto mapperJsonObjeto;

    public ReceiverMessagesMyQuestion(MyQuestionService myQuestionService, MapperJsonObjeto mapperJsonObjeto) {
        this.myQuestionService = myQuestionService;
        this.mapperJsonObjeto = mapperJsonObjeto;
    }

    @RabbitListener(queues = "${mensaje.myquestion.crear.queue-name}")
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
