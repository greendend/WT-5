import java.util.ArrayList;

public class Human {
    ArrayList<Book> bookList = new ArrayList<>();

    public void addBook(Book book) {
        bookList.add(book);
    }

    public void deleteBook(Book book) {
        bookList.remove(book);
    }
}
