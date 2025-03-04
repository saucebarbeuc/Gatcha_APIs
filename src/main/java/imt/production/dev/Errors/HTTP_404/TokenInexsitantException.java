package imt.production.dev.Errors.HTTP_404;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TokenInexsitantException extends RuntimeException {
 
    public TokenInexsitantException(String message) {
        super(message);
    }

}