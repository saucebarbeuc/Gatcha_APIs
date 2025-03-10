package imt.production.dev.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import imt.production.dev.Model.InvocationBackup;

@Repository
public interface BackupRepository extends MongoRepository<InvocationBackup, String>  {

    List<InvocationBackup> findByUsername(String username);
    
}
