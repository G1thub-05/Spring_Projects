package in.digeshwar.controller;


import in.digeshwar.utils.JwtUtilHs256;
import in.digeshwar.utils.RefreshTokenStore;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtilHs256 jwtUtilHs256;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenStore refreshTokenStore;

    public AuthController(
            JwtUtilHs256 jwtUtilHs256,
            AuthenticationManager authenticationManager,
            RefreshTokenStore refreshTokenStore
    ){

        this.jwtUtilHs256 = jwtUtilHs256;
        this.authenticationManager = authenticationManager;
        this.refreshTokenStore = refreshTokenStore;
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED, "Invalid credentials"
            );
        }
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginRefCookiesHs(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password,
            HttpServletResponse response) {

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", "Username and password are required"));
        }

        authenticate(username, password);
        String accessToken = jwtUtilHs256.generateToken(username);
        String refreshToken = UUID.randomUUID().toString();
        refreshTokenStore.save(refreshToken, username);
        ResponseCookie RefreshToken = ResponseCookie.from("REFRESH_TOKEN", refreshToken)
                .httpOnly(true)         // Blocks JS or XSS access
                .secure(false)          // Only over HTTPS (set false for localhost & true in prod)
                .sameSite("Lax")        // Blocks CSRF (set Lax for localhost & Strict in prod)
                .path("/")              // Limits exposure
                .maxAge(Duration.ofDays(7))
                .build();
        response.addHeader("Set-Cookie", RefreshToken.toString());

        return ResponseEntity.ok(Map.of(
                "access_token", accessToken,
                "token_type", "Bearer",
                "access_token_expires_in_min", 2,
                "refresh_token", RefreshToken,
                "refresh_token_expires_in_days", 7));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, Object>> refreshCookiesRot(
            @CookieValue(name = "REFRESH_TOKEN", required = false) String refreshToken,
            HttpServletResponse response) {

        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Missing refresh token"));
        }

        String username = refreshTokenStore.getUsername(refreshToken);

        // 🚨 reuse detection
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Refresh token reused or invalid"));
        }

        refreshTokenStore.delete(refreshToken);                   // Refresh Token Rotation
        String newRefreshToken = UUID.randomUUID().toString();    // issue new refresh token
        refreshTokenStore.save(newRefreshToken, username);
        ResponseCookie NewRefreshToken = ResponseCookie.from("REFRESH_TOKEN", newRefreshToken)
                .httpOnly(true)   // Blocks JS or XSS access
                .secure(false)    // Only over HTTPS (set false for localhost & true in prod)
                .sameSite("Lax")  // Blocks CSRF (set Lax for localhost & Strict in prod)
                .path("/")        // Limits exposure
                .maxAge(Duration.ofDays(7))
                .build();
        response.addHeader("Set-Cookie", NewRefreshToken.toString());
        return ResponseEntity.ok(Map.of(
                "new_access_token", jwtUtilHs256.generateToken(username), // issue new access token
                "token_type", "Bearer",
                "new_access_token_expires_in_min", 2,
                "new_refresh_token", newRefreshToken,
                "new_refresh_token_expires_in_days", 7
        ));
    }
}
