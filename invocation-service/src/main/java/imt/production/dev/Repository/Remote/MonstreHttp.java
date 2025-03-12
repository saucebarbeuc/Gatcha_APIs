package imt.production.dev.Repository.Remote;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import imt.production.dev.Dto.MonstreUtils.MonstreDto;

@Component
public class MonstreHttp {

    @Value("${monstre.api.url}")
    private String API_URL;

    public String create(MonstreDto dto, String token) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.put("Authorization", Arrays.asList(token));

        HttpEntity<MonstreDto> requestEntity = new HttpEntity<>(dto, headers);

        try {
            ResponseEntity<Map<String, String>> response = restTemplate.exchange(
                    API_URL,
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<Map<String, String>>() {}
            );

            return response.getBody().get("id");
        } catch (RuntimeException e) {
            throw new RuntimeException("Api Monstre Introuvable");
        }
    }


}
