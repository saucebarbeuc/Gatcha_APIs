package imt.production.dev.Service.impl;

import imt.production.dev.Dto.MonstreDto;
import imt.production.dev.Mapper.MonstreMapper;
import imt.production.dev.Model.Monstre;
import imt.production.dev.Repository.MonstreRepository;
import imt.production.dev.Service.MonstreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MonstreServiceImpl implements MonstreService {

    private final MonstreRepository monstreRepository;

    public MonstreServiceImpl(MonstreRepository monstreRepository) {
        this.monstreRepository = monstreRepository;
    }

    @Override
    public MonstreDto createMonstre(MonstreDto monstreDto) {
        Monstre monstre = MonstreMapper.toEntity(monstreDto);
        Monstre savedMonstre = monstreRepository.save(monstre);
        return MonstreMapper.toDto(savedMonstre);
    }

    @Override
    public List<MonstreDto> getAllMonstres() {
        return monstreRepository.findAll()
                .stream()
                .map(MonstreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MonstreDto> getMonstreById(String id) {
        return monstreRepository.findById(id)
                .map(MonstreMapper::toDto);
    }

    @Override
    public MonstreDto updateMonstre(String id, MonstreDto monstreDetails) {
        return monstreRepository.findById(id).map(monstre -> {
            monstre.setNom(monstreDetails.getNom());
            monstre.getStats().setPv(monstreDetails.getStats().getPv());
            monstre.getStats().setAtq(monstreDetails.getStats().getAtq());

            Monstre updatedMonstre = monstreRepository.save(monstre);
            return MonstreMapper.toDto(updatedMonstre);
        }).orElseThrow(() -> new RuntimeException("Monstre non trouvé avec l'ID : " + id));
    }

    @Override
    public void deleteMonstre(String id) {
        if (!monstreRepository.existsById(id)) {
            throw new RuntimeException("Monstre non trouvé avec l'ID : " + id);
        }
        monstreRepository.deleteById(id);
    }

    @Override
    public void deleteAllMonstres() {
        monstreRepository.deleteAll();
    }
}