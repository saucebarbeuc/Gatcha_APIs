package imt.production.dev.Mapper;

import imt.production.dev.Dto.MonstreDto;
import imt.production.dev.Model.MonstreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MonstreMapper {

    @Autowired
    private CompetenceMapper competenceMapper;

    public MonstreDto toDto(MonstreModel monstreModel) {
        if (monstreModel == null) {
            return null;
        }
        return new MonstreDto(
                monstreModel.getId(),
                monstreModel.getNom(),
                monstreModel.getNiveau(),
                monstreModel.getExperiences(),
                monstreModel.getTypeElementaire(),
                monstreModel.getStats(),
                competenceMapper.toDtoList(monstreModel.getCompetences())
        );
    }

    public MonstreModel toEntity(MonstreDto monstreDto) {
        if (monstreDto == null) {
            return null;
        }
        return new MonstreModel(
                monstreDto.getId(),
                monstreDto.getNom(),
                monstreDto.getNiveau(),
                monstreDto.getExperiences(),
                monstreDto.getTypeElementaire(),
                monstreDto.getStats(),
                competenceMapper.toEntityList(monstreDto.getCompetences())
        );
    }
}