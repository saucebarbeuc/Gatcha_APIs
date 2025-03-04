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
}