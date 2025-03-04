package imt.production.dev.Model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "joueurs")
public class Joueur {

    @Id
    @NotBlank(message = "Identifiant unique du joueur")
    private String id;

    @NotBlank(message = "Nom du joueur")
    private String name;

    @NotBlank(message = "Niveau du joueur")
    private int level;

    @NotBlank(message = "Expérience du joueur")
    private int experience;

    @NotBlank(message = "Seuil d'expérience pour le prochain niveau")
    private int experienceThreshold;

    @NotBlank(message = "Liste des monstres du joueur")
    private List<String> monsters = new ArrayList<>();

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getExperienceThreshold() {
        return experienceThreshold;
    }

    public void setExperienceThreshold(int experienceThreshold) {
        this.experienceThreshold = experienceThreshold;
    }

    public List<String> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<String> monsters) {
        this.monsters = monsters;
    }
}
