package delivery.example.enaaskill.repository;


import delivery.example.enaaskill.model.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CompetenceRepository extends JpaRepository<Competence, Long> {
}
