package imt.production.dev.Mapper;

import imt.production.dev.Dto.JoueurDto;
import imt.production.dev.Model.Joueur;
import org.springframework.stereotype.Component;

@Component
public class JoueurMapper {

    public JoueurDto toDto(Joueur joueur) {
        if (joueur == null) {
            return null;
        }
        return new JoueurDto(
                joueur.getId(),
                joueur.getName(),
                joueur.getLevel(),
                joueur.getExperience(),
                joueur.getExperienceThreshold(),
                joueur.getMonstres()
        );
    }

    public Joueur toEntity(JoueurDto joueurDto) {
        if (joueurDto == null) {
            return null;
        }
        Joueur joueur = new Joueur();
        joueur.setId(joueurDto.getId());
        joueur.setName(joueurDto.getNom());
        joueur.setLevel(joueurDto.getLevel());
        joueur.setExperience(joueurDto.getExperience());
        joueur.setExperienceThreshold(joueurDto.getExperienceThreshold());
        joueur.setMonstres(joueurDto.getMonstres());
        return joueur;
    }
}
