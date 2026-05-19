package in.digeshwar.resttemplatehtml.service;




import in.digeshwar.resttemplatehtml.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    private static final String API_URL =
            "https://api.restful-api.dev/objects";

    @Autowired
    private RestTemplate restTemplate;

    public List<Product> getAllProducts() {

        Product[] products =
                restTemplate.getForObject(API_URL, Product[].class);

        return Arrays.asList(products);
    }
}
