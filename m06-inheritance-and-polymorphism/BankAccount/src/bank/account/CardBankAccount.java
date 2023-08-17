package bank.account;

public class CardBankAccount extends BankAccount {

    public static final double COMMISSION = 1.0 / 100;

    public CardBankAccount(double amount) {
        super(amount);
    }

    @Override
    protected void startMessage() {
        System.out.println("Открыт карточный " + CURRENCY + " счёт №" + super.accountId +
                ". Баланс: " + super.amount + " " + CURRENCY);
    }

    @Override
    public void withdraw(double sum) {
        super.withdraw(sum * (1 + COMMISSION));
    }

    public boolean send(BankAccount recipient, double sum) {
        return super.send(recipient,sum * (1 + COMMISSION));
    }
}
