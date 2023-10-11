package authors;
import library.Book;
import java.util.ArrayList;
import java.util.List;

public class Author {
    private String name;

    private List<Book> booksByAuthor;

    public Author(String name) {
        this.name = name;
        booksByAuthor = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooksByAuthor() {
        return booksByAuthor;
    }

    public void setBooksByAuthor(List<Book> booksByAuthor) {
        this.booksByAuthor = booksByAuthor;
    }

    public void addBookByAuthor(Book book) {
        booksByAuthor.add(book);
    }

    public void removeBookByAuthor(Book book) {
        booksByAuthor.remove(book);
    }
}
