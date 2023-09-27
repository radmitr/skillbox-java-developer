import bank.Account;
import bank.Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.*;

// Task 11.2
public class MainDemo {

    /** Число потоков */
    private static final int NUMBER_OF_THREADS = 16;

    /** Число клиентов банка */
    private static final int NUMBER_OF_CLIENTS = 100_000;

    /** Количесвто операций для тестирования */
    private static final int NUMBER_OF_TRANSACTIONS = 1_000_000;

    /** Миниальный начальный генерируемый баланс на счёте */
    private static final int MIN_START_ACCOUNT_SUM = 30_000;

    /** Максимальный начальный генерируемый баланс на счёте */
    private static final int MAX_START_ACCOUNT_SUM = 300_000;

    /** Минимальная сумма транзакции */
    private static final long MIN_TRANSFER_SUM = 500;

    /** Максимальная сумма транзакции */
    private static final long MAX_TRANSFER_SUM = 100_000;

    /** Процент транзакций, которые превышают пороговую сумму */
    private static final double BIG_TRANSFER_SUM_PERCENT = 2.5;

    public static void main(String[] args) throws InterruptedException {
        Bank bank = createBankWithRandomClients(NUMBER_OF_CLIENTS);

        long startBalance = bank.getBankBalance();
        System.out.println("Сумма всех средств на всех счетах банка: " + startBalance + " RUR");

        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        List<String> accountIdList = bank.getAccountIds();

        // создаём случайную транзакцию
        Runnable randomTransfer = () -> {
            int idx1 = ThreadLocalRandom.current().nextInt(accountIdList.size());
            String id1 = accountIdList.get(idx1);

            int idx2 = ThreadLocalRandom.current().nextInt(accountIdList.size());
            String id2 = accountIdList.get(idx2);

            long sum = generateTransferSum();

            // устанавливаем масимальный приоритет для операций с большой суммой
            if (sum > Bank.THRESHOLD_SUM_TO_CHECK) {
                Thread.currentThread().setPriority(MAX_PRIORITY);
            } else {
                Thread.currentThread().setPriority(NORM_PRIORITY);
            }

            bank.transfer(id1, id2, sum);
        };

        long startTime = System.currentTimeMillis();

        ////////////////////////////// Запускаем транзакции //////////////////////////////
        for (int i = 0; i < NUMBER_OF_TRANSACTIONS; i++) {
            executorService.execute(randomTransfer);
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.HOURS);
        //////////////////////////////////////////////////////////////////////////////////

        long endTime = System.currentTimeMillis();
        long endBalance =  bank.getBankBalance();

        System.out.println("Сумма всех средств на всех счетах банка: " + endBalance + " RUR");
        System.out.println("Ошибка в банковских транзакциях: " + (startBalance - endBalance) + " RUR");
        System.out.println("Количество счетов: " + NUMBER_OF_CLIENTS);
        System.out.println("Количество заблокированных счетов: " + bank.countBlocked());
        System.out.println("Количество потоков: " + NUMBER_OF_THREADS);
        System.out.println("Количество транзакций: " + NUMBER_OF_TRANSACTIONS);
        System.out.println("Время работы: " + (endTime - startTime) / 1000 + " сек.");
    }

    /**
     * Метод создаёт банк с заданным количеством случайных клиентов.
     */
    public static Bank createBankWithRandomClients(int numberOfClients) {
        Bank bank = new Bank();
        if (numberOfClients < 1) {
            return bank;
        }
        List<Account> accountList = new ArrayList<>(numberOfClients);

        for (int i = 0; i < numberOfClients; i++) {
            String id = String.valueOf(System.nanoTime());
            accountList.add(new Account(id, ThreadLocalRandom.current()
                    .nextLong(MIN_START_ACCOUNT_SUM, MAX_START_ACCOUNT_SUM)));
        }

        bank.addAccounts(accountList);
        return bank;
    }

    /**
     * Генерирует случайную сумму для перевода.
     */
    public static long generateTransferSum() {
        double randomPercent = ThreadLocalRandom.current().nextDouble(0,101);

        long sum;
        if (randomPercent <= BIG_TRANSFER_SUM_PERCENT) {
            // big transaction sum
            sum = ThreadLocalRandom.current().nextLong(Bank.THRESHOLD_SUM_TO_CHECK + 1, MAX_TRANSFER_SUM + 1);
        } else {
            // small transaction sum
            sum = ThreadLocalRandom.current().nextLong(MIN_TRANSFER_SUM, Bank.THRESHOLD_SUM_TO_CHECK + 1);
        }
        return sum;
    }
}
