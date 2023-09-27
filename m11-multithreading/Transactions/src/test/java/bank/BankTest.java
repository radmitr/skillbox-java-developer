package bank;

import exceptions.AccountNotExistsException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.MAX_PRIORITY;
import static java.lang.Thread.NORM_PRIORITY;
import static org.junit.jupiter.api.Assertions.*;

class BankTest {

    /** Число потоков */
    private static final int NUMBER_OF_THREADS = 16;

    /** Число клиентов банка */
    private static final int NUMBER_OF_CLIENTS = 100_000;

    /** Количесвто операций для тестирования */
    private static final int NUMBER_OF_TRANSACTIONS = 10_000;

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

    private static Bank bank;

    @BeforeEach
    void setUp() {
        bank = createBank();
    }

    @Test
    void getAccount_whenNotExistId_thenThrowException() {
        assertThrows(AccountNotExistsException.class, () -> bank.getAccount("123"));
    }

    @Nested
    @DisplayName("Testing method: getAccountBalance()")
    class Method_GetAccountBalance {
        @Test
        public void getAccountBalance() {
            assertEquals(25_000, bank.getAccountBalance("1"));
        }

        @Test
        public void getAccountBalance_whenNonExistentAccount_thenReturnZero() {
            assertEquals(0, bank.getAccountBalance("123"));
        }

        @Test
        public void getAccountBalance_whenBlockedAccount_thenAnywayReturnBalance() {
            assertEquals(150_000, bank.getAccountBalance("5"));
        }
    }

    @Test
    public void getBankBalance() {
        assertEquals(1_215_000, bank.getBankBalance());
    }

    @Test
    public void getAccountIds() {
        List<String> expectedList = new ArrayList<>();
        expectedList.add("1");
        expectedList.add("2");
        expectedList.add("3");
        expectedList.add("4");
        expectedList.add("5");

        List<String> actualList = bank.getAccountIds();;

        assertEquals(expectedList, actualList);
    }

    @Test
    public void countBlocked() {
        assertEquals(1, bank.countBlocked());
    }

    @Nested
    @DisplayName("Testing method: transfer()")
    class Method_Transfer {
        @Test
        void transfer_whenSmallSum() {
            bank.setFraudPercent(0);

            bank.transfer("1", "2", 20_000);

            assertEquals(5_000, bank.getAccountBalance("1"));
            assertEquals(140_000, bank.getAccountBalance("2"));
        }

        @Test
        void transfer_whenLargeSumAndNotFraud() {
            bank.setFraudPercent(0);

            bank.transfer("2", "1", 70_000);

            assertEquals(95_000, bank.getAccountBalance("1"));
            assertEquals(50_000, bank.getAccountBalance("2"));
        }

        @Test
        void transfer_whenIdenticalAccounts_thenTransferFailed() {
            bank.setFraudPercent(0);

            bank.transfer("1", "1", 5_000);

            assertEquals(25_000, bank.getAccountBalance("1"));
        }

        @Test
        void transfer_whenFraud_thenTransferFailedAndBlockAccounts()
                throws AccountNotExistsException {
            bank.setFraudPercent(100);

            bank.transfer("2", "1", 70_000);

            assertEquals(25_000, bank.getAccountBalance("1"));
            assertEquals(120_000, bank.getAccountBalance("2"));
            assertTrue(bank.getAccount("1").isBlocked());
            assertTrue(bank.getAccount("2").isBlocked());
        }

        @Test
        void transfer_whenFromAccountBlocked_thenTransferFailed() {
            bank.setFraudPercent(0);

            bank.transfer("5", "3", 30_000);

            assertEquals(150_000, bank.getAccountBalance("5"));
            assertEquals(350_000, bank.getAccountBalance("3"));
        }

        @Test
        void transfer_whenToAccountBlocked_thenTransferFailed() {
            bank.setFraudPercent(0);

            bank.transfer("3", "5", 100_000);

            assertEquals(350_000, bank.getAccountBalance("3"));
            assertEquals(150_000, bank.getAccountBalance("5"));
        }

        @Test
        public void transfer_whenZeroSum_thenTransferFailed() {
            bank.setFraudPercent(0);

            bank.transfer("3","4",0);

            assertEquals(350_000, bank.getAccountBalance("3"));
            assertEquals(570_000, bank.getAccountBalance("4"));
        }

        @Test
        public void transfer_whenNegativeSum_thenTransferFailed() {
            bank.setFraudPercent(0);

            bank.transfer("3","4",-10_000);

            assertEquals(350_000, bank.getAccountBalance("3"));
            assertEquals(570_000, bank.getAccountBalance("4"));
        }

        @Test
        public void transfer_whenNotEnoughMoney_thenTransferFailed() {
            bank.setFraudPercent(0);

            bank.transfer("1","2",30_000);

            assertEquals(25_000, bank.getAccountBalance("1"));
            assertEquals(120_000, bank.getAccountBalance("2"));
        }

        @Test
        public void transfer_whenManyThreads_thenBankBalanceNotChanged() {
            bank = createBankWithRandomClients(NUMBER_OF_CLIENTS);
            long expectedBalance = bank.getBankBalance();

            try {
                executeRandomTransfers(bank, NUMBER_OF_TRANSACTIONS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            long actualBalance =  bank.getBankBalance();

            System.out.println("Количество заблокированных счетов: " + bank.countBlocked());
            assertEquals(expectedBalance, actualBalance);
        }
    }

    @AfterAll
    static void tearDownAll() {
        bank = null;
    }

    /**
     * Метод для создания нового банка
     */
    private static Bank createBank() {
        List<Account> accounts = new ArrayList<>();

        accounts.add(new Account("1", 25_000));
        accounts.add(new Account("2", 120_000));
        accounts.add(new Account("3", 350_000));
        accounts.add(new Account("4", 570_000));
        accounts.add(new Account("5", 150_000, true));
//        accounts.add(new Account("6", 1_250_000));
//        accounts.add(new Account("7", 210_000));
//        accounts.add(new Account("8", 100_000));
//        accounts.add(new Account("9", 500_510));
//        accounts.add(new Account("10", 730_000));

        return new Bank(accounts);
    }

    /**
     * Метод создаёт банк с заданным количеством случайных клиентов.
     */
    private static Bank createBankWithRandomClients(int numberOfClients) {
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
    private static long generateTransferSum() {
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

    /**
     * Совершает случайные транзакции
     */
    private static void executeRandomTransfers(Bank bank, int numberOfTransfers)
            throws InterruptedException {
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

        for (int i = 0; i < numberOfTransfers; i++) {
            executorService.execute(randomTransfer);
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.HOURS);
    }
}
