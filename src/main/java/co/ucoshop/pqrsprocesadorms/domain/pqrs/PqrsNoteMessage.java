package co.ucoshop.pqrsprocesadorms.domain.pqrs;

import java.util.UUID;

public class PqrsNoteMessage {

    private UUID idPqrs;
    private String mensaje;

    public PqrsNoteMessage() {
    }

    public PqrsNoteMessage(UUID idPqrs, String mensaje) {
        this.idPqrs = idPqrs;
        this.mensaje = mensaje;
    }

    public UUID getIdPqrs() {
        return idPqrs;
    }

    public void setIdPqrs(UUID idPqrs) {
        this.idPqrs = idPqrs;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
