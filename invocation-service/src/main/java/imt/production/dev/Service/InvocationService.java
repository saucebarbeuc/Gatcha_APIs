package imt.production.dev.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import imt.production.dev.Dto.InvocationDto;
import imt.production.dev.Mapper.InvocationMapper;
import imt.production.dev.Model.InvocationBackup;
import imt.production.dev.Repository.BackupRepository;
import imt.production.dev.Repository.InvocationRepository;
import imt.production.dev.Repository.Remote.JoueurHttp;
import imt.production.dev.Repository.Remote.MonstreHttp;

@Service
public class InvocationService {

    private MonstreHttp monstreHttp;
    private JoueurHttp joueurHttp;
    private BackupRepository backupRepository;
    private InvocationRepository invocationRepository;

    public InvocationService(MonstreHttp monstreHttp, JoueurHttp joueurHttp, BackupRepository backupRepository, InvocationRepository invocationRepository){
        this.monstreHttp = monstreHttp;
        this.joueurHttp = joueurHttp;
        this.backupRepository = backupRepository;
        this.invocationRepository = invocationRepository;
    }

    public Map<String, String> invoque(InvocationDto invocationDto, String token, String username) {
        InvocationBackup backup = new InvocationBackup();
        backup.setUsername(username);
        backup.setSavedInvocation(InvocationMapper.dtoToModel(invocationDto));
        backup.setCalcul(0);

        try {
            // peut lever une exception
            String idMonstre = monstreHttp.create(invocationDto.getMonstreDto(), token);

            backup.setIdMonstre(idMonstre);

            // peut lever une exception
            joueurHttp.acquireMonstre(idMonstre, token);

            return Map.of("id", invocationRepository.save(backup.getSavedInvocation()).getId());
        } catch (RuntimeException e) {
            backupRepository.save(backup);
            throw new RuntimeException("Invocation failed.");
        }

    }

    public List<String> recup(String token, String username) {
        List<InvocationBackup> backups = backupRepository.findByUsername(username);

        List<String> l = new ArrayList<>();

        for (InvocationBackup backup : backups) {
            l.add(backup.getIdMonstre());
        }

        return l;

        // for (InvocationBackup backup : backups) {
        //     Invocation saved = backup.getSavedInvocation();
        //     int calcul = backup.getCalcul();
            
        // }

    }
    
}
