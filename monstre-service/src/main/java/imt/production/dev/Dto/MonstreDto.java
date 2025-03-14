package imt.production.dev.Dto;

import imt.production.dev.Utils.Stats;
import imt.production.dev.Utils.TypeElementaire;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MonstreDto {

    private String nom;
    private TypeElementaire typeElementaire;
    private Stats stats;
    private List<CompetenceDto> competences;

}