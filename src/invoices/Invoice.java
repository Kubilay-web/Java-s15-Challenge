package invoices;

public class Invoice {
    private String userId;
    private String bookId;
    private double amount;

    public Invoice(String userId, String bookId, double amount) {
        this.userId = userId;
        this.bookId = bookId;
        this.amount = amount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "userId='" + userId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
