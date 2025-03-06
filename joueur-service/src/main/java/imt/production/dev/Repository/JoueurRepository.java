package imt.production.dev.Repository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import imt.production.dev.Model.Joueur;

@Repository
public interface JoueurRepository extends MongoRepository<Joueur, String> {
    Optional<Joueur> findByName(String name);
    void deleteById(String id);
    boolean existsByName(String username);

    List<Joueur> id(@NotBlank(message = "Identifiant unique du joueur") String id);
}