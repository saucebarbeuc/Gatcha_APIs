package imt.production.dev.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    // Base simulée pour stocker les utilisateurs et tokens
    private final Map<String, String> userStore = new HashMap<>(); // username -> password
    private final Map<String, TokenInfo> tokenStore = new HashMap<>(); // token -> TokenInfo

    public AuthenticationController() {
        // Ajout d'un utilisateur pour test
        userStore.put("user1", "password1");
        userStore.put("admin", "admin123");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestParam String identifiant, @RequestParam String password) {
        if (!userStore.containsKey(identifiant) || !userStore.get(identifiant).equals(password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        // Générer le token
        String token = generateToken(identifiant);
        LocalDateTime expiration = LocalDateTime.now().plusHours(1);
        tokenStore.put(token, new TokenInfo(identifiant, expiration));

        // Retourner le token
        return ResponseEntity.ok(Map.of(
            "token", token,
            "expiresAt", expiration.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        ));
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam String token) {
        TokenInfo tokenInfo = tokenStore.get(token);
        if (tokenInfo == null || tokenInfo.getExpiration().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }

        // Mettre à jour la date d'expiration
        tokenInfo.setExpiration(LocalDateTime.now().plusHours(1));
        return ResponseEntity.ok(Map.of(
            "username", tokenInfo.getUsername(),
            "newExpiresAt", tokenInfo.getExpiration().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        ));
    }

    private String generateToken(String username) {
        String datePart = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss"));
        String randomPart = UUID.randomUUID().toString();
        return username + "-" + datePart + "-" + randomPart;
    }

    // Classe interne pour gérer les informations des tokens
    private static class TokenInfo {
        private String username;
        private LocalDateTime expiration;

        public TokenInfo(String username, LocalDateTime expiration) {
            this.username = username;
            this.expiration = expiration;
        }

        public String getUsername() {
            return username;
        }

        public LocalDateTime getExpiration() {
            return expiration;
        }

        public void setExpiration(LocalDateTime expiration) {
            this.expiration = expiration;
        }
    }
}
