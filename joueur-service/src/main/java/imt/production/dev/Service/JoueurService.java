package imt.production.dev.Service;

import imt.production.dev.Dto.JoueurDto;
import imt.production.dev.Errors.HTTP_409.UtilisateurDejaExistantException;
import imt.production.dev.Model.Joueur;
import imt.production.dev.Repository.JoueurRepository;
import imt.production.dev.Repository.MonstreCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JoueurService {

    @Autowired
    private JoueurRepository joueurRepository;
    @Autowired
    private MonstreCustomRepository monstreCustomRepository;

    public List<Joueur> getAllJoueurs() {
        return joueurRepository.findAll();
    }

    public Optional<Joueur> getJoueurById(String id) {
        return joueurRepository.findById(id);
    }

    public Optional<Joueur> getJoueurByName(String name) {
        return joueurRepository.findByName(name);
    }

    public Optional<List<String>> getJoueurMonstersByName(String username) {
        return joueurRepository.findByName(username)
                .map(joueur -> Optional.ofNullable(joueur.getMonsters())
                        .orElse(List.of()));
    }
    
    public JoueurDto createJoueur(JoueurDto dto) {
        Joueur joueur = new Joueur();
        joueur.setName(dto.getUsername());
        joueur.setLevel(1);
        joueur.setExperience(0);
        joueur.setExperienceThreshold(50);
        joueur.setMonsters(new ArrayList<>());
        
        if (joueurRepository.existsByName(dto.getUsername())) {
            throw new UtilisateurDejaExistantException("Joueur déjà existant");
        }

        return new JoueurDto(joueurRepository.save(joueur).getName());
    }

    public Joueur updateJoueur(String username, JoueurDto dto) {
        Optional<Joueur> joueur = joueurRepository.findByName(username);

        if (joueur.isPresent()) {
            joueur.get().setName(dto.getUsername());
            joueurRepository.save(joueur.get());
            return joueur.get();
        }

        return  null;
    }

    public void deleteJoueur(String username) {
        joueurRepository.deleteByName(username);
    }

    public Joueur gainExperience(String username, int experience) {
        Joueur joueur = joueurRepository.findByName(username).orElse(null);
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

    public JoueurDto acquireMonster(String monsterId, String token, String username) {
        // String userName = utilisateurCustomRepository.getUtilisateurNameByToken(token);
        Joueur joueur = joueurRepository.findByName(username).orElse(null);
        if (joueur == null) {
            return null;
        }
        int maxMonsters = 10 + joueur.getLevel();
        String monstreId = monstreCustomRepository.monstreExist(monsterId, token);
        if (joueur.getMonsters().size() < maxMonsters) {
            joueur.getMonsters().add(monstreId);
            // return new JoueurDto(username);
            return new JoueurDto(joueurRepository.save(joueur).getName()); // TODO verif username = null
        }
        return new JoueurDto(joueur.getName());
    }

    public Joueur removeMonster(String username, String monster) {
        Joueur joueur = joueurRepository.findByName(username).orElse(null);
        if (joueur == null) {
            return null;
        }
        joueur.getMonsters().remove(monster);
        return joueurRepository.save(joueur);
    }
}
