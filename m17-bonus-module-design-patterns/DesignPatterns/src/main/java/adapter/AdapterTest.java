package adapter;

import java.util.Date;
import java.util.List;

public class AdapterTest {

    public static void main(String[] args) {
        // Service
        Customer customerService = new Customer();
        // Adapter
        CustomerDataObject adapter = new CustomerDataObject(customerService);

        // Client
        double callsPerMonth = adapter.getValue("callsPerMonth");
        double callsPerYear = adapter.getValue("callsPerYear");
        double minutesPerMonth = adapter.getValue("minutesPerMonth");
        System.out.println("callsPerMonth" + callsPerMonth);
        System.out.println("callsPerYear" + callsPerYear);
        System.out.println("minutesPerMonth" + minutesPerMonth);

        //-----------------------------------------------------------------------

        // Service examples
        Date now = new Date();
        Date someTimeAgo = new Date(now.getTime() - 1000);
        int callsCount = customerService.getCallsCount(now, someTimeAgo);
        System.out.println("callsCount: " + callsCount);

        List<Call> calls = customerService.getCalls(now, someTimeAgo);
        int minutes = 0;
        for (Call call : calls) {
            minutes += call.getDuration();
        }
        System.out.println("minutes: " + minutes);
    }
}
