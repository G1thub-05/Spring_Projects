package in.digeshwar.controller;


import in.digeshwar.util.JwtUtilHs256;
import in.digeshwar.util.JwtUtilRs256;
import in.digeshwar.util.RefreshTokenStore;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtilHs256 jwtHs256;
    private final JwtUtilRs256 jwtRs256;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenStore refreshTokenStore;

    public AuthController(
            JwtUtilHs256 jwtHs256,
            JwtUtilRs256 jwtRs256,
            AuthenticationManager authenticationManager,
            RefreshTokenStore refreshTokenStore
    ){

        this.jwtHs256 = jwtHs256;
        this.jwtRs256 = jwtRs256;
        this.authenticationManager = authenticationManager;
        this.refreshTokenStore = refreshTokenStore;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
    }

    @PostMapping("/hs256/login/cookies")
    public ResponseEntity<Map<String, String>> loginCookiesHs(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletResponse response) {

        authenticate(username, password);
        String accessToken = jwtHs256.generateToken(username);
        String refreshToken = UUID.randomUUID().toString();
        refreshTokenStore.save(refreshToken, username);
        ResponseCookie cookie = ResponseCookie.from("REFRESH_TOKEN", refreshToken)
                .httpOnly(true)
                .secure(false)          // true in HTTPS
                .path("/")              // Set "/auth" endpoints or "/" for postman
                .sameSite("Lax")        // Set "Strict" for prod or "Lax" for postman
                .maxAge(Duration.ofDays(7))
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
        System.out.println("SET-COOKIE = " + cookie.toString());
//        return ResponseEntity.ok(accessToken);
        return ResponseEntity.ok(Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        ));
    }

    @PostMapping("/hs256/refresh/cookies")
    public ResponseEntity<String> refreshCookiesHs(
            @CookieValue(name = "REFRESH_TOKEN", required = false) String refreshToken,
            HttpServletResponse response) {

        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing refresh token");
        }

        String username = refreshTokenStore.getUsername(refreshToken);

        // 🚨 reuse detection
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Refresh token reused or invalid");
        }

        refreshTokenStore.delete(refreshToken);
        String newRefreshToken = UUID.randomUUID().toString();
        refreshTokenStore.save(newRefreshToken, username);
        ResponseCookie cookie = ResponseCookie.from("REFRESH_TOKEN", newRefreshToken)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(Duration.ofDays(7))
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return ResponseEntity.ok(jwtHs256.generateToken(username));
    }


    @PostMapping("/rs256/login/cookies")
    public ResponseEntity<Map<String, String>> loginCookiesRs(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletResponse response) {

        authenticate(username, password);
        String accessToken = jwtRs256.generateToken(username);
        String refreshToken = UUID.randomUUID().toString();
        refreshTokenStore.save(refreshToken, username);
        ResponseCookie cookie = ResponseCookie.from("REFRESH_TOKEN", refreshToken)
                .httpOnly(true)
                .secure(false)          // true in HTTPS
                .path("/")              // Set "/auth" endpoints or "/" for postman
                .sameSite("Lax")        // Set "Strict" for prod or "Lax" for postman
                .maxAge(Duration.ofDays(7))
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.ok(Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        ));
    }

    @PostMapping("/rs256/refresh/cookies")
    public ResponseEntity<String> refreshCookiesRs(
            @CookieValue(name = "REFRESH_TOKEN", required = false) String refreshToken,
            HttpServletResponse response) {

        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Missing refresh token");
        }
        String username = refreshTokenStore.getUsername(refreshToken);

        // 🚨 reuse detection
        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Refresh token reused or invalid");
        }

        refreshTokenStore.delete(refreshToken);
        String newRefreshToken = UUID.randomUUID().toString();
        refreshTokenStore.save(newRefreshToken, username);
        ResponseCookie cookie = ResponseCookie.from("REFRESH_TOKEN", newRefreshToken)
                .httpOnly(true)
                .secure(false)
                .sameSite("Lax")
                .path("/")
                .maxAge(Duration.ofDays(7))
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.ok(jwtRs256.generateToken(username));
    }


}
