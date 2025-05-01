package co.ucoshop.pqrsprocesadorms.repositories.pqrs;

import co.ucoshop.ucoshopapi.domain.pqrs.TypePqrs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITypePqrsRepository extends JpaRepository<TypePqrs, UUID> {
}
