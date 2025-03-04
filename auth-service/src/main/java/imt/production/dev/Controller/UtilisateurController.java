package imt.production.dev.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import imt.production.dev.DTO.UtilisateurDTO;
// import imt.production.dev.Model.Utilisateur;
import imt.production.dev.Service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Utilisateurs", description = "Gestion des utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody UtilisateurDTO dto) {
        Map<String, String> response = utilisateurService.login(dto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/validate")
    public ResponseEntity<Map<String, String>> validate(@RequestParam String token) {
        Map<String, String> response = utilisateurService.validate(token);
        return ResponseEntity.ok().body(response);
    }

    // @Operation(summary = "Récupérer tous les utilisateurs")
    // @GetMapping
    // public List<Utilisateur> getAllUtilisateurs() {
    //     return utilisateurService.getAllUtilisateurs();
    // }

    @Operation(summary = "Créer un nouveau utilisateur")
    @PostMapping
    public ResponseEntity<Map<String, String>> createUtilisateur(@Valid @RequestBody UtilisateurDTO dto) {
        return ResponseEntity.ok(utilisateurService.createUtilisateur(dto));
    }

    @Operation(summary = "Supprimer un utilisateur par son ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable String id) {
        utilisateurService.deleteUtilisateurById(id);
        return ResponseEntity.ok().build();
    }

}
