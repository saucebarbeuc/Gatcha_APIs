package imt.production.dev.Controller;

import imt.production.dev.Dto.MonstreDto;
import imt.production.dev.Service.MonstreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/monstres")
@Tag(name = "Monstres", description = "Gestion des monstres")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*", allowCredentials = "true")
public class MonstreController {

    private final MonstreService monstreService;

    public MonstreController(MonstreService monstreService) {
        this.monstreService = monstreService;
    }

    @GetMapping
    @Operation(summary = "Get All Monstres")
    public ResponseEntity<List<MonstreDto>> getMonstres() {
        return ResponseEntity.ok(monstreService.getAllMonstres());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get By Id")
    public ResponseEntity<MonstreDto> getMonstre(@PathVariable String id) {
        return monstreService.getMonstreById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create")
    public ResponseEntity<Map<String, String>> createMonstre(@RequestBody MonstreDto monstreDto) {
        return ResponseEntity.ok(monstreService.createMonstre(monstreDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update")
    public ResponseEntity<MonstreDto> updateMonstre(@PathVariable String id, @RequestBody MonstreDto monstreDto) {
        return ResponseEntity.ok(monstreService.updateMonstre(id, monstreDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete")
    public ResponseEntity<Void> deleteMonstre(@PathVariable String id) {
        monstreService.deleteMonstre(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete All")
    public ResponseEntity<Void> deleteAllMonstres() {
        monstreService.deleteAllMonstres();
        return ResponseEntity.noContent().build();
    }
}