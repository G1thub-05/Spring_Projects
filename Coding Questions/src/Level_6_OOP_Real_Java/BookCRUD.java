package Level_6_OOP_Real_Java;

import java.util.ArrayList;
import java.util.List;

class Book {

	int id;
	String title;
	String author;

	Book(int id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

	@Override
	public String toString() {
		return "ID: " + id + ", Title: " + title + ", Author: " + author;
	}
}

public class BookCRUD {

	static List<Book> books = new ArrayList<>();

	// CREATE
	static void addBook(Book book) {
		books.add(book);
	}

	// READ
	static void displayBooks() {
		for (Book book : books) {
			System.out.println(book);
		}
	}

	// UPDATE
	static void updateBook(int id, String title, String author) {

		for (Book book : books) {

			if (book.id == id) {
				book.title = title;
				book.author = author;
				return;
			}
		}
	}

	// DELETE
	static void deleteBook(int id) {
		books.removeIf(book -> book.id == id);
	}

	public static void main(String[] args) {

		// CREATE
		addBook(new Book(1, "Java", "James"));
		addBook(new Book(2, "Spring", "Rod"));
		addBook(new Book(3, "SQL", "John"));

		// READ
		System.out.println("Books:");
		displayBooks();

		// UPDATE
		updateBook(2, "Spring Boot", "Rod");

		// DELETE
		deleteBook(3);

		System.out.println("\nAfter Update and Delete:");
		displayBooks();
	}
}
