import java.util.*;

public class User {
    private String name;
    private double balance;
    private Map<String, Integer> portfolio;
    private List<Transaction> transactions;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.portfolio = new HashMap<>();
        this.transactions = new ArrayList<>();
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (cost > balance) {
            System.out.println("❌ Not enough balance to complete the purchase.");
            return;
        }
        balance -= cost;
        portfolio.put(stock.getSymbol(), portfolio.getOrDefault(stock.getSymbol(), 0) + quantity);
        transactions.add(new Transaction(stock.getSymbol(), quantity, stock.getPrice(), true));
        System.out.println("✅ Bought " + quantity + " shares of " + stock.getSymbol());
    }

    public void sellStock(Stock stock, int quantity) {
        int owned = portfolio.getOrDefault(stock.getSymbol(), 0);
        if (quantity > owned) {
            System.out.println("❌ Not enough shares to sell.");
            return;
        }
        balance += stock.getPrice() * quantity;
        if (quantity == owned)
            portfolio.remove(stock.getSymbol());
        else
            portfolio.put(stock.getSymbol(), owned - quantity);

        transactions.add(new Transaction(stock.getSymbol(), quantity, stock.getPrice(), false));
        System.out.println("✅ Sold " + quantity + " shares of " + stock.getSymbol());
    }

    public void viewPortfolio(Map<String, Stock> stockMarket) {
        System.out.println("💼 Portfolio:");
        double totalValue = 0;
        for (String symbol : portfolio.keySet()) {
            int qty = portfolio.get(symbol);
            double price = stockMarket.get(symbol).getPrice();
            double value = qty * price;
            totalValue += value;
            System.out.println(symbol + ": " + qty + " shares @ ₹" + price + " = ₹" + value);
        }
        System.out.println("💰 Balance: ₹" + balance);
        System.out.println("📊 Total Portfolio Value: ₹" + (totalValue + balance));
    }

    public void viewTransactions() {
        System.out.println("📃 Transaction History:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}
