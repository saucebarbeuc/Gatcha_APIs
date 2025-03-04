package imt.production.dev.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imt.production.dev.DTO.MonstreDTO;
import imt.production.dev.Model.Monstre;
import imt.production.dev.Service.MonstreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/monstres")
@Tag(name = "Monstres", description = "Gestion des monstres")
public class MonstreController {

    @Autowired
    private MonstreService monstreService;

    @Operation(summary = "Récupérer tous les monstres")
    @GetMapping
    public List<Monstre> getAllMonstres(HttpServletRequest request) {
        // String username = (String) request.getAttribute("username");
        return monstreService.getAllMonstres();
    }

    @Operation(summary = "Créer un nouveau monstre")
    @PostMapping
    public ResponseEntity<Map<String, String>> createMonstre(@Valid @RequestBody MonstreDTO dto, HttpServletRequest request) {
        // String username = (String) request.getAttribute("username");

        return ResponseEntity.ok(monstreService.createMonstre(dto));
    }

    @Operation(summary = "Récupérer un monstre par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<Monstre> getMonstreById(@PathVariable String id) {
        Optional<Monstre> monstre = monstreService.getMonstreById(id);
        return monstre.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Mettre à jour un monstre existant")
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateMonstre(@PathVariable String id, @Valid @RequestBody MonstreDTO dto) {
        return ResponseEntity.ok(monstreService.updateMonstre(id, dto));
    }
    


}




