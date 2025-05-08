package co.ucoshop.pqrsprocesadorms.repositories.faq;

import co.ucoshop.pqrsprocesadorms.domain.faq.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IFaqRepository extends JpaRepository<Faq,UUID> {
    List<Faq> findAllByFunctionality_Id(UUID functionalityId);
}
