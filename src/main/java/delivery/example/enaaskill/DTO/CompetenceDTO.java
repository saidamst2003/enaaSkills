package delivery.example.enaaskill.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

public record CompetenceDTO(
        Long id,

        @NotBlank(message = "Le nom est obligatoire")
        @Size(max = 255, message = "Le nom ne doit pas dépasser 255 caractères")
        String nom,

        @Size(max = 1000, message = "La description ne doit pas dépasser 1000 caractères")
        String description,

        @NotNull(message = "Le seuil de validation est obligatoire")
        @Min(value = 0, message = "Le seuil de validation doit être positif")
        Integer seuilValidation,

        LocalDateTime dateCreation,

        LocalDateTime dateModification,

        List<SousCompetenceDTO> sousCompetences
) {}
