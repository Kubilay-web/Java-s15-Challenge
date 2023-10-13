package users;

import library.Book;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private int borrowedBookCount;
    private List<Book> borrowedBooks;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBookCount = 0;
        borrowedBooks = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBorrowedBookCount() {
        return borrowedBookCount;
    }

    public void setBorrowedBookCount(int borrowedBookCount) {
        this.borrowedBookCount = borrowedBookCount;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (borrowedBookCount < 5) {
            if (book.isAvailable()) {
                borrowedBooks.add(book);
                borrowedBookCount++;
                book.setAvailable(false);
                System.out.println(name + " borrowed the book: " + book.getTitle());
            } else {
                System.out.println("Sorry, the book is already borrowed by someone else.");
            }
        } else {
            System.out.println("Sorry, you have reached the maximum borrowing limit.");
        }
    }


    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            borrowedBookCount--;
            book.setAvailable(true);
            System.out.println(name + " returned the book: " + book.getTitle());
        } else {
            System.out.println("You cannot return a book you didn't borrow.");
        }
    }

    public boolean canBorrowMoreBooks() {
        return borrowedBooks.size() < 5;
    }
}
