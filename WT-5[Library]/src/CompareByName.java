import java.util.Comparator;

public class CompareByName implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getName().compareTo(b2.getName());
    }
}
