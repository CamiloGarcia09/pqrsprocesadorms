package co.ucoshop.pqrsprocesadorms.services.myquestion;

import co.ucoshop.pqrsprocesadorms.domain.faq.FaqProcess;
import co.ucoshop.pqrsprocesadorms.domain.faq.Functionality;
import co.ucoshop.pqrsprocesadorms.domain.myQuestion.MyQuestion;
import co.ucoshop.pqrsprocesadorms.repositories.faq.IFunctionalityRepository;
import co.ucoshop.pqrsprocesadorms.repositories.faq.IProcessRepository;
import co.ucoshop.pqrsprocesadorms.repositories.myQuestion.IMyQuestionRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class MyQuestionService {

    private final IMyQuestionRepository myQuestionRepository;
    private final IProcessRepository processRepository;
    private final IFunctionalityRepository functionalityRepository;
    private final ObjectMapper objectMapper;

    public MyQuestionService(IMyQuestionRepository myQuestionRepository,
                             IProcessRepository processRepository,
                             IFunctionalityRepository functionalityRepository, ObjectMapper objectMapper) {
        this.myQuestionRepository = myQuestionRepository;
        this.processRepository = processRepository;
        this.functionalityRepository = functionalityRepository;
        this.objectMapper = objectMapper;
    }

    public List<MyQuestion> findAll() {
        return myQuestionRepository.findAll();
    }

    public Page<MyQuestion> findByUserEmail(String email, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return myQuestionRepository.findByUserEmail(email, pageable);
    }


    public Optional<MyQuestion> findById(UUID id) {
        return myQuestionRepository.findById(id);
    }

    public void saveMyQuestion(MyQuestion myQuestion) {
        if (!processRepository.existsById(myQuestion.getProcess().getId())) {
            throw new IllegalArgumentException("El proceso con ID " + myQuestion.getProcess().getId() + " no existe");
        }
        if (!functionalityRepository.existsById(myQuestion.getFunctionality().getId())) {
            throw new IllegalArgumentException("La funcionalidad con ID " + myQuestion.getFunctionality().getId() + " no existe");
        }
        myQuestionRepository.save(myQuestion);
    }

    public MyQuestion updateMyQuestion(UUID id, MyQuestion updatedQuestion) {
        return myQuestionRepository.findById(id)
                .map(existingQuestion -> {
                    UUID processId = updatedQuestion.getProcess().getId();
                    UUID functionalityId = updatedQuestion.getFunctionality().getId();

                    FaqProcess process = processRepository.findById(processId)
                            .orElseThrow(() -> new RuntimeException("El proceso con ID " + processId + " no existe."));
                    Functionality functionality = functionalityRepository.findById(functionalityId)
                            .orElseThrow(() -> new RuntimeException("La funcionalidad con ID " + functionalityId + " no existe."));

                    existingQuestion.setProcess(process);
                    existingQuestion.setFunctionality(functionality);
                    existingQuestion.setDescription(updatedQuestion.getDescription());
                    existingQuestion.setUserEmail(updatedQuestion.getUserEmail());
                    existingQuestion.setAdminEmail(updatedQuestion.getAdminEmail());
                    existingQuestion.setAnswer(updatedQuestion.getAnswer());

                    return myQuestionRepository.save(existingQuestion);
                })
                .orElseThrow(() -> new RuntimeException("No se encontró la pregunta con ID " + id));
    }

    public void deleteMyQuestion(UUID id) {
        myQuestionRepository.deleteById(id);
    }

    public void updateMyQuestionByParams(UUID id, Map<String, Object> data) {
        MyQuestion myQuestion = myQuestionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la pregunta con ID " + id));

        try {
            objectMapper.updateValue(myQuestion, data);
            myQuestionRepository.save(myQuestion);
        } catch (JsonMappingException e) {
            throw new IllegalStateException("Error al mapear los datos de actualización", e);
        }
    }

}
