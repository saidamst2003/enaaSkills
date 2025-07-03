package delivery.example.enaaskill.service;

import delivery.example.enaaskill.DTO.SousCompetenceDTO;
import delivery.example.enaaskill.mapper.SousCompetenceMapper;
import delivery.example.enaaskill.model.SousCompetence;
import delivery.example.enaaskill.repository.SousCompetenceRepository;
import delivery.example.enaaskill.service.CompetenceService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SousCompetenceService {

    private final SousCompetenceRepository sousCompetenceRepository;
    private final SousCompetenceMapper sousCompetenceMapper;
    private final CompetenceService competenceService;

    public SousCompetenceService(SousCompetenceRepository sousCompetenceRepository,
                                 SousCompetenceMapper sousCompetenceMapper,
                                 CompetenceService competenceService) {
        this.sousCompetenceRepository = sousCompetenceRepository;
        this.sousCompetenceMapper = sousCompetenceMapper;
        this.competenceService = competenceService;
    }

    public SousCompetenceDTO createSousCompetence(SousCompetenceDTO dto) {
        SousCompetence entity = sousCompetenceMapper.toEntity(dto);
        SousCompetence saved = sousCompetenceRepository.save(entity);
        return sousCompetenceMapper.toDTO(saved);
    }

    public List<SousCompetenceDTO> getAllSousCompetences() {
        return sousCompetenceRepository.findAll()
                .stream()
                .map(sousCompetenceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<SousCompetenceDTO> updateSousCompetence(Long id, SousCompetenceDTO dto) {
        return sousCompetenceRepository.findById(id).map(existing -> {
            existing.setNom(dto.getNom());
            existing.setValidee(dto.isValidee());
            SousCompetence updated = sousCompetenceRepository.save(existing);
            return sousCompetenceMapper.toDTO(updated);
        });
    }

    public boolean deleteSousCompetence(Long id) {
        if (sousCompetenceRepository.existsById(id)) {
            sousCompetenceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<SousCompetenceDTO> getByCompetenceId(Long competenceId) {
        return sousCompetenceRepository.findByCompetenceId(competenceId)
                .stream()
                .map(sousCompetenceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<SousCompetenceDTO> validateSousCompetence(Long id) {
        return sousCompetenceRepository.findById(id).map(sc -> {
            sc.setValidee(true);
            SousCompetence updated = sousCompetenceRepository.save(sc);

            // Update competence validation
            if (sc.getCompetence() != null) {
                Long competenceId = sc.getCompetence().getId();
                competenceService.mettreAJourValidationCompetence(competenceId);
            }

            return sousCompetenceMapper.toDTO(updated);
        });
    }
}
