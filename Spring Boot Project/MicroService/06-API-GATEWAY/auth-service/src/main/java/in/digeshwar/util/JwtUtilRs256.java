package in.digeshwar.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtilRs256 {

    private static final String ISSUER = "auth-service";
    private static final String AUDIENCE = "api-gateway";
    private static final long EXPIRATION_MS = 15 * 60 * 1000;
    private final RSAPrivateKey privateKey;
    private final RSAPublicKey publicKey;

    public JwtUtilRs256(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    // This will use in Auth Service
    public String generateToken(String username) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())   // 👈 (token id)
                .setSubject(username)
                .setIssuer(ISSUER)
                .setAudience(AUDIENCE)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusMillis(EXPIRATION_MS)))
                .setHeaderParam("kid", "auth-key-1")
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    // This will use in Resource Service
    public Claims parseAndValidate(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .requireIssuer(ISSUER)
                .requireAudience(AUDIENCE)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}