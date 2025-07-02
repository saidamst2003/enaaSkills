package delivery.example.enaaskill.DTO;

import delivery.example.enaaskill.DTO.SousCompetenceDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.time.LocalDateTime;
import java.util.List;

public class CompetenceDTO {

    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 255, message = "Le nom ne doit pas dépasser 255 caractères")
    private String nom;

    @Size(max = 1000, message = "La description ne doit pas dépasser 1000 caractères")
    private String description;

    @NotNull(message = "Le seuil de validation est obligatoire")
    @Min(value = 0, message = "Le seuil de validation doit être positif")
    private Integer seuilValidation;

    private LocalDateTime dateCreation;

    private LocalDateTime dateModification;

    private List<SousCompetenceDTO> sousCompetences;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Le nom est obligatoire") @Size(max = 255, message = "Le nom ne doit pas dépasser 255 caractères") String getNom() {
        return nom;
    }

    public void setNom(@NotBlank(message = "Le nom est obligatoire") @Size(max = 255, message = "Le nom ne doit pas dépasser 255 caractères") String nom) {
        this.nom = nom;
    }

    public @Size(max = 1000, message = "La description ne doit pas dépasser 1000 caractères") String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 1000, message = "La description ne doit pas dépasser 1000 caractères") String description) {
        this.description = description;
    }

    public @NotNull(message = "Le seuil de validation est obligatoire") @Min(value = 0, message = "Le seuil de validation doit être positif") Integer getSeuilValidation() {
        return seuilValidation;
    }

    public void setSeuilValidation(@NotNull(message = "Le seuil de validation est obligatoire") @Min(value = 0, message = "Le seuil de validation doit être positif") Integer seuilValidation) {
        this.seuilValidation = seuilValidation;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }

    public List<SousCompetenceDTO> getSousCompetences() {
        return sousCompetences;
    }

    public void setSousCompetences(List<SousCompetenceDTO> sousCompetences) {
        this.sousCompetences = sousCompetences;
    }
}
