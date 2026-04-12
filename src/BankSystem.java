import java.util.Scanner;

public class BankSystem {

    AccountManager accountManager = new AccountManager();
    TransactionManager transactionManager = new TransactionManager();
    BillQueueManager billQueueManager = new BillQueueManager();
    AccountRequestManager requestManager = new AccountRequestManager();

    Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n1 - Bank");
            System.out.println("2 - ATM");
            System.out.println("3 - Admin");
            System.out.println("4 - Exit");

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
                    return;
            }
        }
    }

    void bankMenu() {
        System.out.println("\n1 - Request Account");
        System.out.println("2 - Deposit");
        System.out.println("3 - Withdraw");

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
        System.out.println("3 - Bills");
        System.out.println("4 - Process bill");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                String name = requestManager.processRequest();
                if (name != null) {
                    accountManager.accounts.add(
                            new BankAccount(accountManager.accounts.size()+1, name, 0)
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
        }
    }
}