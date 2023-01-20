package megalab.news.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;

@Component
@ConfigurationProperties(prefix = "app.security.token")
@Data
public class TokenUtilities {

    private String secret;
    private int expiresAfterDays;

    public String generateToken(String nickname) {
        return JWT.create()
                .withIssuedAt(new Date())
                .withExpiresAt(ZonedDateTime.now().plusDays(expiresAfterDays).toInstant())
                .withClaim("nickname", nickname)
                .sign(Algorithm.HMAC512(secret));
    }

    public String validateToken(String token) {
        return JWT.require(Algorithm.HMAC512(secret))
                .build()
                .verify(token)
                .getClaim("nickname")
                .asString();
    }
}

