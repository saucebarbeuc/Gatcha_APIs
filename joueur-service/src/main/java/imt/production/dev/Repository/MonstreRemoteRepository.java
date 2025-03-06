package imt.production.dev.Repository;

import java.util.Arrays;

import imt.production.dev.DTO.MonstreDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class MonstreRemoteRepository implements MonstreCustomRepository {

    @Value("${monstre.api.url}")
    private String monstreApiUrl;

    @Override
    public String monstreExist(String monstreId, String token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.put("Authorization", Arrays.asList(token));

        HttpEntity<String> requestEntity = new HttpEntity<>(monstreId, headers);

        try {
            ResponseEntity<MonstreDTO> response = restTemplate.exchange(
                    monstreApiUrl + '/' + monstreId,
                    HttpMethod.GET,
                    requestEntity,
                    MonstreDTO.class
            );

            return response.getBody().getNom();
        } catch (RuntimeException e) {
            throw new RuntimeException("Monstre " + monstreId + " could not be acquired", e);
        }
    }

}
