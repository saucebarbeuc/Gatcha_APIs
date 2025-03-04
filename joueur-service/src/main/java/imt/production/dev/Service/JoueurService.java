package imt.production.dev.Service;

import imt.production.dev.DTO.JoueurDTO;
import imt.production.dev.Errors.HTTP_409.UtilisateurDejaExistantException;
import imt.production.dev.Model.Joueur;
import imt.production.dev.Repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JoueurService {

    @Autowired
    private JoueurRepository joueurRepository;

    

    public List<Joueur> getAllJoueurs() {
        return joueurRepository.findAll();
    }

    public Optional<Joueur> getJoueurById(String id) {
        return joueurRepository.findById(id);
    }

    public Optional<Joueur> getLevelById(String id) {
        Joueur joueur = joueurRepository.findById(id).orElse(null);
        if (joueur == null) {
            return null;
        } else {
            return Optional.empty();
        }
    }

    public JoueurDTO createJoueur(JoueurDTO dto) {
        Joueur joueur = new Joueur();
        joueur.setName(dto.getUsername());
        joueur.setLevel(1);
        joueur.setExperience(0);
        joueur.setExperienceThreshold(50);
        joueur.setMonsters(new ArrayList<>());
        
        if (joueurRepository.existsByName(dto.getUsername())) {
            throw new UtilisateurDejaExistantException("Joueur déjà existant");
        }

        return new JoueurDTO(joueurRepository.save(joueur).getName());
    }

    public Joueur updateJoueur(String id, JoueurDTO dto) {
        Optional<Joueur> joueur = joueurRepository.findById(id);

        if (joueur.isPresent()) {
            joueur.get().setName(dto.getUsername());
            joueurRepository.save(joueur.get());
            return joueur.get();
        }

        return  null;
    }

    public void deleteJoueur(String id) {
        joueurRepository.deleteById(id);
    }

    public Joueur gainExperience(String id, int experience) {
        Joueur joueur = joueurRepository.findById(id).orElse(null);
        if (joueur == null) {
            return null;
        }
        joueur.setExperience(joueur.getExperience() + experience);
        if (joueur.getExperience() >= joueur.getExperienceThreshold()) {
            return levelUp(joueur);
        }
        return joueurRepository.save(joueur);
    }

    public Joueur levelUp(Joueur joueur) {
        if (joueur.getLevel() < 50) {
            joueur.setLevel(joueur.getLevel() + 1);
            joueur.setExperience(joueur.getExperience() - joueur.getExperienceThreshold());
            joueur.setExperienceThreshold((int) (joueur.getExperienceThreshold() * 1.1));
            return joueurRepository.save(joueur);
        }
        return joueur;
    }

    public Joueur acquireMonster(String id, String monster) {
        Joueur joueur = joueurRepository.findById(id).orElse(null);
        if (joueur == null) {
            return null;
        }
        int maxMonsters = 10 + joueur.getLevel();
        if (joueur.getMonsters().size() < maxMonsters) {
            joueur.getMonsters().add(monster);
            return joueurRepository.save(joueur);
        }
        return joueur;
    }

    public Joueur removeMonster(String id, String monster) {
        Joueur joueur = joueurRepository.findById(id).orElse(null);
        if (joueur == null) {
            return null;
        }
        joueur.getMonsters().remove(monster);
        return joueurRepository.save(joueur);
    }
}
