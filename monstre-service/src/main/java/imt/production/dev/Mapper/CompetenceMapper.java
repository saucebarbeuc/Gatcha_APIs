package imt.production.dev.Mapper;

import imt.production.dev.Dto.CompetenceDto;
import imt.production.dev.Model.Competence;
import java.util.List;
import java.util.stream.Collectors;

public class CompetenceMapper {

    public static CompetenceDto toDto(Competence competence) {
        if (competence == null) {
            return null;
        }
        return new CompetenceDto(
                competence.getNom(),
                competence.getDegatsBase(),
                competence.getRatioDegats(),
                competence.getCooldown(),
                competence.getNiveauMax()
        );
    }

    public static Competence toEntity(CompetenceDto competenceDto) {
        if (competenceDto == null) {
            return null;
        }
        return new Competence(
                competenceDto.getNom(),
                competenceDto.getDegatsBase(),
                competenceDto.getRatioDegats(),
                competenceDto.getCooldown(),
                1,
                competenceDto.getNiveauMax()
        );
    }

    public static List<CompetenceDto> toDtoList(List<Competence> competences) {
        return competences.stream().map(CompetenceMapper::toDto).collect(Collectors.toList());
    }

    public static List<Competence> toEntityList(List<CompetenceDto> competenceDtos) {
        return competenceDtos.stream().map(CompetenceMapper::toEntity).collect(Collectors.toList());
    }
}