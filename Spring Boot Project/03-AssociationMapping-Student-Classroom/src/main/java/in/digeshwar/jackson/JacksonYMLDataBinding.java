package in.digeshwar.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

class Config {
	public String appName;
	public int maxUsers;
}

public class JacksonYMLDataBinding  {
	public static void main(String[] args) throws Exception {
		ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
		Config cfg = new Config();
		cfg.appName = "Food Ordering";
		cfg.maxUsers = 100;

		// Serialization — Java → YAML
		String yaml = yamlMapper.writeValueAsString(cfg);
		System.out.println("YAML:" + yaml);

		// Deserialization — YAML → Java
		Config loaded = yamlMapper.readValue(yaml, Config.class);
		System.out.println("Loaded appName = " + loaded.appName);
		System.out.println("Loaded maxUsers = " + loaded.maxUsers);
	}
}
