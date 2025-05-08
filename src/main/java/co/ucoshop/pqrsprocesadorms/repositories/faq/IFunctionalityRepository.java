package co.ucoshop.pqrsprocesadorms.repositories.faq;

import co.ucoshop.pqrsprocesadorms.domain.faq.Functionality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IFunctionalityRepository extends JpaRepository<Functionality, UUID> {
}
