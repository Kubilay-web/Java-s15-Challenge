package Main;

import categories.Category;
import library.Book;
import library.Library;
import users.User;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        Set<User> users = new HashSet<>();
        Library library = new Library(books, users);

        Book book1 = new Book("1", "Book 1", "Author 1", "Category 1");
        Book book2 = new Book("2", "Book 2", "Author 2", "Category 2");
        User user1 = new User("1", "User 1");
        User user2 = new User("2", "User 2");
        Category category1 = new Category("Category 1");
        Category category2 = new Category("Category 2");

        books.add(book1);
        books.add(book2);
        users.add(user1);
        users.add(user2);
        category1.addBookToCategory(book1);
        category2.addBookToCategory(book2);

        // Ana uygulama mantığı
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Kitap ödünç al");
            System.out.println("2. Kitap iade et");
            System.out.println("3. Çıkış");
            System.out.print("Seçiminizi girin: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    borrowBook(library, scanner);
                    break;
                case 2:
                    returnBook(library, scanner);
                    break;
                case 3:
                    System.out.println("Çıkılıyor!");
                    System.exit(0);
                default:
                    System.out.println("Geçersiz seçenek. Lütfen geçerli bir seçenek girin.");
            }
        }
    }

    private static void borrowBook(Library library, Scanner scanner) {
        // Kitap ödünç alma mantığını uygulayın
        System.out.print("Kullanıcı ID'sini girin: ");
        String userId = scanner.nextLine();
        System.out.print("Kitap ID'sini girin: ");
        String bookId = scanner.nextLine();

        User user = findUserById(library, userId);
        Book book = library.findBookById(bookId);

        if (user != null && book != null) {
            library.borrowBook(user, book);
        } else {
            System.out.println("Kullanıcı veya kitap bulunamadı.");
        }
    }

    private static void returnBook(Library library, Scanner scanner) {
        // Kitap iade etme mantığını uygulayın
        System.out.print("Kullanıcı ID'sini girin: ");
        String userId = scanner.nextLine();
        System.out.print("Kitap ID'sini girin: ");
        String bookId = scanner.nextLine();

        User user = findUserById(library, userId);

        if (user != null) {
            library.returnBook(user, bookId);
        } else {
            System.out.println("Kullanıcı bulunamadı.");
        }
    }

    private static User findUserById(Library library, String userId) {
        for (User user : library.getUsers()) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}
