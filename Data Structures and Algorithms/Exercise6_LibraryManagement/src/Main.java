import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        Book[] books = {
                new Book(101, "C Programming", "Dennis Ritchie"),
                new Book(102, "Data Structures", "Mark Allen"),
                new Book(103, "Java Programming", "James Gosling"),
                new Book(104, "Operating Systems", "Galvin"),
                new Book(105, "Python Basics", "Guido")
        };

        System.out.println("Linear Search:");

        Book result1 = LibrarySearch.linearSearch(books, "Java Programming");

        if (result1 != null) {
            System.out.println(result1);
        } else {
            System.out.println("Book Not Found.");
        }

        Arrays.sort(books, Comparator.comparing(book -> book.title));

        System.out.println("\nBinary Search:");

        Book result2 = LibrarySearch.binarySearch(books, "Java Programming");

        if (result2 != null) {
            System.out.println(result2);
        } else {
            System.out.println("Book Not Found.");
        }
    }
}