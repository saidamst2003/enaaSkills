package delivery.example.enaaskill.mapper;

import delivery.example.enaaskill.DTO.SousCompetenceDTO;
import delivery.example.enaaskill.model.SousCompetence;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface SousCompetenceMapper {

    @Mapping(source = "competence.id", target = "competenceId")
    SousCompetenceDTO toDTO(SousCompetence entity);

    @Mapping(source = "competenceId", target = "competence")
    SousCompetence toEntity(SousCompetenceDTO dto);

    // Méthode d’aide pour convertir Long competenceId en Competence (à gérer en service)
    default delivery.example.enaaskill.model.Competence mapCompetenceIdToCompetence(Long id) {
        if (id == null) {
            return null;
        }
        delivery.example.enaaskill.model.Competence c = new delivery.example.enaaskill.model.Competence();
        c.setId(id);
        return c;
    }
}
