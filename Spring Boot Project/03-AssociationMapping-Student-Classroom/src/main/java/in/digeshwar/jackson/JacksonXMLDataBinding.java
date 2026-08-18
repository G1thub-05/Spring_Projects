package in.digeshwar.jackson;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

class Book {
	public String title;
	public String author;
	public Book() {}
	public Book(String t, String a) { this.title = t; this.author = a; }
}

public class JacksonXMLDataBinding  {
	public static void main(String[] args) throws Exception {
		XmlMapper xmlMapper = new XmlMapper();

		// Serialization — Java → XML
		Book book = new Book("Spring in Action", "Craig Walls");
		String xml = xmlMapper.writeValueAsString(book);
		System.out.println("XML: " + xml);

		// Deserialization — XML → Java
		Book fromXml = xmlMapper.readValue(xml, Book.class);
		System.out.println("Book: " + fromXml.title + " by " + fromXml.author);
	}
}
