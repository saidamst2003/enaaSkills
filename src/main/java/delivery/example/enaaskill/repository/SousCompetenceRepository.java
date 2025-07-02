package delivery.example.enaaskill.repository;


import delivery.example.enaaskill.model.SousCompetence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SousCompetenceRepository extends JpaRepository<SousCompetence, Long> {

    List<SousCompetence> findByCompetenceId(Long competenceId);
}
