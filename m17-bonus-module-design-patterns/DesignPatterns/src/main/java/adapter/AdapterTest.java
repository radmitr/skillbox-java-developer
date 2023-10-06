package adapter;

import java.util.Date;
import java.util.List;

// Client
public class AdapterTest {

    public static void main(String[] args) {
        // service
        Customer customerService = new Customer();
        // adapter
        CustomerDataObject adapter = new CustomerDataObject(customerService);

        // client
        double callsPerMonth = adapter.getValue("callsPerMonth");
        double callsPerYear = adapter.getValue("callsPerYear");
        double minutesPerMonth = adapter.getValue("minutesPerMonth");
        System.out.println("callsPerMonth" + callsPerMonth);
        System.out.println("callsPerYear" + callsPerYear);
        System.out.println("minutesPerMonth" + minutesPerMonth);

        //-----------------------------------------------------------------------

        // service examples
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
