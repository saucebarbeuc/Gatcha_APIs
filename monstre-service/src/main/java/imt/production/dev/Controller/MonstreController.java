package imt.production.dev.Controller;

import imt.production.dev.Dto.MonstreDto;
import imt.production.dev.Service.MonstreService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/monstres")
@Tag(name = "Monstres", description = "Gestion des monstres")
public class MonstreController {

    private final MonstreService monstreService;

    public MonstreController(MonstreService monstreService) {
        this.monstreService = monstreService;
    }

    @GetMapping
    public ResponseEntity<List<MonstreDto>> getMonstres() {
        return ResponseEntity.ok(monstreService.getAllMonstres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonstreDto> getMonstre(@PathVariable String id) {
        return monstreService.getMonstreById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MonstreDto> createMonstre(@RequestBody MonstreDto monstreDto) {
        return ResponseEntity.status(201).body(monstreService.createMonstre(monstreDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonstreDto> updateMonstre(@PathVariable String id, @RequestBody MonstreDto monstreDto) {
        return ResponseEntity.ok(monstreService.updateMonstre(id, monstreDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonstre(@PathVariable String id) {
        monstreService.deleteMonstre(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllMonstres() {
        monstreService.deleteAllMonstres();
        return ResponseEntity.noContent().build();
    }
}