package co.ucoshop.pqrsprocesadorms.services.faq;

import co.ucoshop.pqrsprocesadorms.domain.faq.FaqProcess;
import co.ucoshop.pqrsprocesadorms.repositories.faq.IProcessRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessService {

    private final IProcessRepository processRepository;

    public ProcessService(IProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public List<FaqProcess> getProcesses() {
        return processRepository.findAll();
    }
}
