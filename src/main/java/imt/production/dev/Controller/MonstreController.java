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

import imt.production.dev.Model.Monstre;
import imt.production.dev.Service.MonstreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/monstres")
@Tag(name = "Monstres", description = "Gestion des monstres")
public class MonstreController {

    @Autowired
    private MonstreService monstreService;

    // Get all products
    @Operation(summary = "Récupérer tous les monstres")
    @GetMapping
    public List<Monstre> getAllMonstres() {
        return monstreService.getAllMonstres();
    }

    // Create a new product
    @Operation(summary = "Créer un nouveau monstre")
    @PostMapping
    public ResponseEntity<Monstre> createMonstre(@Valid @RequestBody Monstre monstre) {
        Monstre createdMonstre = monstreService.createMonstre(monstre);
        return ResponseEntity.ok(createdMonstre);
    }

    @Operation(summary = "Récupérer un monstre par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<Monstre> getMonstreById(@PathVariable int id) {
        Optional<Monstre> monstre = monstreService.getMonstreById(id);
        return monstre.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Update an existing product
    @Operation(summary = "Mettre à jour un monstre existant")
    @PutMapping("/{id}")
    public ResponseEntity<Monstre> updateMonstre(@PathVariable int id, @Valid @RequestBody Monstre updatedMonstre) {
        return monstreService.getMonstreById(id).map(existingMonstre -> {
            existingMonstre.setNom(updatedMonstre.getNom());
            existingMonstre.setDescription(updatedMonstre.getDescription());
            existingMonstre.setPrix(updatedMonstre.getPrix());
            Monstre savedMonstre = monstreService.createMonstre(existingMonstre);
            return ResponseEntity.ok(savedMonstre);
        }).orElse(ResponseEntity.notFound().build());
    }
    
    // Delete a product by ID
    @Operation(summary = "Supprimer un monstre par son ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonstre(@PathVariable int id) {
        Optional<Monstre> monstre = monstreService.getMonstreById(id);
        if (monstre.isPresent()) {
            monstreService.deleteMonstreById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Get products with price less than a certain value
    @Operation(summary = "Recupere les monstres dont le prix est inferieur a une certaine valeur")
    @GetMapping("/prix/{maxPrix}")
    public List<Monstre> getMonstresByPrix(@PathVariable int maxPrix) {
        return monstreService.getMonstresByPrixLessThan(maxPrix);
    }
    

 
}




