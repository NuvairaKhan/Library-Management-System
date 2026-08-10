import java.io.IOException;

public interface Manageable<T> {
    void add(T obj);
    void view();
    void update(int id);
    void delete(int id);
    void loadFromFile() throws IOException;
    void saveToFile() throws IOException;
}