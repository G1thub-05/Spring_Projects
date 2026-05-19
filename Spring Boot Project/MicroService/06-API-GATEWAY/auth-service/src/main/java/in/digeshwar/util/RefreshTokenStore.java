package in.digeshwar.util;

import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RefreshTokenStore {

    private final Map<String, String> store = new ConcurrentHashMap<>();
    public void save(String refreshToken, String username) {
        store.put(refreshToken, username);
        System.out.println("REFRESH SAVED = " + refreshToken);
        System.out.println("STORE SIZE AFTER SAVE = " + store.size());
    }

    public String getUsername(String refreshToken) {
        System.out.println("REFRESH RECEIVED = " + refreshToken);
        System.out.println("STORE KEYS = " + store.keySet());
        return store.get(refreshToken);
    }

    public void delete(String refreshToken) {
        System.out.println("DELETING REFRESH TOKEN = " + refreshToken);
        store.remove(refreshToken);
    }

    // 🚨 reuse detection support
    public void deleteAllForUser(String username) {
        store.entrySet().removeIf(e -> e.getValue().equals(username));
    }
}



