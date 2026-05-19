//package in.digeshwar.util;
//
//import org.springframework.stereotype.Component;
//
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//
//@Component
//public class TokenBlacklist {
//    private final Set<String> revokedTokens = ConcurrentHashMap.newKeySet();
//
//    public void revoke(String jti) {
//        revokedTokens.add(jti);
//    }
//
//    public boolean isRevoked(String jti) {
//        return revokedTokens.contains(jti);
//    }
//}
