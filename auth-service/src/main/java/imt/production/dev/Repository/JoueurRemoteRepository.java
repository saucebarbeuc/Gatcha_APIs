package imt.production.dev.Repository;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import imt.production.dev.Dto.JoueurDto;

@Repository
public class JoueurRemoteRepository implements JoueurCustomRepository {

    @Value("${joueur.api.url}")
    private String joueurApiUrl;

    @Override
    public void createJoueur(JoueurDto joueur, String token) {
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.put("Authorization", Arrays.asList(token));

        HttpEntity<JoueurDto> requestEntity = new HttpEntity<>(joueur, headers);

        try {
            restTemplate.postForObject(joueurApiUrl, requestEntity, Void.class);

        } catch (RuntimeException e) {
            throw e;
        }
    }

}
