//package in.digeshwar.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//
//import java.io.InputStream;
//import java.security.KeyFactory;
//import java.security.interfaces.RSAPublicKey;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Base64;
//
//// Load keys from PEM files (Auth Server)
//@Configuration
//public class PublicRsaKeyConfig {
//
//    @Value("${jwt.public-key}")
//    private Resource publicKeyResource;
//
//    // ================= PUBLIC KEY =================
//    @Bean
//    public RSAPublicKey publicKey() {
//        try (InputStream is = publicKeyResource.getInputStream()) {
//
//            String key = new String(is.readAllBytes())
//                    .replaceAll("-----BEGIN (.*)-----", "")
//                    .replaceAll("-----END (.*)-----", "")
//                    .replaceAll("\\s+", "");
//
//            byte[] decoded = Base64.getDecoder().decode(key);
//            X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
//
//            return (RSAPublicKey) KeyFactory
//                    .getInstance("RSA")
//                    .generatePublic(spec);
//
//        } catch (Exception e) {
//            throw new IllegalStateException("Failed to load RSA public key", e);
//        }
//    }
//}
