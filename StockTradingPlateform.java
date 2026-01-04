import java.util.ArrayList;
import java.util.Scanner;

// Stock class
class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// Portfolio item
class PortfolioItem {
    Stock stock;
    int quantity;

    PortfolioItem(Stock stock, int quantity) {
        this.stock = stock;
        this.quantity = quantity;
    }
}

// User class
class User {
    double balance;
    ArrayList<PortfolioItem> portfolio = new ArrayList<>();

    User(double balance) {
        this.balance = balance;
    }
}

public class StockTradingPlateform {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Market stocks
        ArrayList<Stock> market = new ArrayList<>();
        market.add(new Stock("TATA", 850));
        market.add(new Stock("INFY", 1450));
        market.add(new Stock("RELIANCE", 2500));
        market.add(new Stock("HDFC", 1650));

        User user = new User(10000);

        int choice;
        do {
            System.out.println("\n📊 STOCK TRADING PLATFORM");
            System.out.println("1. View Market Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewMarket(market);
                    break;
                case 2:
                    buyStock(user, market);
                    break;
                case 3:
                    sellStock(user);
                    break;
                case 4:
                    viewPortfolio(user);
                    break;
                case 5:
                    System.out.println("Thank you for trading!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }

    // View market stocks
    static void viewMarket(ArrayList<Stock> market) {
        System.out.println("\n📈 Market Stocks:");
        for (int i = 0; i < market.size(); i++) {
            System.out.println((i + 1) + ". " + market.get(i).name +
                    " - ₹" + market.get(i).price);
        }
    }

    // Buy stock
    static void buyStock(User user, ArrayList<Stock> market) {
        viewMarket(market);

        System.out.print("Select stock number: ");
        int choice = sc.nextInt() - 1;

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        Stock selected = market.get(choice);
        double cost = selected.price * qty;

        if (cost > user.balance) {
            System.out.println("❌ Insufficient balance!");
            return;
        }

        user.balance -= cost;
        user.portfolio.add(new PortfolioItem(selected, qty));

        System.out.println("✅ Stock purchased successfully!");
    }

    // Sell stock
    static void sellStock(User user) {
        if (user.portfolio.isEmpty()) {
            System.out.println("Portfolio is empty!");
            return;
        }

        System.out.println("\nYour Portfolio:");
        for (int i = 0; i < user.portfolio.size(); i++) {
            PortfolioItem p = user.portfolio.get(i);
            System.out.println((i + 1) + ". " + p.stock.name +
                    " - Qty: " + p.quantity);
        }

        System.out.print("Select stock to sell: ");
        int choice = sc.nextInt() - 1;

        PortfolioItem item = user.portfolio.remove(choice);
        double amount = item.stock.price * item.quantity;

        user.balance += amount;
        System.out.println("✅ Stock sold successfully!");
    }

    // View portfolio
    static void viewPortfolio(User user) {
        System.out.println("\n💼 PORTFOLIO");
        if (user.portfolio.isEmpty()) {
            System.out.println("No stocks owned.");
        } else {
            for (PortfolioItem p : user.portfolio) {
                System.out.println(p.stock.name +
                        " | Qty: " + p.quantity +
                        " | Value: ₹" + (p.stock.price * p.quantity));
            }
        }
        System.out.println("Available Balance: ₹" + user.balance);
    }
}

