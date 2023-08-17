package client;

public class LegalPersonClient extends AbstractClient {

    public static final double COMMISSION = 1.0 / 100;

    public LegalPersonClient(double bankAccount) {
        super(bankAccount);
        System.out.println("Открыт счёт для юридического лица, ID=" + super.clientId +
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
            super.bankAccount -= sum * (1 + COMMISSION);
            System.out.println("Расход по счёту ID=" + super.clientId + " на " + sum +
                    ", комиссия " + (COMMISSION * 100) + "%: " + (sum * COMMISSION) +
                    ". Остаток на счёте: " + super.bankAccount);
        } else {
            System.out.println("Расходне возможен. Некорректная сумма.");
        }
    }
}
