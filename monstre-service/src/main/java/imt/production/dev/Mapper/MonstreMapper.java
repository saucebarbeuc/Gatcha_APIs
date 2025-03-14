package imt.production.dev.Mapper;

import imt.production.dev.Dto.MonstreDto;
import imt.production.dev.Model.Monstre;

public class MonstreMapper {

    public static MonstreDto toDto(Monstre monstre) {
        if (monstre == null) {
            return null;
        }
        return new MonstreDto(
                monstre.getNom(),
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
                monstreDto.getNom(),
                1,
                0,
                monstreDto.getTypeElementaire(),
                monstreDto.getStats(),
                CompetenceMapper.toEntityList(monstreDto.getCompetences())
        );
    }
}