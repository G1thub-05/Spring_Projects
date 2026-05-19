package in.digeshwar.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtilHs256 {

    private static final String ISSUER = "internal-auth";
    private static final long EXPIRATION_MS = 15 * 60 * 1000;
    private final SecretKey secretKey;

    public JwtUtilHs256( @Value("${jwt.secret}") String secret) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username) {

        Instant now = Instant.now();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())   // 👈 add this line (token id)
                .setSubject(username)
                .setIssuer(ISSUER)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusMillis(EXPIRATION_MS)))
                .setHeaderParam("kid", "auth-key-1") // 👈 HERE
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims parseAndValidate(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .requireIssuer(ISSUER)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
