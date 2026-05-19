//package in.digeshwar.util;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import org.springframework.stereotype.Component;
//
//import java.security.interfaces.RSAPublicKey;
//
//@Component
//public class JwtUtilRs256 {
//
//    private static final String ISSUER = "auth-service";
//    private static final String AUDIENCE = "api-gateway";
//    private final RSAPublicKey publicKey;
//
//    public JwtUtilRs256(RSAPublicKey publicKey) {
//        this.publicKey = publicKey;
//    }
//
//    public Claims parseAndValidate(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(publicKey)
//                .requireIssuer(ISSUER)
//                .requireAudience(AUDIENCE)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}