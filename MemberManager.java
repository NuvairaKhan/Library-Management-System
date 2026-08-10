import java.io.*;
import java.util.*;

public class MemberManager implements Manageable<Member> {
    private final ArrayList<Member> members = new ArrayList<>();
    private final String fileName = "members.txt";

    @Override
    public void add(Member member) {
        members.add(member);
    }

    @Override
    public void view() {
        if (members.isEmpty()) {
            System.out.println("No members available.");
            return;
        }
        for (Member m : members)
            m.display();
    }

    @Override
    public void update(int id) {
        for (Member m : members) {
            if (m.getId() == id) {
                try (Scanner sc = new Scanner(System.in)) {
                    System.out.print("Enter new name: ");
                    m.setName(sc.nextLine());
                }
                System.out.println("Member updated successfully.");
                return;
            }
        }
        System.out.println("Member not found.");
    }

    @Override
    public void delete(int id) {
        boolean removed = members.removeIf(m -> m.getId() == id);
        System.out.println(removed ? "Member deleted successfully." : "Member not found.");
    }

    public boolean exists(int id) {
        for (Member m : members)
            if (m.getId() == id)
                return true;
        return false;
    }

    public int getNextId() {
        int maxId = 0;
        for (Member m : members)
            if (m.getId() > maxId)
                maxId = m.getId();
        return maxId + 1;
    }

    @Override
    public void loadFromFile() throws IOException {
        File file = new File(fileName);
        if (!file.exists()) return;
        members.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                members.add(new Member(
                        Integer.parseInt(data[0]),
                        data[1]));
            }
        }
    }

    @Override
    public void saveToFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Member m : members)
                bw.write(m.toString() + "\n");
        }
    }
}