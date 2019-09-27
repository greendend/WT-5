import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("1. Просмотр книг библиотеки\n2. Мои книги\n3. Просмотр авторов\n");
        System.out.print("Введите номер действия: ");

        int num = in.nextInt();
        while ((num < 0) || (num > 3)) {
            System.out.print("Неправильный ввод\n");
            System.out.print("Введите номер действия: ");
            num = in.nextInt();
        }

        Book book = new Book();
        ArrayList<Book> booklist = new ArrayList<>();

        book.name = "Boobs";
        book.author = "J. K. Rowling";
        book.pagecount = 823;
        try(FileWriter writer = new FileWriter("books.txt", true))
        {
            // запись всей строки
            String text = book.name + "/" + book.author + "/" + book.pagecount + "/";
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        switch (num) {
            case (1):
                System.out.print("Переход к коду 1\n");
                //book.displayInfo();
                break;
            case (2):
                System.out.print("Переход к коду 2\n");
                break;
            case (3):
                System.out.print("Переход к коду 3\n");
                break;
        }
    }
}
