package imt.production.dev.Model;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "invocation")
public class Invocation {

    @Id
    private String id;
    private String username;
    private int calcul;
    private String idMonstre;

    
}