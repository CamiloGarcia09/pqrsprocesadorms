package co.ucoshop.pqrsprocesadorms.services.faq;

import co.ucoshop.pqrsprocesadorms.domain.faq.Faq;
import co.ucoshop.pqrsprocesadorms.repositories.faq.IFaqRepository;
import co.ucoshop.pqrsprocesadorms.repositories.faq.IFunctionalityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class FaqService {
    private final IFaqRepository faqRepository;
    private final IFunctionalityRepository functionalityRepository;

    public FaqService(IFaqRepository faqRepository, IFunctionalityRepository functionalityRepository) {
        this.faqRepository = faqRepository;
        this.functionalityRepository = functionalityRepository;
    }

    public Page<Faq> getFaqs(int page, int size) {
        int maxSize = 50;
        size = Math.min(size, maxSize);
        page = Math.max(page, 0);

        Pageable pageable = PageRequest.of(page, size);
        return faqRepository.findAll(pageable);
    }

    public List<Faq> getFaqsByFunctionalityId(UUID functionalityId) {
        if (!functionalityRepository.existsById(functionalityId)) {
            throw new IllegalArgumentException("Funcionalidad con id " + functionalityId + " no existe");
        }
        return faqRepository.findAllByFunctionality_Id(functionalityId);
    }
}
