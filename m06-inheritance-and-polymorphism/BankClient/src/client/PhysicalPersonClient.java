package client;

public class PhysicalPersonClient extends AbstractClient {

    public PhysicalPersonClient(double bankAccount) {
        super(bankAccount);
        System.out.println("Открыт счёт для физического лица, ID=" + super.clientId +
                ". Баланс счёта: " + super.bankAccount);
    }

    @Override
    public void deposit(double sum) {
        if (sum > 0) {
            super.bankAccount += sum;
            System.out.println("Пополнение счёта ID=" + super.clientId +" на " + sum +
                    ". Баланс счёта: " + super.bankAccount);
        } else {
            System.out.println("Пополнение счёта невозможно. Некорректная сумма.");
        }
    }

    @Override
    public void withdraw(double sum) {
        if (sum > 0  && sum <= super.bankAccount) {
            super.bankAccount -= sum;
            System.out.println("Расход по счёту ID=" + super.clientId + " на " + sum +
                    ". Остаток на счёте: " + super.bankAccount);
        } else {
            System.out.println("Расход невозможен. Некорректная сумма.");
        }
    }
}
