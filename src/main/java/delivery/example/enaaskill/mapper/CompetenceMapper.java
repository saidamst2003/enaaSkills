package delivery.example.enaaskill.mapper;

import delivery.example.enaaskill.DTO.CompetenceDTO;
import delivery.example.enaaskill.DTO.SousCompetenceDTO;
import delivery.example.enaaskill.model.Competence;
import delivery.example.enaaskill.model.SousCompetence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompetenceMapper {

    CompetenceMapper INSTANCE = Mappers.getMapper(CompetenceMapper.class);

    CompetenceDTO toDto(Competence entity);
    Competence toEntity(CompetenceDTO dto);

    SousCompetenceDTO toSousDto(SousCompetence entity);
    SousCompetence toSousEntity(SousCompetenceDTO dto);

    List<CompetenceDTO> toDtoList(List<Competence> entities);
    List<Competence> toEntityList(List<CompetenceDTO> dtos);
}
