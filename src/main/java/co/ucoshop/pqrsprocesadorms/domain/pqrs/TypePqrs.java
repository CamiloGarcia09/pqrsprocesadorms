package co.ucoshop.pqrsprocesadorms.domain.pqrs;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "type_pqrs")
public class TypePqrs {

    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    public TypePqrs() {
    }

    public TypePqrs(UUID id, String name) {
        setId(id);
        setName(name);
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
