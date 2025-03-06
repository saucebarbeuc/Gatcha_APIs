package imt.production.dev.Dto;

import jakarta.validation.constraints.NotBlank;

public class MonstreDTO {

    @NotBlank(message = "Le nom du monstre est obligatoire.")
    private String nom;

    @NotBlank(message = "La description est obligatoire.")
    private String description;

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
