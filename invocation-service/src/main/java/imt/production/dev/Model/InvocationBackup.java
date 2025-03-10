package imt.production.dev.Model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "backup")
public class InvocationBackup {

    private String id;
    private String username;
    private Invocation savedInvocation;
    private int calcul;
    private String idMonstre;
    
}
