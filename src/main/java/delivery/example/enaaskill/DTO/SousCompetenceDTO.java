package delivery.example.enaaskill.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SousCompetenceDTO {

    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 255, message = "Le nom ne doit pas dépasser 255 caractères")
    private String nom;

    @Size(max = 1000, message = "La description ne doit pas dépasser 1000 caractères")
    private String description;

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
}
