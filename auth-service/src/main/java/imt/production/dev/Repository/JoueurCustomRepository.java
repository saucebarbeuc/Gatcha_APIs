package imt.production.dev.Repository;

import imt.production.dev.Dto.JoueurDto;

public interface JoueurCustomRepository {

    void createJoueur(JoueurDto joueur, String token);


}


