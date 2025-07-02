package delivery.example.enaaskill.controller;

import delivery.example.enaaskill.DTO.CompetenceDTO;
import delivery.example.enaaskill.model.Competence;
import delivery.example.enaaskill.model.SousCompetence;
import delivery.example.enaaskill.service.CompetenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/competences")
@CrossOrigin("*")
public class CompetenceController {

    @Autowired
    private CompetenceService competenceService;

    // Créer une compétence
    @PostMapping
    public CompetenceDTO create(@Valid @RequestBody CompetenceDTO dto) {
        Competence competence = dtoToEntity(dto);
        Competence saved = competenceService.create(competence);
        return entityToDto(saved);
    }

    // Récupérer toutes les compétences
    @GetMapping
    public List<CompetenceDTO> getAll() {
        return competenceService.getAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    // Récupérer une compétence par ID
    @GetMapping("/{id}")
    public CompetenceDTO getById(@PathVariable Long id) {
        return entityToDto(competenceService.getById(id));
    }

    // Modifier une compétence
    @PutMapping("/{id}")
    public CompetenceDTO update(@PathVariable Long id, @Valid @RequestBody CompetenceDTO dto) {
        Competence updated = competenceService.update(id, dtoToEntity(dto));
        return entityToDto(updated);
    }

    // Supprimer une compétence
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        competenceService.delete(id);
    }

    // Conversion DTO -> Entity
    private Competence dtoToEntity(CompetenceDTO dto) {
        Competence competence = new Competence();
        competence.setId(dto.getId());
        competence.setNom(dto.getNom());
        competence.setDescription(dto.getDescription());
        competence.setSeuilValidation(dto.getSeuilValidation());

        if (dto.getSousCompetences() != null) {
            List<SousCompetence> sousCompetences = dto.getSousCompetences()
                    .stream()
                    .map(scdto -> {
                        SousCompetence sc = new SousCompetence();
                        sc.setId(scdto.getId());
                        sc.setNom(scdto.getNom());
                        sc.setDescription(scdto.getDescription());
                        sc.setCompetence(competence);
                        return sc;
                    })
                    .collect(Collectors.toList());

            competence.setSousCompetences(sousCompetences);
        }

        return competence;
    }



}
