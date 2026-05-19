package in.digeshwar.multidb.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import in.digeshwar.multidb.entity.h2.Product;
public interface ProductRepository extends JpaRepository<Product, Long> {}
