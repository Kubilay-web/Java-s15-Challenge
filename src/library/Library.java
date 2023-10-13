package library;
import categories.Category;
import invoices.Invoice;
import users.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Library {
    private List<Book> books;
    private Set<User> users;
    private Map<String, User> borrowedBooks;
    private List<Category> categories = new ArrayList<>();

    public Library(List<Book> books, Set<User> users) {
        this.books = books;
        this.users = users;
        this.borrowedBooks = new HashMap<>();
    }


    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String bookId) {
        Book book = findBookById(bookId);
        if (book != null) {
            books.remove(book);
        }
    }

    public Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> findBooksByAuthor(String authorName) {
        List<Book> authorBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(authorName)) {
                authorBooks.add(book);
            }
        }
        return authorBooks;
    }

    public List<Book> findBooksInCategory(String categoryName) {
        List<Book> categoryBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equals(categoryName)) {
                categoryBooks.add(book);
            }
        }
        return categoryBooks;
    }

    public void borrowBook(User user, Book book) {
        if (user.getBorrowedBookCount() < 5) {
            book.setAvailable(false);
            user.borrowBook(book);
            borrowedBooks.put(book.getId(), user);
            // Fatura kesme işlemi
            double fee = calculateBorrowFee(book);
            Invoice invoice = new Invoice(user.getUserId(), book.getId(), fee);
            System.out.println(user.getName() + " kitap ödünç aldı. Ödünç alma ücreti: " + fee + " TL");
        } else {
            System.out.println(user.getName() + " maksimum kitap ödünç aldı.");
        }
    }

    public void returnBook(User user, String bookId) {
        Book book = findBookById(bookId);
        if (book != null && borrowedBooks.containsKey(book.getId()) && borrowedBooks.get(book.getId()) == user) {
            book.setAvailable(true);
            user.returnBook(book);
            borrowedBooks.remove(book.getId());
        } else {
            System.out.println("Kitap iade edilemedi.");
        }
    }

    private double calculateBorrowFee(Book book) {
        return 5.0;
    }

    public User[] getUsers() {
        return users.toArray(new User[0]);
    }

    public List<Book> getBooksByAuthor(String authorName) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : this.books) {
            if (book.getAuthor().equals(authorName)) {
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }

    public Category[] getCategories() {
        return categories.toArray(new Category[0]);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }

    public List<Book> getBooksInCategory(String categoryName) {
        List<Book> booksInCategory = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equals(categoryName)) {
                booksInCategory.add(book);
            }
        }
        return booksInCategory;
    }
}
