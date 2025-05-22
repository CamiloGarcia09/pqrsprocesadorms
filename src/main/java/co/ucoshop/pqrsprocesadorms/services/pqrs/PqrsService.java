package co.ucoshop.pqrsprocesadorms.services.pqrs;


import co.ucoshop.pqrsprocesadorms.domain.pqrs.Note;
import co.ucoshop.pqrsprocesadorms.domain.pqrs.Pqrs;
import co.ucoshop.pqrsprocesadorms.repositories.pqrs.IPqrsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PqrsService {

    private final IPqrsRepository ipqrsRepository;

    public PqrsService(IPqrsRepository ipqrsRepository) {
        this.ipqrsRepository = ipqrsRepository;
    }

    public List<Pqrs> findAll() {
        return ipqrsRepository.findAll();
    }

    public Pqrs savePqrs(Pqrs pqrs) {
        if (pqrs.getId() == null) {
            pqrs.setId(UUID.randomUUID());
            pqrs.setCreatedAt(LocalDateTime.now());
        }
        return ipqrsRepository.save(pqrs);
    }

    public void deletePqrs(UUID idpqrs) {
        ipqrsRepository.deleteById(idpqrs);
    }

    public Optional<Pqrs> findById(UUID idpqrs) {
        return ipqrsRepository.findById(idpqrs);
    }

    public Page<Pqrs> findByUserEmail(String email , Pageable pageable) {
        return ipqrsRepository.findAllByUserEmail(email, pageable);
    }

    public Page<Pqrs> getPqrsPaginated(Pageable pageable) {
        return ipqrsRepository.findAll(pageable);
    }

    public Pqrs addNoteToPqrs(Pqrs pqrs, String mensaje) {
        Note note = new Note();
        note.setId(UUID.randomUUID());
        note.setNota(mensaje);
        note.setPqrs(pqrs);
        note.setCreatedAt(LocalDateTime.now());
        pqrs.getNotes().add(note);
        return savePqrs(pqrs);
    }
}