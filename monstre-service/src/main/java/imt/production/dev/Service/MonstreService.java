package imt.production.dev.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.Map;

import imt.production.dev.Dto.MonstreDto;
import imt.production.dev.Errors.HTTP_404.MonstreIdNonExistantException;
import imt.production.dev.Model.Monstre;
import imt.production.dev.Repository.MonstreRepository;

@Service
public class MonstreService {

    @Autowired
    private MonstreRepository monstreRepository;

    public List<Monstre> getAllMonstres() {
        return monstreRepository.findAll();
    }

    public Map<String, String> createMonstre(MonstreDto dto) {
        Monstre monstre = monstreRepository.save(new Monstre(dto.getNom(), dto.getDescription()));
        return Map.of("id", monstre.getId());
    }

    public Optional<Monstre> getMonstreById(String id) {
        return monstreRepository.findById(id);
    }

    public Map<String, String> updateMonstre(String id, MonstreDto dto) {
        Optional<Monstre> monstre = monstreRepository.findById(id);
        
        if (!monstre.isPresent()) {
            throw new MonstreIdNonExistantException("L'id donné ne correspond à aucun monstre.");
        }

        monstre.get().setNom(dto.getNom());
        monstre.get().setDescription(dto.getNom());

        monstreRepository.save(monstre.get());
        return Map.of("id", monstre.get().getId());
    }

}
