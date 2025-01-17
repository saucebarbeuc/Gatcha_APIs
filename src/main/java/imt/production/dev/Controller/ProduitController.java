package imt.production.dev.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imt.production.dev.Model.Produit;
import imt.production.dev.Service.ProduitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produits")
@Tag(name = "Produits", description = "Gestion des produits")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    // Get all products
    @Operation(summary = "Récupérer tous les produits")
    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    // Create a new product
    @Operation(summary = "Créer un nouveau produit")
    @PostMapping
    public ResponseEntity<Produit> createProduit(@Valid @RequestBody Produit produit) {
        Produit createdProduit = produitService.createProduit(produit);
        return ResponseEntity.ok(createdProduit);
    }

    @Operation(summary = "Récupérer un produit par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable int id) {
        Optional<Produit> produit = produitService.getProduitById(id);
        return produit.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Update an existing product
    @Operation(summary = "Mettre à jour un produit existant")
    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable int id, @Valid @RequestBody Produit updatedProduit) {
        return produitService.getProduitById(id).map(existingProduit -> {
            existingProduit.setNom(updatedProduit.getNom());
            existingProduit.setDescription(updatedProduit.getDescription());
            existingProduit.setPrix(updatedProduit.getPrix());
            Produit savedProduit = produitService.createProduit(existingProduit);
            return ResponseEntity.ok(savedProduit);
        }).orElse(ResponseEntity.notFound().build());
    }
    
    // Delete a product by ID
    @Operation(summary = "Supprimer un produit par son ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable int id) {
        Optional<Produit> produit = produitService.getProduitById(id);
        if (produit.isPresent()) {
            produitService.deleteProduitById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Get products with price less than a certain value
    @Operation(summary = "Recupere les produits dont le prix est inferieur a une certaine valeur")
    @GetMapping("/prix/{maxPrix}")
    public List<Produit> getProduitsByPrix(@PathVariable int maxPrix) {
        return produitService.getProduitsByPrixLessThan(maxPrix);
    }
    

 
}




