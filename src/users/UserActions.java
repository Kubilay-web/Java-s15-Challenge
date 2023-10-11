package users;

import library.Book;

public interface UserActions {
    void borrowBook(User user, Book book);
    void returnBook(User user, Book book);
}
