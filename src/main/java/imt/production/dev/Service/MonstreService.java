package imt.production.dev.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

import imt.production.dev.Model.Monstre;
import imt.production.dev.Repository.MonstreRepository;

@Service
public class MonstreService {

    @Autowired
    private MonstreRepository monstreRepository;

    public Monstre createMonstre(Monstre monstre) {
        return monstreRepository.save(monstre);
    }

    public Optional<Monstre> getMonstreById(int id) {
        return monstreRepository.findById(id);
    }

    public List<Monstre> getMonstresByPrixLessThan(int prix) {
        return monstreRepository.findByPrixLessThan(prix);
    }

    public List<Monstre> getAllMonstres() {
        return monstreRepository.findAll();
    }

    public void deleteMonstreById(int id) {
        monstreRepository.deleteById(id);
    }
}
