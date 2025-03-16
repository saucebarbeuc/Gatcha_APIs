package imt.production.dev.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import imt.production.dev.Dto.JoueurDto;
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

    @Operation(summary = "Récupérer un joueur par Name")
    @GetMapping
    public ResponseEntity<Joueur> getJoueurByName(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        Optional<Joueur> joueur = joueurService.getJoueurByName(username);
        return joueur
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Récupérer la liste d'ID de monstres")
    @GetMapping("/monsters")
    public ResponseEntity<List<String>> getMonstersByName(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        Optional<List<String>> monsters = joueurService.getJoueurMonstersByName(username);
        return monsters.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Récupérer le niveau d'un joueur")
    @GetMapping("/level")
    public ResponseEntity<Integer> getJoueurLevel(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        Optional<Joueur> joueur = joueurService.getJoueurByName(username);
        return joueur.map(j -> ResponseEntity.ok(j.getLevel())).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @Operation(summary = "Créer un nouveau joueur")
    @PostMapping
    public ResponseEntity<JoueurDto> createJoueur(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        JoueurDto newJoueur = joueurService.createJoueur(new JoueurDto(username));
        return ResponseEntity.ok(newJoueur);
    }

    @Operation(summary = "Mettre à jour un joueur")
    @PutMapping
    public ResponseEntity<Joueur> updateJoueur(HttpServletRequest request, @RequestBody JoueurDto joueurDetails) {
        String username = (String) request.getAttribute("username");
        Joueur updatedJoueur = joueurService.updateJoueur(username, joueurDetails);
        if (updatedJoueur != null) {
            return ResponseEntity.ok(updatedJoueur);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Supprimer un joueur")
    @DeleteMapping
    public ResponseEntity<Void> deleteJoueur(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        joueurService.deleteJoueur(username);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Gagner de l'expérience")
    @PostMapping("/experience")
    public ResponseEntity<Joueur> gainExperience(HttpServletRequest request, @RequestParam int experience) {
        String username = (String) request.getAttribute("username");
        Joueur updatedJoueur = joueurService.gainExperience(username, experience);
        if (updatedJoueur != null) {
            return ResponseEntity.ok(updatedJoueur);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Monter de niveau")
    @PostMapping("/levelup")
    public ResponseEntity<Joueur> levelUp(HttpServletRequest request) {
        String username = (String) request.getAttribute("username");
        Joueur joueur = joueurService.getJoueurByName(username).orElse(null);
        if (joueur == null) {
            return ResponseEntity.notFound().build();
        }
        Joueur updatedJoueur = joueurService.levelUp(joueur);
        return ResponseEntity.ok(updatedJoueur);
    }

    @Operation(summary = "Acquérir un nouveau monstre")
    @PostMapping("/monsters")
    public ResponseEntity<JoueurDto> acquireMonster(@RequestParam String monster, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String username = (String) request.getAttribute("username");
        JoueurDto updatedJoueur = joueurService.acquireMonster(monster, token, username);
        if (updatedJoueur != null) {
            return ResponseEntity.ok(updatedJoueur);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Supprimer un monstre")
    @DeleteMapping("/monsters")
    public ResponseEntity<Joueur> removeMonster(HttpServletRequest request, @RequestParam String monster) {
        String username = (String) request.getAttribute("username");
        Joueur updatedJoueur = joueurService.removeMonster(username,monster);
        if (updatedJoueur != null) {
            return ResponseEntity.ok(updatedJoueur);
        }
        return ResponseEntity.notFound().build();
    }
}
