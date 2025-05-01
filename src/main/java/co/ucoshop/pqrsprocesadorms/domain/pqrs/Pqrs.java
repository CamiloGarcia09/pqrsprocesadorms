package co.ucoshop.pqrsprocesadorms.domain.pqrs;

import co.ucoshop.ucoshopapi.domain.faq.FaqProcess;
import co.ucoshop.ucoshopapi.domain.faq.Functionality;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "pqrs")
public class Pqrs {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "type_pqrs")
    private TypePqrs typePqrs;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "faq_process")
    private FaqProcess process;

    @ManyToOne
    @JoinColumn(name = "functionality")
    private Functionality functionality;

    @OneToMany(mappedBy = "pqrs", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Note> notes; // Relación OneToMany con Note

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    private LocalDateTime createdAt;

    @Column(name = "user_email")
    private String userEmail;

    public Pqrs() {
    }

    public Pqrs(UUID id, TypePqrs typePqrs,
                String description, List<Note> notes,
                State state, LocalDateTime createdAt,
                FaqProcess process, Functionality functionality, String userEmail) {
        setId(id);
        setTypePqrs(typePqrs);
        setDescription(description);
        setProcess(process);
        setFunctionality(functionality);
        setNotes(notes);
        setState(state);
        setCreatedAt(createdAt);
        setUserEmail(userEmail);
    }
}