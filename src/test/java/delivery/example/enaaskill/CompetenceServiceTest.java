package delivery.example.enaaskill;

import delivery.example.enaaskill.model.Competence;
import delivery.example.enaaskill.repository.CompetenceRepository;
import delivery.example.enaaskill.repository.SousCompetenceRepository;
import delivery.example.enaaskill.service.CompetenceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompetenceServiceTest {

    @InjectMocks
    private CompetenceService competenceService;

    @Mock
    private CompetenceRepository competenceRepository;

    @Mock
    private SousCompetenceRepository sousCompetenceRepository;
    @Test
    void testCreateCompetence() {
        Competence competence = new Competence();
        competence.setNom("Java");

        when(competenceRepository.save(any(Competence.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Competence result = competenceService.create(competence);

        assertNotNull(result.getDateCreation());
        assertNotNull(result.getDateModification());
        verify(competenceRepository, times(1)).save(competence);
    }

}
