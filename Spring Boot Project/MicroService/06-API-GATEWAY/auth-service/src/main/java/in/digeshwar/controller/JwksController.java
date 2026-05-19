package in.digeshwar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;


// Instead of copying public.pem, expose it dynamically.
// Call /.well-known/jwks.json → Cache public key → Verify JWTs
@RestController
public class JwksController {

    private final RSAPublicKey publicKey;

    public JwksController(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> jwks() {
        return Map.of(
                "keys", List.of(
                        Map.of(
                                "kty", "RSA",
                                "alg", "RS256",
                                "use", "sig",
                                "kid", "auth-key-1",
                                "n", base64UrlEncode(publicKey.getModulus()),
                                "e", base64UrlEncode(publicKey.getPublicExponent())
                        )
                )
        );
    }

    private String base64UrlEncode(BigInteger value) {
        byte[] bytes = value.toByteArray();
        if (bytes[0] == 0) {
            bytes = Arrays.copyOfRange(bytes, 1, bytes.length);
        }
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
