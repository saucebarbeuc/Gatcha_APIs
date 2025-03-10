package imt.production.dev.Dto;

import lombok.*;

import java.util.List;

import imt.production.dev.Utils.Competence;
import imt.production.dev.Utils.Stats;
import imt.production.dev.Utils.TypeElementaire;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MonstreDto {

    private String nom;
    private int niveau;
    private int experiences;
    private TypeElementaire typeElementaire;
    private Stats stats;
    private List<Competence> competences;

}