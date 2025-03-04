package imt.production.dev.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import imt.production.dev.Model.Monstre;

@Repository
public interface MonstreRepository extends MongoRepository<Monstre, Integer> {

    Optional<Monstre> findById(String id);

    void deleteById(String id);

    boolean existsById(String id);
}