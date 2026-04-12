import java.util.*;

public class BillQueueManager {

    Queue<String> bills = new LinkedList<>();

    public void addBill(String bill) {
        bills.add(bill);
        System.out.println("Added: " + bill);
    }

    public void processBill() {
        if (!bills.isEmpty()) {
            System.out.println("Processing: " + bills.poll());
        } else {
            System.out.println("No bills");
        }
    }

    public void showBills() {
        System.out.println("Queue: " + bills);
    }
}