package co.ucoshop.pqrsprocesadorms.domain.faq;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "faq")
public class Faq {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "functionality")
    private Functionality functionality;

    @ManyToOne
    @JoinColumn(name = "faq_process")
    private FaqProcess process;

    @Column(name = "description")
    private String description;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "answer")
    private String answer;

    @Column(name = "admin_email")
    private String adminEmail;

    public Faq() {
    }

    public Faq(UUID id, Functionality functionality, FaqProcess process, String description, String userEmail, String answer, String adminEmail) {
        this.id = id;
        this.functionality = functionality;
        this.process = process;
        this.description = description;
        this.userEmail = userEmail;
        this.answer = answer;
        this.adminEmail = adminEmail;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Functionality getFunctionality() {
        return functionality;
    }

    public void setFunctionality(Functionality functionality) {
        this.functionality = functionality;
    }

    public FaqProcess getProcess() {
        return process;
    }

    public void setProcess(FaqProcess process) {
        this.process = process;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
}
