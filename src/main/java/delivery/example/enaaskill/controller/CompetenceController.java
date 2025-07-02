package delivery.example.enaaskill.controller;

import delivery.example.enaaskill.DTO.CompetenceDTO;
import delivery.example.enaaskill.mapper.CompetenceMapper;
import delivery.example.enaaskill.model.Competence;
import delivery.example.enaaskill.service.CompetenceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competences")
@CrossOrigin("*")
public class CompetenceController {

    @Autowired
    private CompetenceService competenceService;

    @Autowired
    private CompetenceMapper competenceMapper;

    // Créer une compétence
    @PostMapping
    public CompetenceDTO create(@Valid @RequestBody CompetenceDTO dto) {
        Competence competence = competenceMapper.toEntity(dto);
        Competence saved = competenceService.create(competence);
        return competenceMapper.toDto(saved);
    }

    // Récupérer toutes les compétences
    @GetMapping
    public List<CompetenceDTO> getAll() {
        return competenceMapper.toDtoList(competenceService.getAll());
    }

    // Récupérer une compétence par ID
    @GetMapping("/{id}")
    public CompetenceDTO getById(@PathVariable Long id) {
        return competenceMapper.toDto(competenceService.getById(id));
    }

    // Modifier une compétence
    @PutMapping("/{id}")
    public CompetenceDTO update(@PathVariable Long id, @Valid @RequestBody CompetenceDTO dto) {
        Competence updated = competenceService.update(id, competenceMapper.toEntity(dto));
        return competenceMapper.toDto(updated);
    }

    // Supprimer une compétence
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        competenceService.delete(id);
    }

}
