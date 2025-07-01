package delivery.example.enaaskill.DTO;

import java.time.LocalDateTime;

public record CompetenceDTO(
        Long id,
        String nom,
        String description,
        Integer seuilValidation,
        LocalDateTime dateCreation,
        LocalDateTime dateModification,
        List<SousCompetenceDTO> sousCompetences,
        boolean acquise
) {}