package imt.production.dev.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import imt.production.dev.Dto.InvocationDto;
import imt.production.dev.Dto.JoueurDto;
import imt.production.dev.Model.Invocation;
import imt.production.dev.Model.InvocationBackup;
import imt.production.dev.Repository.BackupRepository;
import imt.production.dev.Repository.Remote.JoueurHttp;
import imt.production.dev.Repository.Remote.MonstreHttp;

@Service
public class InvocationService {

    private MonstreHttp monstreHttp;
    private JoueurHttp joueurHttp;
    private BackupRepository backupRepository;

    public InvocationService(MonstreHttp monstreHttp, JoueurHttp joueurHttp, BackupRepository backupRepository){
        this.monstreHttp = monstreHttp;
        this.joueurHttp = joueurHttp;
        this.backupRepository = backupRepository;
    }

    public JoueurDto invoque(InvocationDto invocationDto, String token, String username) {
        InvocationBackup backup = new InvocationBackup();
        backup.setUsername(username);

        try {

            Invocation saved = new Invocation();
            saved.setTaux(invocationDto.getTaux());
            saved.setType(invocationDto.getMonstreDto().getTypeElementaire());
            saved.setStats(invocationDto.getMonstreDto().getStats());
            saved.setCompetences(invocationDto.getMonstreDto().getCompetences());

            backup.setCalcul(0);

            // peut lever une exception
            String idMonstre = monstreHttp.create(invocationDto.getMonstreDto(), token);

            backup.setIdMonstre(idMonstre);
            backupRepository.save(backup);

            // peut lever une exception
            JoueurDto j = joueurHttp.acquireMonstre(idMonstre, token);

            return j;
            
        } catch (RuntimeException e) {
            backupRepository.save(backup);
            throw new RuntimeException("Invocation failed.");
        }

    }

    public List<InvocationBackup> recup(String token, String username) {
        List<InvocationBackup> backups = backupRepository.findByUsername(username);

        return backups;

        // for (InvocationBackup backup : backups) {
        //     Invocation saved = backup.getSavedInvocation();
        //     int calcul = backup.getCalcul();
            
        // }

    }
    
}
