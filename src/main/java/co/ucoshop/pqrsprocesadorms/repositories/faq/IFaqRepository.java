package co.ucoshop.pqrsprocesadorms.repositories.faq;

import co.ucoshop.pqrsprocesadorms.domain.faq.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IFaqRepository extends JpaRepository<Faq,UUID>{

    List<Faq> findAllByFunctionality_Id(UUID functionalityId);

}
