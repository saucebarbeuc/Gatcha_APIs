package imt.production.dev.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Document(collection = "monstres")
public class Monstre {

    @Id
    @Indexed(unique = true) 
    private int id;

    @NotBlank(message = "Le nom du monstre est obligatoire.")
    @Size(max = 25, message = "Le nom du monstre ne peut pas dépasser 25 caractères.")
    private String nom;

    @NotBlank(message = "La description est obligatoire.")
    @Size(min = 25, message = "La description doit contenir au moins 25 caractères.")
    private String description;

    @Min(value = 1, message = "Le prix doit être supérieur à 0.")
    private int prix;


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    
}
