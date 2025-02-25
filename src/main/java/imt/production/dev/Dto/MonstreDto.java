package imt.production.dev.Dto;

import imt.production.dev.Enum.TypeElementaire;
import imt.production.dev.Model.StatsModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MonstreDto {

    private String id;
    private String nom;
    private int niveau;
    private int experiences;
    private TypeElementaire typeElementaire;
    private StatsModel stats;
    private List<CompetenceDto> competences;

}