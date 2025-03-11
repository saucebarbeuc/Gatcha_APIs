package imt.production.dev.Utils;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Stats {

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