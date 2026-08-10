public class Book {
    private final int bookId;
    private String title;
    private String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void display() {
        System.out.println("Book ID: " + bookId +
                ", Title: " + title +
                ", Author: " + author);
    }

    @Override
    public String toString() {
        return bookId + "," + title + "," + author;
    }
}