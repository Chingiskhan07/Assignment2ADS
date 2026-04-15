import java.util.*;

public class TransactionManager {

    Stack<String> history = new Stack<>();

    public void addTransaction(String action) {
        history.push(action);
    }

    public void undo() {
        if (!history.isEmpty()) {
            System.out.println("Undo: " + history.pop());
        } else {
            System.out.println("No transactions");
        }
    }

    public void showLast() {
        if (!history.isEmpty()) {
            System.out.println("Last: " + history.peek());
        } else {
            System.out.println("No transactions");
        }
    }
}