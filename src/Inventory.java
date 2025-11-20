import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Inventory {
    private LinkedList<Book> inventoryList = new LinkedList<>();
    private Queue<String> orderQueue = new LinkedList<>();

    public void addBook(Scanner scanner) {
        scanner.nextLine();

        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter book price: ");
        double price = 0.0;

        if (scanner.hasNextDouble()) {
            price = scanner.nextDouble();
        } else {
            System.out.println("Invalid price entered. Failed to add book.");
            scanner.nextLine();
            return;
        }

        Book newBook = new Book(title, author, isbn, price);
        inventoryList.add(newBook);
        System.out.println("Book added successfully!");
    }

    public void displayAllBooks() {
        System.out.println("--- All Books in Inventory ---");

        if (inventoryList.isEmpty()) {
            System.out.println("The inventory is currently empty.");
            return;
        }

        for (Book book : inventoryList) {
            System.out.println(book.toString());
        }

        System.out.println("-----------------------------");
    }

    public void sortBooksByTitle() {
        System.out.println("Sorting books by title...");

        int n = inventoryList.size();

        if (n <= 1) {
            System.out.println("There are not enough books to sort.");
            return;
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                if (inventoryList.get(j).getTitle().compareToIgnoreCase(inventoryList.get(j + 1).getTitle()) > 0) {
                    Book temp = inventoryList.get(j);
                    inventoryList.set(j, inventoryList.get(j + 1));
                    inventoryList.set(j + 1, temp);
                }
            }
        }
        System.out.println();
        displayAllBooks();
        System.out.println("Books sorted successfully!");
    }

    public void searchBookByTitle(Scanner scanner) {
        scanner.nextLine();

        System.out.print("Enter the title of the book to search for: ");
        String searchTitle = scanner.nextLine().trim();
        boolean found = false;

        for (Book book : inventoryList) {
            if (book.getTitle().equalsIgnoreCase(searchTitle)) {

                System.out.println("Book found:");
                System.out.println(book.toString());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Book with title '" + searchTitle + "' was not found in the inventory.");
        }
    }

    public void addOrderToQueue(Scanner scanner) {
        scanner.nextLine();

        System.out.print("Enter the title of the book to order: ");
        String orderTitle = scanner.nextLine().trim();

        orderQueue.add(orderTitle);
        System.out.println("Order for \"" + orderTitle + "\" has been added to the queue.");
    }

    public void processNextOrder() {
        System.out.println("Processing next order...");

        if (orderQueue.isEmpty()) {
            System.out.println("The order queue is empty. No orders to process.");
        } else {
            String processedTitle = orderQueue.remove();
            System.out.println("Processed order for: " + processedTitle);
        }
    }

    public static void main(String[] args) {
        Inventory inventoryManager = new Inventory();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Bookstore Inventory Management System!");

        while (running) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Add a new book");
            System.out.println("2. Display all books");
            System.out.println("3. Sort books by title");
            System.out.println("4. Search for a book by title");
            System.out.println("5. Add a customer order to the queue");
            System.out.println("6. Process the next customer order");
            System.out.println("7. Exit");
            System.out.print("\nEnter your choice: ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        inventoryManager.addBook(scanner);
                        break;
                    case 2:
                        inventoryManager.displayAllBooks();
                        break;
                    case 3:
                        inventoryManager.sortBooksByTitle();
                        break;
                    case 4:
                        inventoryManager.searchBookByTitle(scanner);
                        break;
                    case 5:
                        inventoryManager.addOrderToQueue(scanner);
                        break;
                    case 6:
                        inventoryManager.processNextOrder();
                        break;
                    case 7:
                        running = false;
                        System.out.println("Thank you for using the Bookstore Inventory Management System!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 to 7.");
                }

            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        scanner.close();
    }
}