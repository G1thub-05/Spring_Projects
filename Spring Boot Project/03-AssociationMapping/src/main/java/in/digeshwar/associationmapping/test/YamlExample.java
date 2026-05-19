package in.digeshwar.associationmapping.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

class Config {
    public String appName;
    public int maxUsers;
}

public class YamlExample {
    public static void main(String[] args) throws Exception {
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        Config cfg = new Config();
        cfg.appName = "Food Ordering";
        cfg.maxUsers = 100;

        String yaml = yamlMapper.writeValueAsString(cfg);
        System.out.println("YAML:" + yaml);

        Config loaded = yamlMapper.readValue(yaml, Config.class);
        System.out.println("Loaded appName = " + loaded.appName);
    }
}
