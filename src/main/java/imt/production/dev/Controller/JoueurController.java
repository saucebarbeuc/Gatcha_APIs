package imt.production.dev.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import imt.production.dev.Dto.JoueurDto;
import imt.production.dev.Model.Joueur;
import imt.production.dev.Service.JoueurService;
import imt.production.dev.Mapper.JoueurMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/joueurs")
@Tag(name = "Joueur", description = "API pour gérer les joueurs")
public class JoueurController {

    @Autowired
    private JoueurService joueurService;

    @Autowired
    private JoueurMapper joueurMapper;

    @Operation(summary = "Récupérer tous les joueurs")
    @GetMapping
    public List<JoueurDto> getAllJoueurs() {
        return joueurService.getAllJoueurs();
    }

    @Operation(summary = "Récupérer un joueur par ID")
    @GetMapping("/{id}")
    public ResponseEntity<JoueurDto> getJoueurById(@PathVariable String id) {
        Optional<JoueurDto> joueur = joueurService.getJoueurById(id);
        return joueur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Récupérer le niveau d'un joueur")
    @GetMapping("/{id}/level")
    public ResponseEntity<JoueurDto> getLevelById(@PathVariable String id) {
        Optional<Integer> joueur = joueurService.getLevelById(id);
        if (joueur != null) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }


    @Operation(summary = "Créer un nouveau joueur")
    @PostMapping
    public JoueurDto createJoueur(@RequestBody JoueurDto joueur) {
        return joueurService.createJoueur(joueur);
    }

    @Operation(summary = "Mettre à jour un joueur")
    @PutMapping("/{id}")
    public ResponseEntity<JoueurDto> updateJoueur(@PathVariable String id, @RequestBody JoueurDto joueurDetails) {
        JoueurDto updatedJoueur = joueurService.updateJoueur(id, joueurDetails);
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
    public ResponseEntity<JoueurDto> gainExperience(@PathVariable String id, @RequestParam int experience) {
        JoueurDto updatedJoueur = joueurService.gainExperience(id, experience);
        if (updatedJoueur != null) {
            return ResponseEntity.ok(updatedJoueur);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Monter de niveau")
    @PostMapping("/{id}/levelup")
    public ResponseEntity<JoueurDto> levelUp(@PathVariable String id) {
        Optional<JoueurDto> joueurDto = joueurService.getJoueurById(id);
        if (joueurDto.isPresent()) {
            Joueur joueur = joueurMapper.toEntity(joueurDto.get());
            JoueurDto updatedJoueur = joueurMapper.toDto(joueurService.levelUp(joueur));
            return ResponseEntity.ok(updatedJoueur);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Acquérir un nouveau monstre")
    @PostMapping("/{id}/monstre")
    public ResponseEntity<JoueurDto> ajouterMonstre(@PathVariable String id, @RequestParam String monstre) {
        JoueurDto updatedJoueur = joueurService.ajouterMonstre(id, monstre);
        if (updatedJoueur != null) {
            return ResponseEntity.ok(updatedJoueur);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Supprimer un monstre")
    @DeleteMapping("/{id}/monstre")
    public ResponseEntity<JoueurDto> supprimerMonstre(@PathVariable String id, @RequestParam String monstre) {
        JoueurDto updatedJoueur = joueurService.supprimerMonstre(id, monstre);
        if (updatedJoueur != null) {
            return ResponseEntity.ok(updatedJoueur);
        }
        return ResponseEntity.notFound().build();
    }
}
