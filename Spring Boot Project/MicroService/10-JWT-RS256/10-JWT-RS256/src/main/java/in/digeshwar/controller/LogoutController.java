package in.digeshwar.controller;

import in.digeshwar.utils.JwtUtilRs256;
import in.digeshwar.utils.RefreshTokenStore;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LogoutController {

    private final JwtUtilRs256 jwtUtilRs256;
    private final RefreshTokenStore refreshTokenStore;

    public LogoutController(
            JwtUtilRs256 jwtUtilRs256,
            RefreshTokenStore refreshTokenStore
    ) {
        this.jwtUtilRs256 = jwtUtilRs256;
        this.refreshTokenStore = refreshTokenStore;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestHeader(name = "Authorization", required = false) String header,
            @CookieValue(name = "REFRESH_TOKEN", required = false) String refreshToken,
            HttpServletResponse response
    ) {

        // Best-effort cleanup using access token
        if (header != null && header.startsWith("Bearer ")) {
            try {
                String token = header.substring(7);
                String username = jwtUtilRs256.extractUsername(token);
                refreshTokenStore.deleteAllForUser(username);
            } catch (Exception ignored) {
            }
        }

        // Always revoke refresh token if present
        if (refreshToken != null) {
            refreshTokenStore.delete(refreshToken);
        }

        // Clear cookie safely
        ResponseCookie deleteCookie = ResponseCookie.from("REFRESH_TOKEN", "")
                .httpOnly(true)
                .secure(false)     // must match creation
                .sameSite("Lax")   // must match creation
                .path("/auth")
                .maxAge(0)
                .build();

        response.addHeader("Set-Cookie", deleteCookie.toString());
        return ResponseEntity.noContent().build();
    }
}
