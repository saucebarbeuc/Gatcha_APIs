package imt.production.dev.Repository;

import imt.production.dev.DTO.UtilisateurDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@Repository
public class UtilisateurRemoteRepository implements UtilisateurCustomRepository {

    @Value("${auth.api.url}")
    private String authApiUrl;

    @Override
    public String getUtilisateurNameByToken(String token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.put("Authorization", Arrays.asList(token));

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Map<String, String>> authResponse = restTemplate.exchange(
                    authApiUrl + "?token=" + token,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Map<String, String>>() {}
            );

            return authResponse.getBody().get("username");
        } catch (Exception e) {
            throw new RuntimeException(token + " n'existe pas !");
        }
    }

}
