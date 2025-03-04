package imt.production.dev.MiddleWare;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Value("${auth.api.url}")
    private String AUTH_API_URL;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(new ObjectMapper().writeValueAsString(Map.of("error", "No token provided")));
            response.getWriter().flush();
            return false;
        }

        try {

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map<String, String>> authResponse = restTemplate.exchange(
                AUTH_API_URL + "?token=" + token, 
                HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<Map<String, String>>() {}
            );

            request.setAttribute("username", authResponse.getBody().get("username"));
            return true;

        } catch (HttpClientErrorException e) {
            response.setStatus(e.getStatusCode().value());
            response.getWriter().write(e.getResponseBodyAsString());
            response.getWriter().flush();

            return false;

            // response.sendError(e.getStatusCode().value(), "test");
            // return false;
        }
       
    }
    
}

    

