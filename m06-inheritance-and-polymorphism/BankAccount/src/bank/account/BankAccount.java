package bank.account;

public class BankAccount {

    protected String accountId;
    protected double amount;
    public final String CURRENCY = "RUR";

    public BankAccount(double amount) {
        this.accountId = String.valueOf(System.nanoTime());
        if (amount > 0) {
            this.amount = amount;
        } else {
            this.amount = 0;
        }
        startMessage();
    }

    protected void startMessage() {
        System.out.println("Открыт расчётный " + CURRENCY + " счёт №" + accountId +
                ". Баланс: " + amount + " " + CURRENCY);
    }

    public String getAccountId() {
        return accountId;
    }

//    public void setAccountId(String accountId) {
//        this.accountId = accountId;
//    }

    public double balance() {
        return amount;
    }

    public void deposit(double sum) {
        if (sum > 0) {
            amount += sum;
            System.out.println("Счёт №" + accountId + " пополнен на сумму " + sum + " " + CURRENCY +
                    ". Баланс средств после операции: " + amount + " " + CURRENCY);
        } else {
            System.out.println("Не удалось пополнить счёт №" + accountId + "." +
                    "Некорректная сумма.");
        }
    }

    public void withdraw(double sum) {
        if (sum > 0 && amount >= sum) {
            amount -= sum;
            System.out.println("Со счёта №" + accountId + " снята сумма " + sum + " " + CURRENCY +
                    ". Остаток средств после операции: " + amount + " " + CURRENCY);
        } else {
            System.out.println("Не удалось снять средства со счёте №" + accountId +
                    ". Некорректная сумма.");
        }
    }

    public boolean send(BankAccount recipient, double sum) {
        if (sum > 0 && this.amount > 0 && this.amount > sum) {
            amount -= sum;
            recipient.deposit(sum);
            System.out.println("Средства со счёта №" + accountId + " в размере "+ sum + " " + CURRENCY +
                    " успешно переведены на счёт №" + recipient.getAccountId() +
                    ". Остаток средств после операции: " + amount + " " + CURRENCY);
            return true;
        } else {
            System.out.println("Не удалось перевести средства со счёта №" + accountId +
                    " на счёт №" + recipient.getAccountId() +
                    ". Недостаточная сумма средств.");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Остаток средств на счёте №" + accountId +
                " составляет " + amount + " " + CURRENCY;
    }
}
