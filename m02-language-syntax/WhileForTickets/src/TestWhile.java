// Task 2.4
public class TestWhile {

    public static void main(String[] args) {
        int currentTicket = 200_000;
        int endTicket = 235_000;

        while (currentTicket <= endTicket) {
            if (currentTicket > 210_000 && currentTicket < 220_000) {
                currentTicket++;
                continue;
            }
            System.out.println("Ticket #" + currentTicket);
            currentTicket++;
        }
    }
}
