package imt.production.dev.Errors.HTTP_401;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class TokenExpireException extends RuntimeException {
 
    public TokenExpireException(String message) {
        super(message);
    }
    
}
