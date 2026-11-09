public class Book {

	private int id;
	private String title;
	private String author;
	private String isbn;
	private int numberOfPages;

	// Initializes a book with default values.
	public Book() {
		this(0, "", "", "", 0);
	}

	// Initializes a book with the given details.
	public Book(int id, String title, String author, String isbn, int numberOfPages) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.numberOfPages = numberOfPages;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	// Returns the book's information as one formatted block.
	@Override
	public String toString() {
		return String.format(
				"ID: %d%nTitle: %s%nAuthor: %s%nISBN: %s%n"
						+ "Number of pages: %d%n"
						+ "------------------------------",
				id, title, author, isbn, numberOfPages
		);
	}

	// Displays this book's information.
	public void printBookInfo() {
		System.out.println(this);
	}
}