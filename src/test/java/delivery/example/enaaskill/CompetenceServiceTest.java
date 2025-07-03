package delivery.example.enaaskill;

import delivery.example.enaaskill.model.Competence;
import delivery.example.enaaskill.model.SousCompetence;
import delivery.example.enaaskill.repository.CompetenceRepository;
import delivery.example.enaaskill.repository.SousCompetenceRepository;
import delivery.example.enaaskill.service.CompetenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    void testGetAllCompetences() {
        List<Competence> list = List.of(new Competence(), new Competence());

        when(competenceRepository.findAll()).thenReturn(list);

        List<Competence> result = competenceService.getAll();

        assertEquals(2, result.size());
        verify(competenceRepository, times(1)).findAll();
    }




    @Test
    void testUpdateCompetence() {
        Competence existing = new Competence();
        existing.setNom("Old Name");

        Competence newData = new Competence();
        newData.setNom("New Name");
        newData.setDescription("Description");
        newData.setSeuilValidation(2);

        when(competenceRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(competenceRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Competence result = competenceService.update(1L, newData);

        assertEquals("New Name", result.getNom());
        assertEquals("Description", result.getDescription());
        assertEquals(2, result.getSeuilValidation());
        verify(competenceRepository).save(existing);
    }

    @Test
    void testDeleteCompetence() {
        doNothing().when(competenceRepository).deleteById(1L);

        competenceService.delete(1L);

        verify(competenceRepository, times(1)).deleteById(1L);
    }

    @Test
    void testMettreAJourValidationCompetence_Validée() {
        Competence competence = new Competence();
        competence.setSeuilValidation(2);
        competence.setValidee(false);

        SousCompetence sc1 = new SousCompetence();
        sc1.setValidee(true);
        SousCompetence sc2 = new SousCompetence();
        sc2.setValidee(true);

        List<SousCompetence> list = List.of(sc1, sc2);

        when(competenceRepository.findById(1L)).thenReturn(Optional.of(competence));
        when(sousCompetenceRepository.findByCompetenceId(1L)).thenReturn(list);

        competenceService.mettreAJourValidationCompetence(1L);

        assertTrue(competence.isValidee());
        verify(competenceRepository).save(competence);
    }
    @Test
    void testMettreAJourValidationCompetence_NonValidée() {
        Competence competence = new Competence();
        competence.setSeuilValidation(3);
        competence.setValidee(true);

        SousCompetence sc1 = new SousCompetence();
        sc1.setValidee(true);
        SousCompetence sc2 = new SousCompetence();
        sc2.setValidee(false);

        List<SousCompetence> list = List.of(sc1, sc2);

        when(competenceRepository.findById(1L)).thenReturn(Optional.of(competence));
        when(sousCompetenceRepository.findByCompetenceId(1L)).thenReturn(list);

        competenceService.mettreAJourValidationCompetence(1L);

        assertFalse(competence.isValidee());
        verify(competenceRepository).save(competence);
    }

}
