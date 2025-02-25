package imt.production.dev.Model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatsModel {

    private int pv;
    private int atq;
    private int def;
    private int vit;

    public void ameliorer(){
        this.pv += 10;
        this.atq += 3;
        this.def += 2;
        this.vit += 1;
    }
}