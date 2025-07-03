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
    void testGetById_NotFound() {
        when(competenceRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> competenceService.getById(1L));
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

}
