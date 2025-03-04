package imt.production.dev.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "monstres")
public class Monstre {

    @Id
    @Indexed(unique = true) 
    private String id;

    @NotBlank(message = "Le nom du monstre est obligatoire.")
    private String nom;

    @NotBlank(message = "La description est obligatoire.")
    private String description;

    public Monstre(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
}
