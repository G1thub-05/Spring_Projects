package in.digeshwar.utils;

import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RefreshTokenStore {

    private final Map<String, String> store = new ConcurrentHashMap<>();
    public void save(String refreshToken, String username) {
        store.put(refreshToken, username);
    }
    public String getUsername(String refreshToken) {
        return store.get(refreshToken);
    }
    public void delete(String refreshToken) {
        store.remove(refreshToken);
    }

    // 🚨 reuse detection support
    public void deleteAllForUser(String username) {
        store.entrySet().removeIf(e -> e.getValue().equals(username));
    }
}



