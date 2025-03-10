package imt.production.dev.Repository.Remote;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import imt.production.dev.Dto.JoueurDto;

@Component
public class JoueurHttp {

    @Value("${joueur.api.url}")
    private String API_URL;
    
    public JoueurDto acquireMonstre(String idMonstre, String token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.put("Authorization", Arrays.asList(token));

        HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);

        try {
            ResponseEntity<JoueurDto> response = restTemplate.exchange(
                API_URL + "/monsters?monster=" + idMonstre,
                HttpMethod.POST,
                requestEntity,
                JoueurDto.class
            );

            return response.getBody();
        } catch (RuntimeException e) {

            throw new RuntimeException("Api Joueur Introuvable" + e.getMessage());
        }
    }


}
