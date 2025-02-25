package imt.production.dev.Service;

import imt.production.dev.Model.CompetenceModel;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CompetenceService {

    public void ameliorerCompetenceAleatoire(List<CompetenceModel> competences) {
        CompetenceModel competenceAAugmenter = choisirCompetenceValide(competences);

        if (competenceAAugmenter != null) {
            competenceAAugmenter.ameliorer();
        }
    }

    private CompetenceModel choisirCompetenceValide(List<CompetenceModel> competences) {
        Random random = new Random();
        List<CompetenceModel> competencesValides = competences.stream()
                .filter(c -> !c.estNiveauMax())
                .collect(Collectors.toList());

        if (competencesValides.isEmpty()) {
            return null;
        }

        return competencesValides.get(random.nextInt(competencesValides.size()));
    }
}