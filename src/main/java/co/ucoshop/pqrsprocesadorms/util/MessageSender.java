package co.ucoshop.pqrsprocesadorms.util;

public interface MessageSender<T> {
void execute(T message,Long idMessage);
}
