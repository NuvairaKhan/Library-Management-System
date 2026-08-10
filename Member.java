public class Member extends Person {

    public Member(int id, String name) {
        super(id, name);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("Member ID: " + id + ", Name: " + name);
    }

    @Override
    public String toString() {
        return id + "," + name;
    }
}