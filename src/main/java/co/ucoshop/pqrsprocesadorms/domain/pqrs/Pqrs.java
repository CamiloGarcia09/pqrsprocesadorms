package co.ucoshop.pqrsprocesadorms.domain.pqrs;


import co.ucoshop.pqrsprocesadorms.domain.faq.FaqProcess;
import co.ucoshop.pqrsprocesadorms.domain.faq.Functionality;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "pqrs")
@JsonIgnoreProperties(ignoreUnknown = true)
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

    @OneToMany(mappedBy = "pqrs", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Note> notes = new ArrayList<>();

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



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TypePqrs getTypePqrs() {
        return typePqrs;
    }

    public void setTypePqrs(TypePqrs typePqrs) {
        this.typePqrs = typePqrs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FaqProcess getProcess() {
        return process;
    }

    public void setProcess(FaqProcess process) {
        this.process = process;
    }

    public Functionality getFunctionality() {
        return functionality;
    }

    public void setFunctionality(Functionality functionality) {
        this.functionality = functionality;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}