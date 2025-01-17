package imt.production.dev.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import imt.production.dev.Model.Produit;

@Repository
public interface ProduitRepository extends MongoRepository<Produit, Integer> {
    List<Produit> findByPrixLessThan(int prix);
}