package in.digeshwar.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtilRs256 {

    private static final String ISSUER = "http://localhost:8081";
    @Value("${jwt.access-token-ttl-ms}")
    private long EXPIRATION_MS;
    private final RSAPrivateKey privateKey;
    private final RSAPublicKey publicKey;

    public JwtUtilRs256(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String generateToken(String username) {

        Instant now = Instant.now();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())   // 👈 add this line (token id)
                .setSubject(username)
                .setIssuer(ISSUER)
                .setHeaderParam("kid", "auth-key-1")
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusMillis(EXPIRATION_MS)))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public Claims parseAndValidate(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .requireIssuer(ISSUER)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return parseAndValidate(token).getSubject();
    }

}
