import java.io.*;
import java.util.*;

public class IssueManager implements Manageable<Issue> {
    private final ArrayList<Issue> issues = new ArrayList<>();
    private final String fileName = "issues.txt";

    @Override
    public void add(Issue issue) {
        issues.add(issue);
    }

    @Override
    public void view() {
        if (issues.isEmpty()) {
            System.out.println("No issues available.");
            return;
        }
        for (Issue i : issues)
            i.display();
    }

    @Override
    public void update(int bookId) {
        for (Issue i : issues) {
            if (i.getBookId() == bookId) {
                try (Scanner sc = new Scanner(System.in)) {
                    System.out.print("Enter new status: ");
                    i.setStatus(sc.nextLine());
                }
                System.out.println("Issue updated successfully.");
                return;
            }
        }
        System.out.println("Issue not found.");
    }

    @Override
    public void delete(int bookId) {
        boolean removed = issues.removeIf(i -> i.getBookId() == bookId);
        System.out.println(removed ? "Issue deleted successfully." : "Issue not found.");
    }

    @Override
    public void loadFromFile() throws IOException {
        File file = new File(fileName);
        if (!file.exists()) return;
        issues.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                issues.add(new Issue(
                        Integer.parseInt(data[0]),
                        Integer.parseInt(data[1]),
                        data[2]));
            }
        }
    }

    @Override
    public void saveToFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Issue i : issues)
                bw.write(i.toString() + "\n");
        }
    }
}