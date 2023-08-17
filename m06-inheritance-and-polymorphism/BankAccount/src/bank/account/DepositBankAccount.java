package bank.account;

import java.time.LocalDate;

public class DepositBankAccount extends BankAccount {

    public static final long MONTHS_ALLOW_WITHDRAW = 1;
    private LocalDate lastDepositDate;

    public DepositBankAccount(double amount) {
        super(amount);
        lastDepositDate = LocalDate.now();
    }

    @Override
    protected void startMessage() {
        System.out.println("Открыт депозитный " + CURRENCY + " счёт №" + super.accountId +
                ". Баланс: " + super.amount + " " + CURRENCY);
    }

    @Override
    public void deposit(double sum) {
        super.deposit(sum);
    }

    @Override
    public void withdraw(double sum) {
        if (LocalDate.now().isAfter(lastDepositDate.plusMonths(MONTHS_ALLOW_WITHDRAW))) {
            super.withdraw(sum);
        } else {
            System.out.println("Не удалось снять средства со счёта №" + super.accountId +
                    ". Депозитный расчётный счёт, с которого нельзя снимать деньги" +
                    " в течение месяца после последнего внесения.");
        }
    }

    public void withdraw(double sum, LocalDate dateOfOperation) {
        if (dateOfOperation.isAfter(lastDepositDate.plusMonths(MONTHS_ALLOW_WITHDRAW))) {
            super.withdraw(sum);
        } else {
            System.out.println("Не удалось снять средства со счёте №" + super.accountId +
                    ". Депозитный расчётный счёт, с которого нельзя снимать деньги" +
                    " в течение месяца после последнего внесения.");
        }
    }

    @Override
    public boolean send(BankAccount recipient, double sum) {
        if (LocalDate.now().isAfter(lastDepositDate.plusMonths(MONTHS_ALLOW_WITHDRAW))) {
            return super.send(recipient, sum);
        } else {
            System.out.println("Не удалось перевести средства со счёта №" + super.accountId +
                    " на счёт №" + recipient.getAccountId() +
                    ". Депозитный расчётный счёт, с которого нельзя снимать деньги" +
                    " в течение месяца после последнего внесения.");
            return false;
        }
    }

    public boolean send(BankAccount recipient, double sum, LocalDate dateOfOperation) {
        if (dateOfOperation.isAfter(lastDepositDate.plusMonths(MONTHS_ALLOW_WITHDRAW))) {
            return super.send(recipient, sum);
        } else {
            System.out.println("Не удалось перевести средства со счете №" + super.accountId +
                    " на счёт №" + recipient.getAccountId() +
                    ". Депозитный расчётный счёт, с которого нельзя снимать деньги" +
                    " в течение месяца после последнего внесения.");
            return false;
        }
    }
}
