package in.digeshwar.controller;


import in.digeshwar.util.JwtUtilRs256;
import in.digeshwar.util.RefreshTokenStore;
import in.digeshwar.util.TokenBlacklist;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LogoutController {

    private final JwtUtilRs256 jwtRs256;
    private final TokenBlacklist tokenBlacklist;
    private final RefreshTokenStore refreshTokenStore;

    public LogoutController(
            JwtUtilRs256 jwtRs256,
            TokenBlacklist tokenBlacklist,
            RefreshTokenStore refreshTokenStore
    ){
        this.jwtRs256 = jwtRs256;
        this.tokenBlacklist = tokenBlacklist;
        this.refreshTokenStore = refreshTokenStore;
    }


    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestHeader(name = "Authorization", required = false) String header,
            @CookieValue(name = "REFRESH_TOKEN", required = false) String refreshToken,
            HttpServletResponse response) {

        // 1️⃣ Handle access & refresh token (if present)
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            Claims claims = jwtRs256.parseAndValidate(token);
            tokenBlacklist.revoke(claims.getId());  // Blacklist access token
            refreshTokenStore.deleteAllForUser(claims.getSubject()); // Delete ALL refresh tokens for this user
            refreshTokenStore.delete(refreshToken);
        }

        // 2️⃣ Clear refresh-token cookie (browser safety)
        ResponseCookie deleteRefresh = ResponseCookie.from("REFRESH_TOKEN", "")
                .httpOnly(true)
                .path("/")
                .maxAge(0)
                .build();

        // 3️⃣ Clear access token cookie (browser safety)
        ResponseCookie deleteAccess = ResponseCookie.from("ACCESS_TOKEN", "")
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(0)
                .build();

        response.addHeader("Set-Cookie", deleteRefresh.toString());
        response.addHeader("Set-Cookie", deleteAccess.toString());
        return ResponseEntity.ok().build();
    }

}
