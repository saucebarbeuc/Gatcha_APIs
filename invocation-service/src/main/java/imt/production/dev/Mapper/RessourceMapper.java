package imt.production.dev.Mapper;

import java.util.ArrayList;
import java.util.List;

import imt.production.dev.Dto.MonstreUtils.MonstreDto;
import imt.production.dev.Dto.MonstreUtils.CompetenceDto;
import imt.production.dev.Dto.MonstreUtils.Stats;
import imt.production.dev.Dto.MonstreUtils.TypeElementaire;
import imt.production.dev.Model.ResourceMonstre.MonstreResource;

public class RessourceMapper {

    public static MonstreDto toDto(MonstreResource model) {
        List<CompetenceDto> skills = new ArrayList<>();
        model.getSkills().forEach(skill -> {
            skills.add(new CompetenceDto(
                "",
                skill.getDmg(),
                skill.getRatio().getPercent(),
                skill.getCooldown(),
                -1,
                skill.getLvlMax()
            ));
        });

        return new MonstreDto(
            "",
            -1,
            -1,
            TypeElementaire.fromString(model.getElement()),
            new Stats(
                model.getHp(),
                model.getAtk(),
                model.getDef(),
                model.getVit()
            ),
            skills
        );

    }

}