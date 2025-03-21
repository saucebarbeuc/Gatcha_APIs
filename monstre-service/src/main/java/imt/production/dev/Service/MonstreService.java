package imt.production.dev.Service;

import imt.production.dev.Dto.MonstreDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MonstreService {
    Map<String, String> createMonstre(MonstreDto monstre);
    List<MonstreDto> getAllMonstres();
    Optional<MonstreDto> getMonstreById(String id);
    MonstreDto updateMonstre(String id, MonstreDto monstreDetails);
    void deleteMonstre(String id);
    void deleteAllMonstres();
}