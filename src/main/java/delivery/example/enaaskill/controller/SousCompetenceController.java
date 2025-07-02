package delivery.example.enaaskill.controller;

import delivery.example.enaaskill.DTO.SousCompetenceDTO;
import delivery.example.enaaskill.service.SousCompetenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sous-competences")
public class SousCompetenceController {
    private final SousCompetenceService sousCompetenceService;

    public SousCompetenceController(SousCompetenceService sousCompetenceService) {
        this.sousCompetenceService = sousCompetenceService;
    }

    // Créer une nouvelle sous-compétence
    @PostMapping
    public ResponseEntity<SousCompetenceDTO> create(@RequestBody SousCompetenceDTO dto) {
        SousCompetenceDTO created = sousCompetenceService.createSousCompetence(dto);
        return ResponseEntity.ok(created);
    }

    // Obtenir toutes les sous-compétences
    @GetMapping
    public ResponseEntity<List<SousCompetenceDTO>> getAll() {
        List<SousCompetenceDTO> list = sousCompetenceService.getAllSousCompetences();
        return ResponseEntity.ok(list);
    }



    // Mettre à jour une sous-compétence
    @PutMapping("/{id}")
    public ResponseEntity<SousCompetenceDTO> update(@PathVariable Long id, @RequestBody SousCompetenceDTO dto) {
        return sousCompetenceService.updateSousCompetence(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //  Supprimer une sous-compétence
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (sousCompetenceService.deleteSousCompetence(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //  Marquer une sous-compétence comme validée
    @PutMapping("/{id}/valider")
    public ResponseEntity<SousCompetenceDTO> validate(@PathVariable Long id) {
        return sousCompetenceService.validateSousCompetence(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Lister les sous-compétences d'une compétence
    @GetMapping("/competence/{competenceId}")
    public ResponseEntity<List<SousCompetenceDTO>> getByCompetence(@PathVariable Long competenceId) {
        List<SousCompetenceDTO> list = sousCompetenceService.getByCompetenceId(competenceId);
        return ResponseEntity.ok(list);
    }
}
