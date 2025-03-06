package imt.production.dev.Service;

import imt.production.dev.Dto.JoueurDto;
import imt.production.dev.DTO.MonstreDTO;
import imt.production.dev.DTO.UtilisateurDTO;
import imt.production.dev.Errors.HTTP_409.UtilisateurDejaExistantException;
import imt.production.dev.Model.Joueur;
import imt.production.dev.Repository.JoueurRepository;
import imt.production.dev.Repository.MonstreCustomRepository;
import imt.production.dev.Repository.MonstreRemoteRepository;
import imt.production.dev.Repository.UtilisateurCustomRepository;
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
    @Autowired
    private UtilisateurCustomRepository utilisateurCustomRepository;

    public List<Joueur> getAllJoueurs() {
        return joueurRepository.findAll();
    }

    public Optional<Joueur> getJoueurById(String id) {
        return joueurRepository.findById(id);
    }

    public Optional<List<String>> getJoueurMonstersById(String id) {
        return joueurRepository.findById(id)
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

    public Joueur updateJoueur(String id, JoueurDto dto) {
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

    public Joueur acquireMonster(String monsterId, String token) {
        String userName = utilisateurCustomRepository.getUtilisateurNameByToken(token);
        if (userName == null) {
            return null;
        }
        Joueur joueur = joueurRepository.findByName(userName).orElse(null);
        if (joueur == null) {
            return null;
        }
        int maxMonsters = 10 + joueur.getLevel();
        String monstreName = monstreCustomRepository.monstreExist(monsterId, token);
        if (monstreName == null) {
            return null;
        }
        if (joueur.getMonsters().size() < maxMonsters) {
            joueur.getMonsters().add(monsterId);
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
