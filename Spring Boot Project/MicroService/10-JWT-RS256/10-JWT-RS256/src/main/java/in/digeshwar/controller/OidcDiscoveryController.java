package in.digeshwar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OidcDiscoveryController {

    @GetMapping("/.well-known/openid-configuration")
    public Map<String, Object> discovery() {

        return Map.of(
                "issuer", "http://localhost:8081",
                "jwks_uri", "http://localhost:8081/.well-known/jwks.json",
                "id_token_signing_alg_values_supported", new String[]{"RS256"},
                "token_endpoint_auth_methods_supported", new String[]{"client_secret_post"},
                "claims_supported", new String[]{"sub", "iss", "exp", "iat"}
        );
    }
}
