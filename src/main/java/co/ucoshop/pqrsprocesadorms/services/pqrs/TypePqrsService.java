package co.ucoshop.pqrsprocesadorms.services.pqrs;

import co.ucoshop.ucoshopapi.domain.pqrs.TypePqrs;
import co.ucoshop.ucoshopapi.repositories.pqrs.ITypePqrsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypePqrsService {

    private final ITypePqrsRepository iTypePqrsRepository;

    public TypePqrsService(ITypePqrsRepository typePqrsRepository) {
        this.iTypePqrsRepository = typePqrsRepository;
    }

    public List<TypePqrs> findAll() {
        return iTypePqrsRepository.findAll();
    }
}
