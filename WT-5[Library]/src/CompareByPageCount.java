import java.util.Comparator;

public class CompareByPageCount implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        if (b1.getPageCount() == b2.getPageCount())
            return 0;
        else if (b1.getPageCount() > b2.getPageCount())
            return 1;
        else return -1;
    }
}