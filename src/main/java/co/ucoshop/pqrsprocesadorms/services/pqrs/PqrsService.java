package co.ucoshop.pqrsprocesadorms.services.pqrs;



import co.ucoshop.pqrsprocesadorms.domain.pqrs.Note;
import co.ucoshop.pqrsprocesadorms.domain.pqrs.Pqrs;
import co.ucoshop.pqrsprocesadorms.repositories.pqrs.IPqrsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PqrsService {

    private IPqrsRepository IpqrsRepository;

    public PqrsService(IPqrsRepository IpqrsRepository) {
        this.IpqrsRepository = IpqrsRepository;
    }

    public List<Pqrs> findAll() {
        return IpqrsRepository.findAll();
    }

    public Pqrs savePqrs(Pqrs pqrs) {
        if (pqrs.getId() == null) {
            pqrs.setId(UUID.randomUUID());
            pqrs.setCreatedAt(LocalDateTime.now());
        }
        return IpqrsRepository.save(pqrs);
    }

    public void deletePqrs(UUID idpqrs) {
        IpqrsRepository.deleteById(idpqrs);
    }

    public Pqrs findById(UUID idpqrs) {
        return IpqrsRepository.findById(idpqrs).orElse(null);
    }

    public Page<Pqrs> findByUserEmail(String email ,  Pageable pageable) {
        return IpqrsRepository.findAllByUserEmail(email, pageable);
    }

    public ResponseEntity<Map<String, Object>> getCheckStatus(UUID idpqrs) {
        Pqrs pqrs = findById(idpqrs);
        if (pqrs == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Map<String, Object> response = new HashMap<>();
        response.put("idpqrs", pqrs.getId().toString());
        response.put("idusuario", pqrs.getUserEmail());
        response.put("asunto", pqrs.getDescription());
        response.put("estado", pqrs.getState().getName());
        response.put("fechaCreacion", pqrs.getCreatedAt());

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, String>> addMessage(UUID idpqrs, String mensaje) {
        Pqrs pqrs = findById(idpqrs);
        if (pqrs == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Note note = new Note();
        note.setId(UUID.randomUUID());
        note.setNota(mensaje);
        note.setPqrs(pqrs);
        note.setCreatedAt(LocalDateTime.now());

        pqrs.getNotes().add(note);
        savePqrs(pqrs);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "Mensaje enviado correctamente");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Map<String, Object>> getMessages(UUID idpqrs) {
        Pqrs pqrs = findById(idpqrs);
        if (pqrs == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("idpqrs", pqrs.getId().toString());

        List<Map<String, String>> mensajes = new ArrayList<>();
        for (Note note : pqrs.getNotes()) {
            mensajes.add(Map.of(
                    "remitente", note.getPqrs().getUserEmail(),
                    "mensaje", note.getNota(),
                    "fecha", note.getCreatedAt().toString()
            ));
        }
        response.put("mensajes", mensajes);

        return ResponseEntity.ok(response);
    }


    public Page<Pqrs> getPqrsPaginated(Pageable pageable) {
        return IpqrsRepository.findAll(pageable);
    }
}