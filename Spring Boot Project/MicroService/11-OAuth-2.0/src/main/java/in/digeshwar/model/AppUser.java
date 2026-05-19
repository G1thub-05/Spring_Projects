package in.digeshwar.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String provider;   // GOOGLE or GITHUB

    @Column(nullable = false)
    private String role;       // USER or ADMIN

    @Column(nullable = false)
    private String name;       // Full name

}

