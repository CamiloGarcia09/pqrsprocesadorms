package co.ucoshop.pqrsprocesadorms.domain.pqrs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "note")
public class Note {

    @Id
    private UUID id;

    @Column(name = "name")
    private String nota;

    @ManyToOne
    @JoinColumn(name = "pqrs_id")
    @JsonIgnore // Evita que se serialice la relación con Pqrs
    private Pqrs pqrs;

    private LocalDateTime createdAt;

    public Note() {
    }

    public Note(UUID id, String nota, Pqrs pqrs, LocalDateTime createdAt) {
        setId(id);
        setNota(nota);
        setPqrs(pqrs);
        setCreatedAt(createdAt);
    }
}