package imt.production.dev.Errors.HTTP_404;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MonstreIdNonExistantException extends RuntimeException {

    public MonstreIdNonExistantException(String message) {
        super(message);
    }
    
}
