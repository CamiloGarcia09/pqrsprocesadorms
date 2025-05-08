package co.ucoshop.pqrsprocesadorms.repositories.myQuestion;

import co.ucoshop.pqrsprocesadorms.domain.myQuestion.MyQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IMyQuestionRepository extends JpaRepository<MyQuestion, UUID> {

    Page<MyQuestion> findByUserEmail(String email, Pageable pageable);

}
