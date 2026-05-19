package in.digeshwar.service;

import in.digeshwar.model.AppUser;
import in.digeshwar.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.Set;


@Service
public class CustomOAuth2UserService
        implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oauthUser = delegate.loadUser(userRequest);
        String provider = userRequest.getClientRegistration().getRegistrationId().toUpperCase();

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        if (name == null) {name = oauthUser.getAttribute("login");} // GitHub fallback
        if (email == null) { throw new OAuth2AuthenticationException("Email not found from provider");}
        if (name == null) {name = "Unknown User";}

        // Find or create user
        String finalName = name;
        AppUser user = userRepository.findByEmail(email).orElseGet(() -> {
                    AppUser newUser = new AppUser();
                    newUser.setEmail(email);
                    newUser.setName(finalName);
                    newUser.setProvider(provider);
                    newUser.setRole("USER"); // default role
                    return userRepository.save(newUser);
                });

        // Sync name updates
        if (!user.getName().equals(name)) {
            user.setName(name);
            userRepository.save(user);
        }

        // Attach role to Spring Security
        Set<GrantedAuthority> authorities = Set.of(
                new SimpleGrantedAuthority("ROLE_" + user.getRole())
        );

        return new DefaultOAuth2User(authorities, oauthUser.getAttributes(), "email");
    }
}
