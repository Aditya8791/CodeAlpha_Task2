import java.time.LocalDateTime;

public class Transaction {
    private String stockSymbol;
    private int quantity;
    private double price;
    private boolean isBuy;
    private LocalDateTime date;

    public Transaction(String stockSymbol, int quantity, double price, boolean isBuy) {
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.isBuy = isBuy;
        this.date = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return (isBuy ? "BUY" : "SELL") + " " + quantity + " shares of " + stockSymbol +
               " @ ₹" + price + " on " + date.toString();
    }
}
