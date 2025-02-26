package imt.production.dev.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JoueurDto {

    private String id;
    private String nom;
    private int level;
    private int experience;
    private int experienceThreshold;
    private List<String> monstres;
}