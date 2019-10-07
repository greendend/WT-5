import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;


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

        //чтение файла!
        try {
            File file = new File("E:\\Учеба\\WT-5[Library]\\books.txt");
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            String s = line;
            while (line != null) {
                //System.out.println(line);
                // считываем остальные строки в цикле
                line = reader.readLine();
                s = s + line;
            }
            //System.out.println(s);
            //заполняем библиотеку книгами
            line = "";
            String word = "";
            int h = 1;
            for (int i = 0; i < s.length(); i++) {
                line = line + s.charAt(i);
                while (s.charAt(i) == '|') {
                    for (int j = 0; j < line.length(); j++){
                        word = word + line.charAt(j);
                        while (line.charAt(j) == '/') { //сюда докинуть ещё чек на конец структуры
                            if (h == 1) {
                                book.name = word;
                                h++;
                                word = "";
                                break;
                            } else if (h == 2) {
                                book.author = word;
                                h++;
                                word = "";
                                break;
                            } else if (h == 3) {
                                book.pagecount = Integer.parseInt(word);
                                h = 1;
                                word = "";
                                line = "";
                                booklist.add(book);
                                book = null;
                                break;
                            }
                        }
                    }
                }
            }
            System.out.println(book.name);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        //выбор действия!
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
}
