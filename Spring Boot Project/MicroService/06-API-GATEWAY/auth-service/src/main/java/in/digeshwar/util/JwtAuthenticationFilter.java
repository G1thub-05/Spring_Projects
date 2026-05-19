package in.digeshwar.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final UserDetailsService userDetailsService;
    private final JwtUtilHs256 jwtHs256;
    private final JwtUtilRs256 jwtRs256;
    private final TokenBlacklist tokenBlacklist;  // 👈 add this line in JwtAuthenticationFilter


    public JwtAuthenticationFilter(
            UserDetailsService userDetailsService,
            JwtUtilHs256 jwtHs256,
            JwtUtilRs256 jwtRs256,
            TokenBlacklist tokenBlacklist) {

        this.userDetailsService = userDetailsService;
        this.jwtHs256 = jwtHs256;
        this.jwtRs256 = jwtRs256;
        this.tokenBlacklist = tokenBlacklist;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        System.out.println("HEADER = " + request.getHeader("Authorization"));
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            try {
                String issuer = Jwts.parserBuilder()
                        .build()
                        .parseClaimsJwt(token.substring(0, token.lastIndexOf('.') + 1))
                        .getBody()
                        .getIssuer();


                Claims claims;

                if ("auth-service".equals(issuer)) {
                    claims = jwtRs256.parseAndValidate(token);
                } else {
                    claims = jwtHs256.parseAndValidate(token);
                }

                // 👇 add this line in JwtAuthenticationFilter
                String jti = claims.getId();
                if (tokenBlacklist.isRevoked(jti)) {
                    throw new JwtException("Token revoked");
                }


                String username = claims.getSubject();

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities()
                            );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

            catch (JwtException ex) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}