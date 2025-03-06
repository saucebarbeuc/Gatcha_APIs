package imt.production.dev.Dto;

import imt.production.dev.Enum.TypeElementaire;
import imt.production.dev.Model.Stats;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MonstreDto {

    private String id;
    private String nom;
    private int niveau;
    private int experiences;
    private TypeElementaire typeElementaire;
    private Stats stats;
    private List<CompetenceDto> competences;

}