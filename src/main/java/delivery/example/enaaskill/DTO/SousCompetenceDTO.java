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
}
