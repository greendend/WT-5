public class Book {
    String name;
    String author;
    int pagecount;

    void displayInfo() {
        System.out.printf("Название: %s| \tАвтор: %s| \tКол-во страниц :%d|\n", name, author, pagecount);
    }
}
