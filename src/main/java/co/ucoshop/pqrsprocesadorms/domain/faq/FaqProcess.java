package co.ucoshop.pqrsprocesadorms.domain.faq;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "faq_process")
public class FaqProcess {

    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    public FaqProcess() {
    }

    public FaqProcess(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
