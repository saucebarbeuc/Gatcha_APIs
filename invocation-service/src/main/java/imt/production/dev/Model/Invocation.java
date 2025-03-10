package imt.production.dev.Model;

import lombok.*;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import imt.production.dev.Utils.Competence;
import imt.production.dev.Utils.Stats;
import imt.production.dev.Utils.TypeElementaire;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "invocation")
public class Invocation {

    @Id
    private String id;

    private int taux;

    private TypeElementaire type;
    private Stats stats;
    private List<Competence> competences;

    
    public Invocation(int taux, TypeElementaire type, Stats stats, List<Competence> competences) {
        this.taux = taux;
        this.type = type;
        this.stats = stats;
        this.competences = competences;
    }



}