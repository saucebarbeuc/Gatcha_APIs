package imt.production.dev.Errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class JoueurApiIndisponibleException extends RuntimeException {
    
    public JoueurApiIndisponibleException() {
        super("API Joueur indisponible");
    }
}