import java.io.*;
import java.util.*;

public class BookManager implements Manageable<Book> {
    private final ArrayList<Book> books = new ArrayList<>();
    private final String fileName = "books.txt";

    @Override
    public void add(Book book) {
        books.add(book);
    }

    @Override
    public void view() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book b : books)
            b.display();
    }

    @Override
    public void update(int id) {
        for (Book b : books) {
            if (b.getBookId() == id) {
                try (Scanner sc = new Scanner(System.in)) {
                    System.out.print("Enter new title: ");
                    b.setTitle(sc.nextLine());
                    System.out.print("Enter new author: ");
                    b.setAuthor(sc.nextLine());
                }
                System.out.println("Book updated successfully.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    @Override
    public void delete(int id) {
        boolean removed = books.removeIf(b -> b.getBookId() == id);
        System.out.println(removed ? "Book deleted successfully." : "Book not found.");
    }

    public boolean exists(int id) {
        for (Book b : books)
            if (b.getBookId() == id)
                return true;
        return false;
    }

    public int getNextId() {
        int maxId = 0;
        for (Book b : books)
            if (b.getBookId() > maxId)
                maxId = b.getBookId();
        return maxId + 1;
    }

    @Override
    public void loadFromFile() throws IOException {
        File file = new File(fileName);
        if (!file.exists()) return;
        books.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                books.add(new Book(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2]));
            }
        }
    }

    @Override
    public void saveToFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Book b : books)
                bw.write(b.toString() + "\n");
        }
    }
}