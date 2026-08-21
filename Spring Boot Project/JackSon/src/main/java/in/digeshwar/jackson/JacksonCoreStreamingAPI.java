package in.digeshwar.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.io.StringWriter;
import com.fasterxml.jackson.core.*;

public class JacksonCoreStreamingAPI  {
	public static void main(String[] args) throws IOException {
		// Write JSON using streaming API
		StringWriter out = new StringWriter();
		JsonFactory factory = new JsonFactory();
		JsonGenerator gen = factory.createGenerator(out);

		gen.writeStartObject();
		gen.writeStringField("name", "Digeshwar");
		gen.writeBooleanField("active", true);
		gen.writeEndObject();
		gen.close();

		String json = out.toString();
		System.out.println("Generated JSON: " + json);

		// Read JSON using streaming API
		JsonParser parser = factory.createParser(json);
		while (!parser.isClosed()) {
			JsonToken token = parser.nextToken();
			if (token == JsonToken.FIELD_NAME) {
				String fieldName = parser.getCurrentName();
				parser.nextToken(); // move to value
				System.out.println(fieldName + " = " + parser.getValueAsString());
			}
		}
		parser.close();
	}
}


