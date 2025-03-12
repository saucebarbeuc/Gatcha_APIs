package imt.production.dev.Errors.HTTP_404;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TypeElementaireNotFoundException extends RuntimeException {
    
    public TypeElementaireNotFoundException(String message) {
        super(message);
    }
    
}
