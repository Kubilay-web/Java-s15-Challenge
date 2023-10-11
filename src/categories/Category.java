package categories;

import library.Book;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;


    private List<Book> booksInCategory;

    public Category(String name) {
        this.name = name;
        booksInCategory = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooksInCategory() {
        return booksInCategory;
    }

    public void setBooksInCategory(List<Book> booksInCategory) {
        this.booksInCategory = booksInCategory;
    }

    public void addBookToCategory(Book book) {
        booksInCategory.add(book);
    }

    public void removeBookFromCategory(Book book) {
        booksInCategory.remove(book);
    }
}
