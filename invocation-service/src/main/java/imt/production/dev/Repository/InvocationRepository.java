package imt.production.dev.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import imt.production.dev.Model.Invocation;

@Repository
public interface InvocationRepository extends MongoRepository<Invocation, String>  {
    
}
