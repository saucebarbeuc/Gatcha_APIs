package imt.production.dev.Mapper;

import imt.production.dev.Dto.MonstreDto;
import imt.production.dev.Model.Monstre;

public class MonstreMapper {

    public static MonstreDto toDto(Monstre monstre) {
        if (monstre == null) {
            return null;
        }
        return new MonstreDto(
                monstre.getId(),
                monstre.getNom(),
                monstre.getNiveau(),
                monstre.getExperiences(),
                monstre.getTypeElementaire(),
                monstre.getStats(),
                CompetenceMapper.toDtoList(monstre.getCompetences())
        );
    }

    public static Monstre toEntity(MonstreDto monstreDto) {
        if (monstreDto == null) {
            return null;
        }
        return new Monstre(
                monstreDto.getId(),
                monstreDto.getNom(),
                monstreDto.getNiveau(),
                monstreDto.getExperiences(),
                monstreDto.getTypeElementaire(),
                monstreDto.getStats(),
                CompetenceMapper.toEntityList(monstreDto.getCompetences())
        );
    }
}