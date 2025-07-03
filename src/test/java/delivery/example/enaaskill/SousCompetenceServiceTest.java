package delivery.example.enaaskill;


import delivery.example.enaaskill.DTO.SousCompetenceDTO;
import delivery.example.enaaskill.mapper.SousCompetenceMapper;
import delivery.example.enaaskill.model.Competence;
import delivery.example.enaaskill.model.SousCompetence;
import delivery.example.enaaskill.repository.SousCompetenceRepository;
import delivery.example.enaaskill.service.CompetenceService;
import delivery.example.enaaskill.service.SousCompetenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SousCompetenceServiceTest {

    @Mock
    private SousCompetenceRepository sousCompetenceRepository;

    @Mock
    private SousCompetenceMapper sousCompetenceMapper;

    @Mock
    private CompetenceService competenceService;

    @InjectMocks
    private SousCompetenceService sousCompetenceService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreateSousCompetence() {
        SousCompetenceDTO dto = new SousCompetenceDTO();
        dto.setNom("Test");

        SousCompetence entity = new SousCompetence();
        entity.setNom("Test");

        when(sousCompetenceMapper.toEntity(dto)).thenReturn(entity);
        when(sousCompetenceRepository.save(entity)).thenReturn(entity);
        when(sousCompetenceMapper.toDTO(entity)).thenReturn(dto);

        SousCompetenceDTO result = sousCompetenceService.createSousCompetence(dto);

        assertEquals(dto.getNom(), result.getNom());
        verify(sousCompetenceRepository).save(entity);
    }

    @Test
    void testGetAllSousCompetences() {
        SousCompetence sc1 = new SousCompetence();
        sc1.setId(1L);
        SousCompetence sc2 = new SousCompetence();
        sc2.setId(2L);

        when(sousCompetenceRepository.findAll()).thenReturn(List.of(sc1, sc2));
        when(sousCompetenceMapper.toDTO(any())).thenReturn(new SousCompetenceDTO());

        List<SousCompetenceDTO> result = sousCompetenceService.getAllSousCompetences();

        assertEquals(2, result.size());
    }


}

