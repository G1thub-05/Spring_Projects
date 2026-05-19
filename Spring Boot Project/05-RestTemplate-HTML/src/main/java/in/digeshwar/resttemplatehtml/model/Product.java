package in.digeshwar.resttemplatehtml.model;

import lombok.Data;
import java.util.Map;

@Data
public class Product {
    private String id;
    private String name;
    private Map<String, Object> data;
}