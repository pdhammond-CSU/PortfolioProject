import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {

	// Runs the library menu until the user exits.
	public static void main(String[] args) {
		Inventory inventory = new Inventory();

		try (Scanner scanner = new Scanner(System.in)) {
			boolean running = true;

			while (running) {
				printMenu();

				switch (readInt(scanner, "Enter your choice: ")) {
					case 1:
						addBook(scanner, inventory);
						break;

					case 2:
						processBookAction(scanner, inventory,
								"Enter the book ID to borrow: ",
								inventory::borrowBook);
						break;

					case 3:
						processBookAction(scanner, inventory,
								"Enter the book ID to return: ",
								inventory::returnBook);
						break;

					case 4:
						searchByTitle(scanner, inventory);
						break;

					case 5:
						inventory.printAll();
						break;

					case 6:
						running = false;
						System.out.println("Exiting the program. Goodbye!");
						break;

					default:
						System.out.println(
								"Invalid option. Please choose 1 through 6.");
				}
			}
		}
	}

	// Displays the menu options.
	private static void printMenu() {
		System.out.println("\n--- Library Menu ---");
		System.out.println("1. Add Book");
		System.out.println("2. Borrow Book");
		System.out.println("3. Return Book");
		System.out.println("4. Search by Title");
		System.out.println("5. Print All Books");
		System.out.println("6. Exit");
	}

	// Collects valid book details and adds the book to the inventory.
	private static void addBook(Scanner scanner, Inventory inventory) {
		int id = readInt(scanner, "Enter book ID: ");
		String title = readLine(scanner, "Enter title: ");
		String author = readLine(scanner, "Enter author: ");
		String isbn = readLine(scanner, "Enter ISBN: ");
		int numberOfPages = readInt(scanner, "Enter number of pages: ");

		if (!isValidBook(id, title, author, isbn, numberOfPages)) {
			return;
		}

		inventory.addBook(id, title, author, isbn, numberOfPages);
	}

	// Handles the borrow and return options, which share the same flow.
	private static void processBookAction(Scanner scanner,
			Inventory inventory, String prompt,
			java.util.function.IntConsumer action) {

		action.accept(readInt(scanner, prompt));
	}

	// Collects a title and displays all matching books.
	private static void searchByTitle(Scanner scanner, Inventory inventory) {
		String title = readLine(scanner, "Enter a full or partial title: ");

		if (title.isEmpty()) {
			System.out.println("Please enter a title to search for.");
			return;
		}

		ArrayList<Book> matches = inventory.searchByTitle(title);

		if (matches.isEmpty()) {
			System.out.println("No matching book found.");
			return;
		}

		displayMatches(matches);
	}

	// Displays a heading and each matching book.
	private static void displayMatches(ArrayList<Book> matches) {
		System.out.println("\n--- Search Results ---");

		for (Book book : matches) {
			book.printBookInfo();
		}

		System.out.println("Matching available copies: " + matches.size());
	}

	// Confirms the book details meet the library's requirements.
	private static boolean isValidBook(int id, String title, String author,
			String isbn, int numberOfPages) {

		if (id <= 0 || numberOfPages <= 0) {
			System.out.println(
					"Book not added. ID and number of pages must be positive.");
			return false;
		}

		if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
			System.out.println(
					"Book not added. Title, author, and ISBN cannot be empty.");
			return false;
		}

		return true;
	}

	// Reads one line of text from the user.
	private static String readLine(Scanner scanner, String prompt) {
		System.out.print(prompt);

		return scanner.nextLine().trim();
	}

	// Reads a whole number, repeating until the input is valid.
	private static int readInt(Scanner scanner, String prompt) {
		while (true) {
			System.out.print(prompt);

			try {
				return Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println(
						"Invalid input. Please enter a whole number.");
			}
		}
	}
}