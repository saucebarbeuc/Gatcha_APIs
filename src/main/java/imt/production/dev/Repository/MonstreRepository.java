package imt.production.dev.Repository;

import imt.production.dev.Model.Monstre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MonstreRepository extends MongoRepository<Monstre, String> {
}