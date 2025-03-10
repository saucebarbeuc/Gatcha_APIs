package imt.production.dev.Model;

import imt.production.dev.Enum.TypeElementaire;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "monstre")
public class Monstre {

    @Id
    private String id;
    private String nom;
    private int niveau;
    private int experiences;
    private TypeElementaire typeElementaire;
    private Stats stats;
    private List<Competence> competences;

    public Monstre(String nom, int niveau, int experiences, TypeElementaire typeElementaire, Stats stats,
            List<Competence> competences) {
        this.nom = nom;
        this.niveau = niveau;
        this.experiences = experiences;
        this.typeElementaire = typeElementaire;
        this.stats = stats;
        this.competences = competences;
    }
    
}