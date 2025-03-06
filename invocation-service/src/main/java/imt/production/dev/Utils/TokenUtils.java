package imt.production.dev.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TokenUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss");

    public static String generateToken(String username, LocalDateTime now) {
        try {
            String dateTime = now.format(FORMATTER);
            String token = username + "-" + dateTime;
            return CypherUtils.encrypt(token);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du chiffrement du token", e);
        }
    }

    public static boolean isTokenExpired(LocalDateTime dateTime) {
        return dateTime.isBefore(LocalDateTime.now().minus(1, ChronoUnit.HOURS));
    }

}

