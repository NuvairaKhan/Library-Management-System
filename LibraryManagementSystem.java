import java.io.IOException;
import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        try {
            BookManager bookManager = new BookManager();
            MemberManager memberManager = new MemberManager();
            IssueManager issueManager = new IssueManager();

            bookManager.loadFromFile();
            memberManager.loadFromFile();
            issueManager.loadFromFile();

            try (Scanner sc = new Scanner(System.in)) {
                int choice;
                do {
                    System.out.println("\n1.Add Book\n2.View Books\n3.Add Member\n4.View Members\n5.Issue Book\n6.View Issues\n7.Exit");
                    choice = sc.nextInt();
                    sc.nextLine();
                    
                    switch (choice) {
                        case 1 -> {
                            int id = bookManager.getNextId();
                            System.out.print("Enter title: ");
                            String title = sc.nextLine();
                            System.out.print("Enter author: ");
                            String author = sc.nextLine();
                            bookManager.add(new Book(id, title, author));
                        }
                        case 2 -> bookManager.view();
                        case 3 -> {
                            int id = memberManager.getNextId();
                            System.out.print("Enter name: ");
                            memberManager.add(new Member(id, sc.nextLine()));
                        }
                        case 4 -> memberManager.view();
                        case 5 -> {
                            System.out.print("Enter book ID: ");
                            int bookId = sc.nextInt();
                            System.out.print("Enter member ID: ");
                            int memberId = sc.nextInt();
                            
                            if (!bookManager.exists(bookId)) {
                                System.out.println("Book ID not found. Please add the book first.");
                            } else if (!memberManager.exists(memberId)) {
                                System.out.println("Member ID not found. Please register the member first.");
                            } else {
                                issueManager.add(new Issue(bookId, memberId, "Issued"));
                                System.out.println("Book issued successfully.");
                            }
                        }
                        case 6 -> issueManager.view();
                        case 7 -> {
                            bookManager.saveToFile();
                            memberManager.saveToFile();
                            issueManager.saveToFile();
                            System.out.println("Data saved. Exiting...");
                        }
                    }
                } while (choice != 7);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}