import bank.account.BankAccount;
import bank.account.CardBankAccount;
import bank.account.DepositBankAccount;

import java.time.LocalDate;

// Task 6.1.1
// Task 6.1.2
// Task 6.2
public class Main {

    public static void main(String[] args) {
        // Part 1 - BankAccount
        BankAccount myAccount = new BankAccount(500_000.0);
        BankAccount partnerAccount = new BankAccount(1_700_000.0);

        myAccount.deposit(130_000);
        myAccount.withdraw(25_000);
        myAccount.send(partnerAccount, 170_000);
        System.out.println("-----------------------------------------------");

        // Part 2 - DepositBankAccount
        DepositBankAccount depositAccount = new DepositBankAccount(500_000);

        depositAccount.deposit(200_000);
        depositAccount.withdraw(50_000); // не прошло достаточно времени для операции
        depositAccount.withdraw(50_000, LocalDate.now().plusDays(32)); // успешная операция
        depositAccount.withdraw(125_000, LocalDate.now().plusMonths(2)); // успешная операция

        depositAccount.send(myAccount, 75_500); // yе прошло достаточно времени для операции
        depositAccount.send(myAccount, 75_500, LocalDate.now().plusMonths(12)); // успешная операция
        System.out.println("-----------------------------------------------");

        // Part 3 - CardBankAccount
        CardBankAccount cardAccount = new CardBankAccount(0);

        cardAccount.withdraw(1_000); // операция не прошла
        cardAccount.deposit(5_000);
        cardAccount.withdraw(10_000_000); // операция не прошла
        cardAccount.send(partnerAccount,4_500);
        System.out.println("-----------------------------------------------");

        System.out.println(myAccount);
        System.out.println(partnerAccount);
        System.out.println(depositAccount);
        System.out.println(cardAccount);
    }
}
