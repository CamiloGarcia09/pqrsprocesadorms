package co.ucoshop.pqrsprocesadorms.repositories.pqrs;


import co.ucoshop.pqrsprocesadorms.domain.pqrs.Pqrs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IPqrsRepository extends JpaRepository<Pqrs, UUID> {

    Page<Pqrs> findAll(Pageable pageable);

    Optional<Pqrs> findById(UUID id);

    Page<Pqrs> findAllByUserEmail(String userEmail, Pageable pageable);
}