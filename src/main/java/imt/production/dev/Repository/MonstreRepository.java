package imt.production.dev.Repository;

import imt.production.dev.Model.MonstreModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface MonstreRepository extends MongoRepository<MonstreModel, Integer> {
    Optional<MonstreModel> findById(String id);
    void deleteById(String id);
}