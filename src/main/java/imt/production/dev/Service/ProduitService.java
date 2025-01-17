package imt.production.dev.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

import imt.production.dev.Model.Produit;
import imt.production.dev.Repository.ProduitRepository;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Optional<Produit> getProduitById(int id) {
        return produitRepository.findById(id);
    }

    public List<Produit> getProduitsByPrixLessThan(int prix) {
        return produitRepository.findByPrixLessThan(prix);
    }

    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public void deleteProduitById(int id) {
        produitRepository.deleteById(id);
    }
}
