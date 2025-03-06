package imt.production.dev.Model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Competence {

    private String nom;
    private int degatsBase;
    private double ratioDegats;
    private int cooldown;
    private int niveau;
    private int niveauMax;

    public void ameliorer() {
        if (niveau < niveauMax) {
            niveau++;
            degatsBase += 5;
            ratioDegats += 0.1;
        } else {
            throw new IllegalStateException("La compétence est déjà au niveau maximal.");
        }
    }

    public boolean estNiveauMax() {
        return niveau >= niveauMax;
    }
}