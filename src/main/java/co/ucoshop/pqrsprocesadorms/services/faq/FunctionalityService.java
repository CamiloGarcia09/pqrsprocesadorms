package co.ucoshop.pqrsprocesadorms.services.faq;

import co.ucoshop.pqrsprocesadorms.domain.faq.Functionality;
import co.ucoshop.pqrsprocesadorms.repositories.faq.IFunctionalityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionalityService {

    private final IFunctionalityRepository functionalityRepository;

    public FunctionalityService(IFunctionalityRepository functionalityRepository) {
        this.functionalityRepository = functionalityRepository;
    }

    public List<Functionality> getFunctionalities() {
        return functionalityRepository.findAll();
    }
}
