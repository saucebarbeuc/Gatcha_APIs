package imt.production.dev.Model.ResourceMonstre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Competence {

    private int num;
    private int dmg;
    private Ratio ratio;
    private int cooldown;
    private int lvlMax;

}