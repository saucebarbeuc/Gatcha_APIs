package imt.production.dev.Repository;

import imt.production.dev.DTO.JoueurDTO;

public interface JoueurCustomRepository {

    void createJoueur(JoueurDTO joueur, String token);


}


