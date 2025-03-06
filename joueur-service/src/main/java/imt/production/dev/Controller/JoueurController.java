package imt.production.dev.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import imt.production.dev.DTO.JoueurDTO;
import imt.production.dev.Model.Joueur;
import imt.production.dev.Service.JoueurService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/joueurs")
@Tag(name = "Joueur", description = "API pour gérer les joueurs")
public class JoueurController {

    @Autowired
    private JoueurService joueurService;

    @Operation(summary = "Récupérer tous les joueurs")
    @GetMapping
    public List<Joueur> getAllJoueurs() {
        return joueurService.getAllJoueurs();
    }

    @Operation(summary = "Récupérer un joueur par ID")
    @GetMapping("/{id}")
    public ResponseEntity<Joueur> getJoueurById(@PathVariable String id) {
        Optional<Joueur> joueur = joueurService.getJoueurById(id);
        return joueur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Récupérer le niveau d'un joueur")
    @GetMapping("/level/{id}")
    public ResponseEntity<Integer> getJoueurLevel(@PathVariable String id) {
        Optional<Joueur> joueur = joueurService.getJoueurById(id);
        return joueur.map(j -> ResponseEntity.ok(j.getLevel())).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Créer un nouveau joueur")
    @PostMapping
    // public ResponseEntity<JoueurDTO> createJoueur(@RequestBody JoueurDTO joueur, HttpServletRequest request) {
    public ResponseEntity<JoueurDTO> createJoueur(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        JoueurDTO newJoueur = joueurService.createJoueur(new JoueurDTO(username));
        return ResponseEntity.ok(newJoueur);
    }

    @Operation(summary = "Mettre à jour un joueur")
    @PutMapping("/{id}")
    public ResponseEntity<Joueur> updateJoueur(@PathVariable String id, @RequestBody JoueurDTO joueurDetails) {
        Joueur updatedJoueur = joueurService.updateJoueur(id, joueurDetails);
        if (updatedJoueur != null) {
            return ResponseEntity.ok(updatedJoueur);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Supprimer un joueur")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJoueur(@PathVariable String id) {
        joueurService.deleteJoueur(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Gagner de l'expérience")
    @PostMapping("/{id}/experience")
    public ResponseEntity<Joueur> gainExperience(@PathVariable String id, @RequestParam int experience) {
        Joueur updatedJoueur = joueurService.gainExperience(id, experience);
        if (updatedJoueur != null) {
            return ResponseEntity.ok(updatedJoueur);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Monter de niveau")
    @PostMapping("/{id}/levelup")
    public ResponseEntity<Joueur> levelUp(@PathVariable String id) {
        Joueur joueur = joueurService.getJoueurById(id).orElse(null);
        if (joueur == null) {
            return ResponseEntity.notFound().build();
        }
        Joueur updatedJoueur = joueurService.levelUp(joueur);
        return ResponseEntity.ok(updatedJoueur);
    }

    @Operation(summary = "Acquérir un nouveau monstre")
    @PostMapping("/{id}/monsters")
    public ResponseEntity<Joueur> acquireMonster(@PathVariable String id, @RequestParam String monster) {
        Joueur updatedJoueur = joueurService.acquireMonster(id, monster);
        if (updatedJoueur != null) {
            return ResponseEntity.ok(updatedJoueur);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Supprimer un monstre")
    @DeleteMapping("/{id}/monsters")
    public ResponseEntity<Joueur> removeMonster(@PathVariable String id, @RequestParam String monster) {
        Joueur updatedJoueur = joueurService.removeMonster(id, monster);
        if (updatedJoueur != null) {
            return ResponseEntity.ok(updatedJoueur);
        }
        return ResponseEntity.notFound().build();
    }
}
