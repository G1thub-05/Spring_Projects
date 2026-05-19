//package in.digeshwar.filter;
//
//import in.digeshwar.util.JwtUtilRs256;
//import in.digeshwar.util.TokenBlacklist;
//import io.jsonwebtoken.Claims;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//
//@Component
//public class JwtGatewayFilter extends AbstractGatewayFilterFactory<JwtGatewayFilter.Config> {
//
//    private final JwtUtilRs256 jwtUtilRs256;
//    private final TokenBlacklist blacklist;
//
//    public JwtGatewayFilter(JwtUtilRs256 jwtUtilRs256, TokenBlacklist blacklist) {
//        super(Config.class);
//        this.jwtUtilRs256 = jwtUtilRs256;
//        this.blacklist = blacklist;
//    }
//
//    @Override
//    public GatewayFilter apply(Config config) {
//        return (exchange, chain) -> {
//
//            String path = exchange.getRequest().getURI().getPath();
//
//            // 🔓 PUBLIC ENDPOINTS (NO JWT)
//            if (path.startsWith("/auth")) {
//                return chain.filter(exchange);
//            }
//
//            // 🔐 ACCESS TOKEN REQUIRED
//            String authHeader = exchange.getRequest()
//                    .getHeaders()
//                    .getFirst(HttpHeaders.AUTHORIZATION);
//
//            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                return exchange.getResponse().setComplete();
//            }
//
//            String token = authHeader.substring(7);
//
//            try {
//                Claims claims = jwtUtilRs256.parseAndValidate(token);
//
//                // 2️⃣ ISSUER VALIDATION
//                if (!"auth-service".equals(claims.getIssuer())) {
//                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                    return exchange.getResponse().setComplete();
//                }
//
//                // 3️⃣ BLACKLIST CHECK
//                if (blacklist.isRevoked(claims.getId())) {
//                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                    return exchange.getResponse().setComplete();
//                }
//
//                // 🔑 Inject user identity (THIS IS THE FIX)
//                ServerHttpRequest mutatedRequest =
//                        exchange.getRequest().mutate()
//                                .header("X-Authenticated-User", claims.getSubject())
//                                .build();
//
//                ServerWebExchange mutatedExchange =
//                        exchange.mutate().request(mutatedRequest).build();
//
//                return chain.filter(mutatedExchange);
//
//
//            } catch (Exception e) {
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                return exchange.getResponse().setComplete();
//            }
//
//        };
//    }
//
//    public static class Config {}
//}
