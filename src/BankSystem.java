import java.util.Scanner;

public class BankSystem {

    AccountManager accountManager = new AccountManager();
    TransactionManager transactionManager = new TransactionManager();
    BillQueueManager billQueueManager = new BillQueueManager();
    AccountRequestManager requestManager = new AccountRequestManager();

    BankAccount[] fixedAccounts = {
            new BankAccount(1, "Ali", 100000),
            new BankAccount(2, "Sara", 200000),
            new BankAccount(3, "John", 150000)
    };

    Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n1 - Bank");
            System.out.println("2 - ATM");
            System.out.println("3 - Admin");
            System.out.println("4 - Show Fixed Accounts");
            System.out.println("5 - Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    bankMenu();
                    break;
                case 2:
                    atmMenu();
                    break;
                case 3:
                    adminMenu();
                    break;
                case 4:
                    showFixedAccounts();
                    break;
                case 5:
                    return;
            }
        }
    }

    void bankMenu() {
        System.out.println("\n1 - Request Account");
        System.out.println("2 - Deposit");
        System.out.println("3 - Withdraw");
        System.out.println("4 - Show Accounts");
        System.out.println("5 - Add Bill");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                requestManager.addRequest(name);
                break;
            case 2:
                accountManager.deposit(transactionManager);
                break;
            case 3:
                accountManager.withdraw(transactionManager);
                break;
            case 4:
                accountManager.showAccounts();
                break;
            case 5:
                System.out.print("Enter bill: ");
                String bill = scanner.nextLine();
                billQueueManager.addBill(bill);
                break;
        }
    }

    void atmMenu() {
        System.out.print("Enter username: ");
        String name = scanner.nextLine();

        BankAccount acc = accountManager.findAccount(name);

        if (acc == null) {
            System.out.println("Not found");
            return;
        }

        System.out.println("1 - Balance");
        System.out.println("2 - Withdraw");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.println("Balance: " + acc.balance);
        } else if (choice == 2) {
            accountManager.withdraw(transactionManager);
        }
    }

    void adminMenu() {
        System.out.println("\n1 - Process account request");
        System.out.println("2 - Show requests");
        System.out.println("3 - Show bills");
        System.out.println("4 - Process bill");
        System.out.println("5 - Show last transaction");
        System.out.println("6 - Undo transaction");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                String name = requestManager.processRequest();
                if (name != null) {
                    accountManager.accounts.add(
                            new BankAccount(accountManager.accounts.size() + 1, name, 0)
                    );
                    System.out.println("Account created for " + name);
                }
                break;
            case 2:
                requestManager.showRequests();
                break;
            case 3:
                billQueueManager.showBills();
                break;
            case 4:
                billQueueManager.processBill();
                break;
            case 5:
                transactionManager.showLast();
                break;
            case 6:
                transactionManager.undo();
                break;
        }
    }

    void showFixedAccounts() {
        for (BankAccount acc : fixedAccounts) {
            System.out.println(acc.username + " - " + acc.balance);
        }
    }
}