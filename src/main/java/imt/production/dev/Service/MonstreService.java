package imt.production.dev.Service;

import imt.production.dev.Model.MonstreModel;
import imt.production.dev.Repository.MonstreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonstreService {

    @Autowired
    private MonstreRepository monstreRepository;

    public MonstreModel createMonstre(MonstreModel monstreModel) {
        return monstreRepository.save(monstreModel);
    }

    public List<MonstreModel> getAllMonstres() {
        return monstreRepository.findAll();
    }

    public Optional<MonstreModel> getMonstreById(String id) {
        return monstreRepository.findById(id);
    }

    public MonstreModel updateMonstre(String id, MonstreModel monstreModelDetails) {
        Optional<MonstreModel> monstre = monstreRepository.findById(id);
        if (monstre.isPresent()) {
            MonstreModel updatedMonstreModel = monstre.get();
            updatedMonstreModel.setNom(monstreModelDetails.getNom());
            updatedMonstreModel.getStats().setPv(monstreModelDetails.getStats().getPv());
            updatedMonstreModel.getStats().setAtq(monstreModelDetails.getStats().getAtq());
            return monstreRepository.save(updatedMonstreModel);
        }
        return null;
    }

    public void deleteMonstre(String id) {
        monstreRepository.deleteById(id);
    }

    public void deleteAllMonstres() {
        monstreRepository.deleteAll();
    }
}