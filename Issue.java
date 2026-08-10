public class Issue {
    private final int bookId;
    private final int memberId;
    private String status;

    public Issue(int bookId, int memberId, String status) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.status = status;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void display() {
        System.out.println("Book ID: " + bookId +
                ", Member ID: " + memberId +
                ", Status: " + status);
    }

    @Override
    public String toString() {
        return bookId + "," + memberId + "," + status;
    }
}