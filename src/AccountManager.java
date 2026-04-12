import java.util.*;

public class AccountManager {

    LinkedList<BankAccount> accounts = new LinkedList<>();
    Scanner scanner = new Scanner(System.in);

    public void addAccount() {
        System.out.print("Enter account number: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter username: ");
        String name = scanner.nextLine();

        System.out.print("Enter balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine();

        accounts.add(new BankAccount(id, name, balance));
        System.out.println("Account added!");
    }

    public void showAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts.");
            return;
        }

        for (BankAccount acc : accounts) {
            System.out.println(acc.username + " - " + acc.balance);
        }
    }

    public BankAccount findAccount(String username) {
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(username)) {
                return acc;
            }
        }
        return null;
    }

    public void deposit(TransactionManager tm) {
        System.out.print("Enter username: ");
        String name = scanner.nextLine();

        BankAccount acc = findAccount(name);

        if (acc == null) {
            System.out.println("Not found!");
            return;
        }

        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        acc.balance += amount;
        tm.addTransaction("Deposit " + amount + " to " + name);

        System.out.println("New balance: " + acc.balance);
    }

    public void withdraw(TransactionManager tm) {
        System.out.print("Enter username: ");
        String name = scanner.nextLine();

        BankAccount acc = findAccount(name);

        if (acc == null) {
            System.out.println("Not found!");
            return;
        }

        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        if (acc.balance >= amount) {
            acc.balance -= amount;
            tm.addTransaction("Withdraw " + amount + " from " + name);
            System.out.println("New balance: " + acc.balance);
        } else {
            System.out.println("Not enough money!");
        }
    }
}