import client.IndividualEntrepreneurClient;
import client.LegalPersonClient;
import client.PhysicalPersonClient;

// Task 6.3
public class Main {

    public static void main(String[] args) {
        // 1 - Physical person client
        PhysicalPersonClient physicalPerson = new PhysicalPersonClient(100_000);

        physicalPerson.deposit(25_000);
        physicalPerson.withdraw(4_000);
        System.out.println(physicalPerson.balance());
        System.out.println(physicalPerson);
        System.out.println("--------------------------------------------------");

        // 2 - Legal person client
        LegalPersonClient legalPerson = new LegalPersonClient(2_000_000);

        legalPerson.deposit(500_000);
        legalPerson.withdraw(100_000);
        System.out.println("--------------------------------------------------");

        // 3 - Individual entrepreneur client
        IndividualEntrepreneurClient individualEntrepreneur = new IndividualEntrepreneurClient(200_000);

        individualEntrepreneur.deposit(500);
        individualEntrepreneur.deposit(1500);
        individualEntrepreneur.deposit(100_000);
        individualEntrepreneur.withdraw(25_000);
    }
}
