package imt.production.dev.Controller;

import imt.production.dev.Dto.MonstreDto;
import imt.production.dev.Model.MonstreModel;
import imt.production.dev.Service.MonstreService;
import imt.production.dev.Mapper.MonstreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/monstres")
public class MonstreController {

    @Autowired
    private MonstreService monstreService;

    @Autowired
    private MonstreMapper monstreMapper;

    @GetMapping
    public ResponseEntity<List<MonstreDto>> getMonstres() {
        List<MonstreModel> monstreModels = monstreService.getAllMonstres();
        List<MonstreDto> monstresDto = monstreModels.stream()
                .map(monstreMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(monstresDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonstreDto> getMonstre(@PathVariable String id) {
        Optional<MonstreModel> monstre = monstreService.getMonstreById(id);
        return monstre.map(value -> ResponseEntity.ok(monstreMapper.toDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MonstreDto> createMonstre(@RequestBody MonstreDto monstreDto) {
        MonstreModel monstreModel = monstreMapper.toEntity(monstreDto);
        MonstreModel createdMonstreModel = monstreService.createMonstre(monstreModel);
        return ResponseEntity.status(201).body(monstreMapper.toDto(createdMonstreModel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonstreDto> updateMonstre(@PathVariable String id, @RequestBody MonstreDto monstreDto) {
        Optional<MonstreModel> existingMonstre = monstreService.getMonstreById(id);
        if (existingMonstre.isPresent()) {
            MonstreModel updatedMonstreModel = monstreService.updateMonstre(id, monstreMapper.toEntity(monstreDto));
            return ResponseEntity.ok(monstreMapper.toDto(updatedMonstreModel));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonstre(@PathVariable String id) {
        Optional<MonstreModel> existingMonstre = monstreService.getMonstreById(id);
        if (existingMonstre.isPresent()) {
            monstreService.deleteMonstre(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllMonstres() {
        monstreService.deleteAllMonstres();
        return ResponseEntity.noContent().build();
    }
}