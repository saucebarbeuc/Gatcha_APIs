package imt.production.dev.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import imt.production.dev.Model.Utilisateur;

@Repository
public interface UtilisateurRepository extends MongoRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findById(String id);
    void deleteById(String id);

    Optional<Utilisateur> findByUsernameAndPassword(String username, String password);
    Optional<Utilisateur> findByToken(String token);
    
    boolean existsByUsername(String username);
    boolean existsById(String id);
    void deleteByUsername(String username);
}


