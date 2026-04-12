import java.util.*;

public class AccountRequestManager {

    Queue<String> requests = new LinkedList<>();

    public void addRequest(String name) {
        requests.add(name);
        System.out.println("Request added");
    }

    public String processRequest() {
        return requests.poll();
    }

    public void showRequests() {
        System.out.println("Requests: " + requests);
    }
}