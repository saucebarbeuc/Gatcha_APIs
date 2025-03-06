package imt.production.dev.Service;

import imt.production.dev.Model.Competence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CompetenceService {

    private final Random random = new Random();

    public void ameliorerCompetenceAleatoire(List<Competence> competences) {
        choisirCompetenceValide(competences)
                .ifPresent(Competence::ameliorer);
    }

    private Optional<Competence> choisirCompetenceValide(List<Competence> competences) {
        List<Competence> competencesValides = competences.stream()
                .filter(c -> !c.estNiveauMax())
                .collect(Collectors.toList());

        if (competencesValides.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(competencesValides.get(random.nextInt(competencesValides.size())));
    }
}