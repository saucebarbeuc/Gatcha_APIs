package imt.production.dev.Errors.HTTP_409;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UtilisateurDejaExistantException extends RuntimeException {
    
    public UtilisateurDejaExistantException(String message) {
        super(message);
    }
}
