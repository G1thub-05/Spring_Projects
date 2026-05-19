package in.digeshwar.config;

import in.digeshwar.util.JwtAuthenticationFilter;
import in.digeshwar.util.JwtUtilHs256;
import in.digeshwar.util.JwtUtilRs256;
import in.digeshwar.util.TokenBlacklist;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // ✅ NO CONSTRUCTOR HERE

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(
            UserDetailsService userDetailsService,
            JwtUtilHs256 jwtUtilHs256,
            JwtUtilRs256 jwtUtilRs256,
            TokenBlacklist tokenBlacklist // 👈 add this line in SecurityConfig
    ){

        return new JwtAuthenticationFilter(
                userDetailsService,
                jwtUtilHs256,
                jwtUtilRs256,
                tokenBlacklist           // 👈 add this line in SecurityConfig
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/log/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
