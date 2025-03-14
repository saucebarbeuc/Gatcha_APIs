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
                "Competence de " + skill.getRatio().getStat(),
                skill.getDmg(),
                skill.getRatio().getPercent(),
                skill.getCooldown(),
                skill.getLvlMax()
            ));
        });

        return new MonstreDto(
            "Monstre de " + model.getElement(),
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