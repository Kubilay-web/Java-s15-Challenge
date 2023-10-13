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

        System.out.println(books);


        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Kitap ödünç al");
            System.out.println("2. Kitap iade et");
            System.out.println("3. Yeni kitap ekle");
            System.out.println("4. Kitap bilgilerini güncelle");
            System.out.println("5. Kitap sil");
            System.out.println("6. Kategorideki kitapları listele");
            System.out.println("7. Yazarın kitaplarını listele");
            System.out.println("8. Çıkış");
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
                    addNewBook(library, scanner);
                    break;
                case 4:
                    updateBook(library, scanner);
                    break;
                case 5:
                    deleteBook(library, scanner);
                    break;
                case 6:
                    listBooksInCategory(library, scanner);
                    break;
                case 7:
                    listBooksByAuthor(library, scanner);
                    break;
                case 8:
                    System.out.println("Çıkılıyor!");
                    System.exit(0);
                default:
                    System.out.println("Geçersiz seçenek. Lütfen geçerli bir seçenek girin.");
            }
        }
    }

    private static void borrowBook(Library library, Scanner scanner) {
        System.out.print("Kullanıcı ID'sini girin: ");
        String userId = scanner.nextLine();
        System.out.print("Kitap ID'sini girin: ");
        String bookId = scanner.nextLine();

        User user = findUserById(library, userId);
        Book book = library.findBookById(bookId);

        if (user != null && book != null) {
            if (user.canBorrowMoreBooks()) {
                library.borrowBook(user, book);
                System.out.println("Kitap ödünç alındı.");
            } else {
                System.out.println("Kullanıcı 5 kitaplık sınırlamaya ulaştı, daha fazla kitap alamaz.");
            }
        } else {
            System.out.println("Kullanıcı veya kitap bulunamadı.");
        }
    }


    private static void returnBook(Library library, Scanner scanner) {
        System.out.print("Kullanıcı ID'sini girin: ");
        String userId = scanner.nextLine();
        System.out.print("Kitap ID'sini girin: ");
        String bookId = scanner.nextLine();

        User user = findUserById(library, userId);

        if (user != null) {
            library.returnBook(user, bookId);
            System.out.println("Kitap iade edildi.");
        } else {
            System.out.println("Kullanıcı bulunamadı.");
        }
    }


    private static void addNewBook(Library library, Scanner scanner) {
        System.out.print("Kitap ID'sini girin: ");
        String bookId = scanner.nextLine();
        System.out.print("Kitap Adı'nı girin: ");
        String bookTitle = scanner.nextLine();
        System.out.print("Yazar Adı'nı girin: ");
        String authorName = scanner.nextLine();
        System.out.print("Kategori Adı'nı girin: ");
        String categoryName = scanner.nextLine();

        Book newBook = new Book(bookId, bookTitle, authorName, categoryName);
        library.addBook(newBook);
        System.out.println("Yeni kitap eklendi.");
    }

    private static void updateBook(Library library, Scanner scanner) {
        // Kitap bilgilerini güncelleme mantığını uygulayın
        System.out.print("Güncellenecek Kitap ID'sini girin: ");
        String bookId = scanner.nextLine();
        Book bookToUpdate = library.findBookById(bookId);

        if (bookToUpdate != null) {
            System.out.print("Yeni Kitap Adı'nı girin: ");
            String newBookTitle = scanner.nextLine();
            System.out.print("Yeni Yazar Adı'nı girin: ");
            String newAuthorName = scanner.nextLine();
            System.out.print("Yeni Kategori Adı'nı girin: ");
            String newCategoryName = scanner.nextLine();

            bookToUpdate.setTitle(newBookTitle);
            bookToUpdate.setAuthor(newAuthorName);
            bookToUpdate.setCategory(newCategoryName);

            System.out.println("Kitap bilgileri güncellendi.");
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    private static void deleteBook(Library library, Scanner scanner) {
        System.out.print("Silinecek Kitap ID'sini girin: ");
        String bookId = scanner.nextLine();
        Book bookToDelete = library.findBookById(bookId);

        if (bookToDelete != null) {
            library.removeBook(String.valueOf(bookToDelete));
            System.out.println("Kitap silindi.");
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    private static void listBooksInCategory(Library library, Scanner scanner) {
        System.out.print("Kategori Adı'nı girin: ");
        String categoryName = scanner.nextLine();

        List<Book> booksInCategory = library.getBooksInCategory(categoryName);

        if (booksInCategory.size() > 0) {
            System.out.println("Kategori '" + categoryName + "' içindeki kitaplar:");
            for (Book book : booksInCategory) {
                System.out.println(book.getTitle());
            }
        } else {
            System.out.println("Belirtilen kategoride kitap bulunamadı.");
        }
    }

    private static void listBooksByAuthor(Library library, Scanner scanner) {
        System.out.print("Yazar Adı'nı girin: ");
        String authorName = scanner.nextLine();

        List<Book> booksByAuthor = library.getBooksByAuthor(authorName);

        if (booksByAuthor.size() > 0) {
            System.out.println("Yazar '" + authorName + "' tarafından yazılan kitaplar:");
            for (Book book : booksByAuthor) {
                System.out.println(book.getTitle());
            }
        } else {
            System.out.println("Belirtilen yazarın kitapları bulunamadı.");
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
