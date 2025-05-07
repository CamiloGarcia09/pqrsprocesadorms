package co.ucoshop.pqrsprocesadorms.repositories.faq;

import co.ucoshop.pqrsprocesadorms.domain.faq.Functionality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IFunctionalityRepository extends JpaRepository<Functionality, UUID> {
}
