package imt.production.dev.Mapper;

import imt.production.dev.Dto.CompetenceDto;
import imt.production.dev.Model.CompetenceModel;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompetenceMapper {

    public CompetenceDto toDto(CompetenceModel competence) {
        if (competence == null) {
            return null;
        }
        return new CompetenceDto(
                competence.getNom(),
                competence.getDegatsBase(),
                competence.getRatioDegats(),
                competence.getCooldown(),
                competence.getNiveau(),
                competence.getNiveauMax()
        );
    }

    public CompetenceModel toEntity(CompetenceDto competenceDto) {
        if (competenceDto == null) {
            return null;
        }
        return new CompetenceModel(
                competenceDto.getNom(),
                competenceDto.getDegatsBase(),
                competenceDto.getRatioDegats(),
                competenceDto.getCooldown(),
                competenceDto.getNiveau(),
                competenceDto.getNiveauMax()
        );
    }

    public List<CompetenceDto> toDtoList(List<CompetenceModel> competences) {
        return competences.stream().map(this::toDto).collect(Collectors.toList());
    }

    public List<CompetenceModel> toEntityList(List<CompetenceDto> competenceDtos) {
        return competenceDtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}