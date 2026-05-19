package in.digeshwar.multidb.entity.h2;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;


    // Default constructor (required by JPA)
    public Product() {
    }

    // Parameterized constructor (you are missing this)
    public Product(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
