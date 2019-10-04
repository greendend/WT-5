import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("1. Просмотр книг библиотеки\n2. Мои книги\n3. Просмотр авторов\n4. Подарить книгу библиотеке\n");
        System.out.print("Введите номер действия: ");

        int num = in.nextInt();
        while ((num < 1) || (num > 4)) {
            System.out.print("Неправильный ввод\n");
            System.out.print("Введите номер действия: ");
            num = in.nextInt();
        }

        Book book = new Book();
        ArrayList<Book> booklist = new ArrayList<>();

        String fileName = "books.txt";
        //String contents = readUsingFiles(fileName);



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
            case (4):
                System.out.print("Переход к коду 4\n");

                System.out.print("1. Подарить книгу библиотеке\n");
                System.out.print("2. Вернуться\n");
                while ((num < 1) || (num > 2)) {
                    System.out.print("Неправильный ввод\n");
                    System.out.print("Введите номер действия: ");
                    num = in.nextInt();
                }

                if (num == 2)
                    break;

                System.out.print("Введите название книги: ");
                in.nextLine();
                book.name = in.nextLine();

                System.out.print("Введите автора: ");
                book.author = in.nextLine();
                System.out.print("Введите кол-во страниц: ");
                book.pagecount = in.nextInt();

                try(FileWriter writer = new FileWriter("books.txt", true))
                {
                    // запись всей строки
                    String text = book.name + "/" + book.author + "/" + book.pagecount + "|";
                    writer.write(text);
                    writer.flush();
                }
                catch(IOException ex){
                    System.out.println(ex.getMessage());
                }
                break;
        }
    }

    private static String readUsingFiles(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
