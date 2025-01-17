package imt.production.dev.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import imt.production.dev.Model.Monstre;

@Repository
public interface MonstreRepository extends MongoRepository<Monstre, Integer> {
    List<Monstre> findByPrixLessThan(int prix);
}