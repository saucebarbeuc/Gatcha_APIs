package imt.production.dev.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import imt.production.dev.Model.ResourceMonstre.MonstreResource;
import imt.production.dev.Repository.MonstreResourceRepository;

@SpringBootTest
public class CalculInvocationTest {

    @Autowired
    MonstreResourceRepository ressourceRepository;

    @Test
	void choseTest() {
        int n = 10000;
        double delta_erreur = 1.5; // en pourcentage

        List<MonstreResource> resourceMonstres = ressourceRepository.findAllMonstres();

        Map<Map<Integer, Double>, List<MonstreResource>> proba = new HashMap<>();

        for (int i = 0; i < n; i++) {
        
            MonstreResource m = resourceMonstres.get(CalculInvocation.chose(resourceMonstres));

            Map<Integer, Double> key = Map.of(m.get_id(), m.getLootRate());


            if (!proba.containsKey(key)) {

                proba.put(key, new ArrayList<>());
                
            }

            proba.get(key).add(m);
        }

        assertEquals(proba.keySet().size(), 4);
        assertEquals(resourceMonstres.size(), 4);

        for (MonstreResource current : resourceMonstres) {
            Map<Integer, Double> key = Map.of(current.get_id(), current.getLootRate());

            assertEquals(current.getLootRate() * n, proba.get(key).size(), n * delta_erreur / 100, "Monstre " + current.get_id() + " de proba " + current.getLootRate() + " est apparu " + proba.get(key).size() + " fois.");

            // Voir nombre de monstre par proba
            // System.out.println(proba.get(key).size());

        }

    }
    
    
}
