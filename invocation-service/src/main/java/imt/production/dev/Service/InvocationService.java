package imt.production.dev.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import imt.production.dev.Dto.MonstreUtils.MonstreDto;
import imt.production.dev.Mapper.RessourceMapper;
import imt.production.dev.Model.InvocationBackup;
import imt.production.dev.Model.ResourceMonstre.MonstreResource;
import imt.production.dev.Repository.BackupRepository;
import imt.production.dev.Repository.InvocationRepository;
import imt.production.dev.Repository.MonstreResourceRepository;
import imt.production.dev.Repository.Remote.JoueurHttp;
import imt.production.dev.Repository.Remote.MonstreHttp;
import imt.production.dev.Utils.CalculInvocation;

@Service
public class InvocationService {

    private MonstreHttp monstreHttp;
    private JoueurHttp joueurHttp;
    private BackupRepository backupRepository;
    private InvocationRepository invocationRepository;
    private MonstreResourceRepository ressourceRepository;

    public InvocationService(MonstreHttp monstreHttp, JoueurHttp joueurHttp, BackupRepository backupRepository, InvocationRepository invocationRepository, MonstreResourceRepository ressourceRepository){
        this.ressourceRepository = ressourceRepository;
        this.monstreHttp = monstreHttp;
        this.joueurHttp = joueurHttp;
        this.backupRepository = backupRepository;
        this.invocationRepository = invocationRepository;
    }

    public MonstreDto invoque(String token, String username) {
        List<MonstreResource> resourceMonstres = ressourceRepository.findAllMonstres();

        InvocationBackup backup = new InvocationBackup();
        backup.setUsername(username);
        backup.setCalcul(CalculInvocation.chose(resourceMonstres));

        MonstreDto monstreDto = RessourceMapper.toDto(resourceMonstres.get(backup.getCalcul()));

        try {
            // peut lever une exception
            String idMonstre = monstreHttp.create(monstreDto, token);

            backup.setIdMonstre(idMonstre);

            // peut lever une exception
            joueurHttp.acquireMonstre(idMonstre, token);

            invocationRepository.save(backup);
            return monstreDto;
        } catch (RuntimeException e) {
            backupRepository.save(backup);
            throw new RuntimeException(e.getMessage() + ", Backup saved.");
        }
    }

    public List<MonstreDto> recup(String token, String username) {
        List<MonstreResource> resourceMonstres = ressourceRepository.findAllMonstres();
        List<InvocationBackup> backups = backupRepository.findByUsername(username);
        List<MonstreDto> sumoned = new ArrayList<>();

        for (InvocationBackup backup : backups) {
            String idMonstre = backup.getIdMonstre();
            MonstreDto monstreDto = RessourceMapper.toDto(resourceMonstres.get(backup.getCalcul()));

            if(idMonstre == null) {
                
                idMonstre = monstreHttp.create(monstreDto, token);
                backup.setIdMonstre(idMonstre);
                
            }

            joueurHttp.acquireMonstre(idMonstre, token);

            invocationRepository.save(backup);
            backupRepository.deleteById(backup.getId());

            sumoned.add(monstreDto);
        }
        return sumoned;
    }
    
    // public List<MonstreResource> data() {
    //     return ressourceRepository.findAllMonstres();
    // }

}
