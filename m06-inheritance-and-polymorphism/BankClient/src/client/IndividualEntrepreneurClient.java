package client;

public class IndividualEntrepreneurClient extends AbstractClient {

    public static final double COMMISSION_IF_LESS_THRESHOLD = 1.0 / 100;
    public static final double COMMISSION_IF_MORE_THRESHOLD = 0.5 / 100;
    public static final double THRESHOLD_AMOUNT = 1000;

    public IndividualEntrepreneurClient(double bankAccount) {
        super(bankAccount);
        System.out.println("Открыт счёт для индивидуального предпринимателя, ID=" + super.clientId +
                ". Баланс счёта: " + super.bankAccount);
    }

    @Override
    public void deposit(double sum) {
        if (sum > 0) {
            if (sum < THRESHOLD_AMOUNT) {
                this.bankAccount += sum * (1 - COMMISSION_IF_LESS_THRESHOLD);
                System.out.println("Пополнение счёта ID=" + super.clientId +" на " + sum +
                        ", комиссия " + (COMMISSION_IF_LESS_THRESHOLD * 100) +
                        "%: " + (sum * COMMISSION_IF_LESS_THRESHOLD) +
                        ". Баланс счёта: " + super.bankAccount);
            } else {
                this.bankAccount += sum * (1 - COMMISSION_IF_MORE_THRESHOLD);
                System.out.println("Пополнение счёта ID=" + super.clientId +" на " + sum +
                        ", комиссия " + (COMMISSION_IF_MORE_THRESHOLD * 100) +
                        "%: " + (sum * COMMISSION_IF_MORE_THRESHOLD) +
                        ". Баланс счёта: " + super.bankAccount);
            }
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
