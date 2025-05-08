package co.ucoshop.pqrsprocesadorms.domain.myQuestion;

import co.ucoshop.pqrsprocesadorms.domain.faq.FaqProcess;
import co.ucoshop.pqrsprocesadorms.domain.faq.Functionality;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "my_question")
public class MyQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
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

    @Column(name = "admin_email")
    private String adminEmail;

    @Column(name = "answer")
    private String answer;


    public MyQuestion(UUID id, FaqProcess process, Functionality functionality,
                      String description, String userEmail, String adminEmail, String answer) {
        this.id = id;
        this.process = process;
        this.functionality = functionality;
        this.description = description;
        this.userEmail = userEmail;
        this.adminEmail = adminEmail;
        this.answer = answer;
    }

    public MyQuestion() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
