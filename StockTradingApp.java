import java.util.*;

public class StockTradingApp {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, Stock> stockMarket = new HashMap<>();
    static User currentUser;

    public static void main(String[] args) {
        initializeMarket();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        currentUser = new User(name, 100000); // ₹100,000 initial balance

        while (true) {
            System.out.println("\n📈 STOCK TRADING MENU");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> viewMarket();
                case 2 -> buyStock();
                case 3 -> sellStock();
                case 4 -> currentUser.viewPortfolio(stockMarket);
                case 5 -> currentUser.viewTransactions();
                case 6 -> {
                    System.out.println("📤 Exiting... Thank you!");
                    return;
                }
                default -> System.out.println("❌ Invalid choice.");
            }
        }
    }

    static void initializeMarket() {
        stockMarket.put("TCS", new Stock("TCS", "Tata Consultancy", 3800.0));
        stockMarket.put("INFY", new Stock("INFY", "Infosys", 1550.0));
        stockMarket.put("RELI", new Stock("RELI", "Reliance", 2800.0));
        stockMarket.put("HDFC", new Stock("HDFC", "HDFC Bank", 1650.0));
    }

    static void viewMarket() {
        System.out.println("📊 Market Stocks:");
        for (Stock stock : stockMarket.values()) {
            System.out.println(stock);
        }
    }

    static void buyStock() {
        System.out.print("Enter stock symbol to buy: ");
        String symbol = scanner.next().toUpperCase();
        System.out.print("Enter quantity: ");
        int qty = scanner.nextInt();
        Stock stock = stockMarket.get(symbol);
        if (stock != null) {
            currentUser.buyStock(stock, qty);
        } else {
            System.out.println("❌ Invalid stock symbol.");
        }
    }

    static void sellStock() {
        System.out.print("Enter stock symbol to sell: ");
        String symbol = scanner.next().toUpperCase();
        System.out.print("Enter quantity: ");
        int qty = scanner.nextInt();
        Stock stock = stockMarket.get(symbol);
        if (stock != null) {
            currentUser.sellStock(stock, qty);
        } else {
            System.out.println("❌ Invalid stock symbol.");
        }
    }
}
