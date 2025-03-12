package imt.production.dev.Utils;

import java.util.List;
import java.util.Random;

import imt.production.dev.Model.ResourceMonstre.MonstreResource;

public class CalculInvocation {

    public static int chose(List<MonstreResource> monstres) {

        Random rand = new Random();
        double chance = rand.nextDouble();
        double somme = 0.0;

        for (MonstreResource monstre : monstres) {
            somme += monstre.getLootRate();
            if (chance < somme) {
                return monstre.get_id() - 1;
            }
        }

        return -1;
    } 


}
