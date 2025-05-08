package co.ucoshop.pqrsprocesadorms.repositories.faq;

import co.ucoshop.pqrsprocesadorms.domain.faq.FaqProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProcessRepository extends JpaRepository<FaqProcess, UUID> {
}
