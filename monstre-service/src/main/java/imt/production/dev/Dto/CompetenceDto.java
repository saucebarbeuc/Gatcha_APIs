package imt.production.dev.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceDto {

    private String nom;
    private int degatsBase;
    private double ratioDegats;
    private int cooldown;
    private int niveauMax;
}