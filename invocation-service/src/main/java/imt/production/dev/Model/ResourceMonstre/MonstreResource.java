package imt.production.dev.Model.ResourceMonstre;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonstreResource { 

    private int _id;
    private String element;
    private int hp;
    private int atk;
    private int def;
    private int vit;
    private List<Competence> skills;
    private float lootRate;

    


}