package delivery.example.enaaskill.service;

import delivery.example.enaaskill.DTO.SousCompetenceDTO;
import delivery.example.enaaskill.mapper.SousCompetenceMapper;
import delivery.example.enaaskill.model.SousCompetence;
import delivery.example.enaaskill.repository.SousCompetenceRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SousCompetenceService {

    private final SousCompetenceRepository sousCompetenceRepository;
    private final SousCompetenceMapper sousCompetenceMapper;

    public SousCompetenceService(SousCompetenceRepository sousCompetenceRepository, SousCompetenceMapper sousCompetenceMapper) {
        this.sousCompetenceRepository = sousCompetenceRepository;
        this.sousCompetenceMapper = sousCompetenceMapper;
    }

    //  Créer une nouvelle sous-compétence
    public SousCompetenceDTO createSousCompetence(SousCompetenceDTO dto) {
        SousCompetence entity = sousCompetenceMapper.toEntity(dto);
        SousCompetence saved = sousCompetenceRepository.save(entity);
        return sousCompetenceMapper.toDTO(saved);
    }

    //  Obtenir toutes les sous-compétences
    public List<SousCompetenceDTO> getAllSousCompetences() {
        return sousCompetenceRepository.findAll()
                .stream()
                .map(sousCompetenceMapper::toDTO)
                .collect(Collectors.toList());
    }



    // Mettre à jour une sous-compétence
    public Optional<SousCompetenceDTO> updateSousCompetence(Long id, SousCompetenceDTO dto) {
        return sousCompetenceRepository.findById(id).map(existing -> {
            existing.setNom(dto.getNom());
            existing.setValidee(dto.isValidee()); // à ajouter dans DTO si manquant
            SousCompetence updated = sousCompetenceRepository.save(existing);
            return sousCompetenceMapper.toDTO(updated);
        });
    }

    // Supprimer une sous-compétence
    public boolean deleteSousCompetence(Long id) {
        if (sousCompetenceRepository.existsById(id)) {
            sousCompetenceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Lister les sous-compétences par compétence ID
    public List<SousCompetenceDTO> getByCompetenceId(Long competenceId) {
        return sousCompetenceRepository.findByCompetenceId(competenceId)
                .stream()
                .map(sousCompetenceMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Marquer une sous-compétence comme validée
    public Optional<SousCompetenceDTO> validateSousCompetence(Long id) {
        return sousCompetenceRepository.findById(id).map(sc -> {
            sc.setValidee(true);
            SousCompetence updated = sousCompetenceRepository.save(sc);
            return sousCompetenceMapper.toDTO(updated);
        });
    }
}
