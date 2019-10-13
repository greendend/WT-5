import java.util.*;
import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;


public class Main {

    static Human human = new Human();
    static Library library = new Library();



    public static void main(String[] args) {

        Book book = new Book();
        ArrayList<Book> booklist = new ArrayList<>();
        ArrayList<String> authors = new ArrayList<>();
        ArrayList<Author> authorList = new ArrayList<>();
        int numAuthor = 0;

        boolean flag = true;
        //чтение файла!
        try {
            File file = new File("books.txt");
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
                Book books = new Book(); //чтобы не перезатерались книжки
                line = line + s.charAt(i);
                while (s.charAt(i) == '|') {
                    for (int j = 0; j < line.length(); j++){
                        word = word + line.charAt(j);
                        while ((line.charAt(j+1) == '/') || (line.charAt(j+1) == '|')) { //сюда докинуть ещё чек на конец структуры
                            if (h == 1) {
                                books.name = word;
                                h++;
                                word = "";
                                j++;
                                break;
                            } else if (h == 2) {
                                books.author = word;
                                h++;
                                word = "";
                                j++;
                                break;
                            } else if (h == 3) {
                                books.pagecount = Integer.parseInt(word);
                                h = 1;
                                word = "";
                                line = "";
                                //booklist.add(books);
                                library.addBook(books); //добавление в библиотеку

                                Author author = new Author();
                                author.name = books.author;
                                author.bookList.add(books);
                                authorList.add(author);
                                break;
                            }
                        }
                    }
                    break;
                }
            }
            //System.out.println(book.name);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



        //выбор действия!
        while (true) {
            Book[] arr = new Book[library.bookList.size()];
            Book[] arrh = new Book[human.bookList.size()];

            Scanner in = new Scanner(System.in);
            System.out.print("1. Просмотр книг библиотеки\n2. Мои книги\n3. Просмотр авторов\n4. Подарить книгу библиотеке\n");
            System.out.print("Введите номер действия: ");
            String str = in.next();
            int num = 0;
            while (true) {
                if ((str.matches("[-+]?\\d+")))
                    num = Integer.parseInt(str);
                else
                    System.out.print("Неправильный ввод\n");

                if ((num >= 1) || (num <= 4)) {
                    break;
                }
            }


            switch (num) {
                case (1):
                    System.out.print("Переход к коду 1\n---------------------------------\n"); //книги библиотеки
                    for (int i = 0; i < library.bookList.size(); i++)
                        System.out.println(i + ". " + library.bookList.get(i).name + " | " + library.bookList.get(i).author + " | " + library.bookList.get(i).pagecount);
                    System.out.print("---------------------------------\n");
                    System.out.print("1. Взять книгу\n2. Сортировать по названию (А-Я)\n3. Сортировать по автору (А-Я)\n4. Сортировать по кол-ву страниц (по возр.)\n5. Поиск по названию\n6. Поиск по автору\n7. Вернуться\n");
                    System.out.print("Введите номер действия: ");
                    num = in.nextInt();
                    while ((num < 1) || (num > 7)) {
                        System.out.print("Неправильный ввод\n");
                        System.out.print("Введите номер действия: ");
                        num = in.nextInt();
                    }


                    switch (num) {
                        case (1):
                            System.out.print("Введите номер книги: ");
                            num = in.nextInt();

                            if ((num >= library.bookList.size()) || (num < 0))
                                System.out.println("Неверный номер книги");
                            else {
                                //library.bookList.get(num);
                                human.addBook(library.bookList.get(num));
                                library.deleteBook(library.bookList.get(num));
                                System.out.println("Вы взяли книгу: " + human.bookList.get(human.bookList.size() - 1).name + " | " + human.bookList.get(human.bookList.size() - 1).author + " | " + human.bookList.get(human.bookList.size() - 1).pagecount);
                            }

                            break;
                        case (2):
                            sortByTag(arr, new CompareByName());
                            break;
                        case (3):
                            sortByTag(arr, new CompareByAuthor());
                            break;
                        case (4):
                            sortByTag(arr, new CompareByPageCount());
                            break;
                        case (5):
                            System.out.print("Введите название книги: ");
                            in.nextLine();
                            book.name = in.nextLine();
                            for (int i = 0; i < library.bookList.size(); i++)
                                if (book.name.equals(library.bookList.get(i).name)) {
                                    System.out.println("Найдено совпадение: " + library.bookList.get(i).name + " | " + library.bookList.get(i).author + " | " + library.bookList.get(i).pagecount);
                                }
                            break;
                        case (6):
                            System.out.print("Введите имя автора: ");
                            in.nextLine();
                            book.author = in.nextLine();
                            for (int i = 0; i < library.bookList.size(); i++)
                                if (book.author.equals(library.bookList.get(i).author)) {
                                    System.out.println("Найдено совпадение: " + library.bookList.get(i).name + " | " + library.bookList.get(i).author + " | " + library.bookList.get(i).pagecount);
                                }
                            break;
                        case (7):
                            break;
                    }

                    break;
                case (2):
                    System.out.print("Переход к коду 2\n---------------------------------\n"); //мои книги
                    for (int i = 0; i < human.bookList.size(); i++)
                        System.out.println(i + ". " + human.bookList.get(i).name + " | " + human.bookList.get(i).author + " | " + human.bookList.get(i).pagecount);
                    if (human.bookList.size() == 0)
                        System.out.println("У вас нет книг");
                    System.out.print("---------------------------------\n");
                    System.out.print("1. Вернуть книгу\n2. Сортировать по названию (А-Я)\n3. Сортировать по автору (А-Я)\n4. Сортировать по кол-ву страниц (по возр.)\n5. Поиск по названию\n6. Поиск по автору\n7. Вернуться\n");
                    System.out.print("Введите номер действия: ");
                    num = in.nextInt();
                    while ((num < 1) || (num > 7)) {
                        System.out.print("Неправильный ввод\n");
                        System.out.print("Введите номер действия: ");
                        num = in.nextInt();
                    }


                    switch (num) {
                        case (1):
                            System.out.print("Введите номер книги: ");
                            num = in.nextInt();

                            if ((num >= human.bookList.size()) || (num < 0))
                                System.out.println("Неверный номер книги");
                            else {
                                human.bookList.get(num);
                                library.addBook(human.bookList.get(num));
                                human.deleteBook(human.bookList.get(num));
                                System.out.println("Вы вернули книгу: " + library.bookList.get(library.bookList.size() - 1).name + " | " + library.bookList.get(library.bookList.size() - 1).author + " | " + library.bookList.get(library.bookList.size() - 1).pagecount);
                            }

                            break;
                        case (2):
                            sortByTagH(arrh, new CompareByName());
                            break;
                        case (3):
                            sortByTagH(arrh, new CompareByAuthor());
                            break;
                        case (4):
                            sortByTagH(arrh, new CompareByPageCount());

                        case (5):
                            System.out.print("Введите название книги: ");
                            in.nextLine();
                            book.name = in.nextLine();
                            for (int i = 0; i < human.bookList.size(); i++)
                                if (book.name.equals(human.bookList.get(i).name)) {
                                    System.out.println("Найдено совпадение: " + human.bookList.get(i).name + " | " + human.bookList.get(i).author + " | " + human.bookList.get(i).pagecount);
                                }
                            break;
                        case (6):
                            System.out.print("Введите имя автора: ");
                            in.nextLine();
                            book.author = in.nextLine();
                            for (int i = 0; i < human.bookList.size(); i++)
                                if (book.author.equals(human.bookList.get(i).author)) {
                                    System.out.println("Найдено совпадение: " + human.bookList.get(i).name + " | " + human.bookList.get(i).author + " | " + human.bookList.get(i).pagecount);
                                }
                            break;
                        case (7):
                            break;

                    }
                    break;
                case (3):
                    System.out.print("Переход к коду 3\n"); // авторы
                    System.out.print("---------------------------------\n");
                    for (int i = 0; i < authorList.size(); i++)
                        System.out.println(i + ". " + authorList.get(i).name);
                    System.out.print("---------------------------------\n");
                    //System.out.print("1. Сортировка (А-Я)\n");
                    System.out.print("1. Просмотр книг, принадлежащих автору\n2. Вернуться\n");

                    while ((num < 1) || (num > 2)) {
                        System.out.print("Введите номер действия: ");
                        num = in.nextInt();
                    }
                    if (num == 1) {
                        System.out.print("Введите номер автора: ");
                        num = in.nextInt();
                        while ((num >= authorList.size()) || (num < 0)) {
                            System.out.println("Неправильный ввод");
                            System.out.print("Введите номер автора: ");
                            num = in.nextInt();
                        }
                        numAuthor = num;

                        System.out.println("Книги автора " + authorList.get(num).name + ":\n");
                        System.out.println("---------------------------------\n");
                        for (int i = 0; i < authorList.get(num).bookList.size(); i++)
                            System.out.println(i + ". " + authorList.get(num).bookList.get(i).name + " | " + authorList.get(num).bookList.get(i).pagecount);
                        System.out.println("---------------------------------\n");
                        System.out.println("1. Изменить книгу\n2. Вернуться\n");
                        while ((num < 1) || (num > 2)) {
                            System.out.print("Введите номер действия: ");
                            num = in.nextInt();
                        }
                        if (num == 1) {
                            System.out.print("Введите номер книги:");
                            num = in.nextInt();
                            while ((num >= authorList.get(numAuthor).bookList.size()) || (num < 0)) {
                                System.out.println("Неправильный ввод");
                                System.out.print("Введите номер автора: ");
                                num = in.nextInt();
                            }
                            System.out.print("Введите название книги: ");
                            in.nextLine();
                            book.name = in.nextLine();

                            System.out.print("Введите кол-во страниц: ");
                            book.pagecount = in.nextInt();

                            authorList.get(numAuthor).bookList.get(num).name = book.name;
                            authorList.get(numAuthor).bookList.get(num).pagecount = book.pagecount;
                        } else break;
                    } else break;



                    //1. просмотр
                    //2. сортировка
                    //3. просмотр книг принадлежащих автору
                    break;
                case (4):
                    System.out.print("Переход к коду 4\n"); //создание книги

                    System.out.print("1. Подарить книгу библиотеке\n");
                    System.out.print("2. Вернуться\n");
                    boolean f = false;
                    while ((num < 1) || (num > 2)) {
                        if (f)
                            System.out.print("Неправильный ввод\n");
                        f = true;
                        System.out.print("Введите номер действия: ");
                        num = in.nextInt();
                    }

                    if (num == 2) //return
                        break;

                    System.out.print("Введите название книги: ");
                    in.nextLine();
                    book.name = in.nextLine();

                    System.out.print("Введите автора: ");
                    book.author = in.nextLine();
                    System.out.print("Введите кол-во страниц: ");
                    book.pagecount = in.nextInt();

                    try (FileWriter writer = new FileWriter("books.txt", true)) {
                        // запись всей строки
                        String text = book.name + "/" + book.author + "/" + book.pagecount + "|";
                        writer.write(text);
                        writer.flush();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                    library.addBook(book);
                    Author author = new Author();
                    author.name = book.author;
                    author.bookList.add(book);
                    authorList.add(author);
                    break;
            }
        }

    }

    public static void sortByTag(Book[] obj, Comparator c){
        int i = 0;
        for(Book book : library.bookList)
        {
            obj[i] = book;
            ++i;
        }
        Arrays.sort(obj, c);
        library.bookList.clear();
        for (int j = 0; j < i; j++)
            library.bookList.add(obj[j]);
    }

    public static void sortByTagH(Book[] obj, Comparator c){
        int i = 0;
        for(Book book : human.bookList)
        {
            obj[i] = book;
            ++i;
        }
        Arrays.sort(obj, c);
        human.bookList.clear();
        for (int j = 0; j < i; j++)
            human.bookList.add(obj[j]);
    }

}
