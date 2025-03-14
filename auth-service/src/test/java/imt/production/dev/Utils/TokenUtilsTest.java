package imt.production.dev.Utils;

// import imt.production.dev.Utils.TokenUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TokenUtilsTest {

    @Test
    void tokenExpiredAfterOneHour() {
        LocalDateTime pastDateTime = LocalDateTime.now().minusHours(1);
        // String token = TokenUtils.generateToken("testUser", pastDateTime);
        assertTrue(TokenUtils.isTokenExpired(pastDateTime));
    }
}
