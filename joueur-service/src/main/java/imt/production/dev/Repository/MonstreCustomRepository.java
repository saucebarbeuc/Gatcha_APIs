package imt.production.dev.Repository;

import imt.production.dev.DTO.MonstreDTO;

public interface MonstreCustomRepository {

    String monstreExist(String monstreId, String token);

}