package imt.production.dev.Service;

import imt.production.dev.Dto.JoueurDto;
import imt.production.dev.Mapper.JoueurMapper;
import imt.production.dev.Model.Joueur;
import imt.production.dev.Repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JoueurService {

    @Autowired
    private JoueurRepository joueurRepository;

    @Autowired
    private JoueurMapper joueurMapper;

    public List<JoueurDto> getAllJoueurs() {
        List<Joueur> joueurs = joueurRepository.findAll();
        return joueurs.stream()
                .map(joueurMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<JoueurDto> getJoueurById(String id) {
        Optional<Joueur> joueur = joueurRepository.findById(id);
        return joueur.map(joueurMapper::toDto);
    }

    public Optional<Integer> getLevelById(String id) {
        Optional<Joueur> joueur = joueurRepository.findById(id);
        return joueur.map(Joueur::getLevel);
    }

    public JoueurDto createJoueur(JoueurDto joueurDto) {
        Joueur joueur = joueurMapper.toEntity(joueurDto);
        joueur.setLevel(0);
        joueur.setExperience(0);
        joueur.setExperienceThreshold(50);
        joueur.setMonstres(new ArrayList<>());
        Joueur savedJoueur = joueurRepository.save(joueur);
        return joueurMapper.toDto(savedJoueur);
    }

    public JoueurDto updateJoueur(String id, JoueurDto joueurDto) {
        Optional<Joueur> optionalJoueur = joueurRepository.findById(id);
        if (optionalJoueur.isPresent()) {
            Joueur joueur = optionalJoueur.get();
            joueur.setName(joueurDto.getNom());
            joueur.setLevel(Math.min(joueurDto.getLevel(), 50));
            joueur.setExperience(joueurDto.getExperience());
            joueur.setExperienceThreshold(joueurDto.getExperienceThreshold());

            int maxMonstres = 10 + joueur.getLevel();
            if (joueurDto.getMonstres().size() > maxMonstres) {
                joueur.setMonstres(joueurDto.getMonstres().subList(0, maxMonstres));
            } else {
                joueur.setMonstres(joueurDto.getMonstres());
            }

            Joueur updatedJoueur = joueurRepository.save(joueur);
            return joueurMapper.toDto(updatedJoueur);
        }
        return null;
    }

    public void deleteJoueur(String id) {
        joueurRepository.deleteById(id);
    }

    public JoueurDto gainExperience(String id, int experience) {
        Optional<Joueur> optionalJoueur = joueurRepository.findById(id);
        if (optionalJoueur.isPresent()) {
            Joueur joueur = optionalJoueur.get();
            joueur.setExperience(joueur.getExperience() + experience);
            if (joueur.getExperience() >= joueur.getExperienceThreshold()) {
                return joueurMapper.toDto(levelUp(joueur));
            }
            return joueurMapper.toDto(joueurRepository.save(joueur));
        }
        return null;
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

    public JoueurDto ajouterMonstre(String id, String monstre) {
        Optional<Joueur> optionalJoueur = joueurRepository.findById(id);
        if (optionalJoueur.isPresent()) {
            Joueur joueur = optionalJoueur.get();
            int maxMonstres = 10 + joueur.getLevel();
            if (joueur.getMonstres().size() < maxMonstres) {
                joueur.getMonstres().add(monstre);
                return joueurMapper.toDto(joueurRepository.save(joueur));
            }
        }
        return null;
    }

    public JoueurDto supprimerMonstre(String id, String monstre) {
        Optional<Joueur> optionalJoueur = joueurRepository.findById(id);
        if (optionalJoueur.isPresent()) {
            Joueur joueur = optionalJoueur.get();
            joueur.getMonstres().remove(monstre);
            return joueurMapper.toDto(joueurRepository.save(joueur));
        }
        return null;
    }
}
