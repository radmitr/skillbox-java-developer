package bank;

import exceptions.*;
import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Реализация банка и операций по счетам.
 */
@Log4j2
public class Bank {

    /** Пороговая суммы средств для прохождения проверки в Службе Безопасности */
    public static final long THRESHOLD_SUM_TO_CHECK = 50_000;

    /** Время выполнения транзакции */
    private static final int TRANSFER_DELAY_MS = 0;

    /** Время проверки транзакции в Службе Безопасности */
    private static final int SECURITY_SERVICE_DELAY_MS = 10;

    /** Количество итераций задержки на цикле в транзакциях */
    private static final double TRANSFER_DELAY_COUNT = 1_000;

    /** Количество итераций задержки на цикле в Службе Безопасности */
    private static final double SECURITY_SERVICE_DELAY_COUNT = 10_000;

    /* Разрешает задержку на цикле в транзакциях */
    private static final boolean ENABLE_TRANSFER_LOOP_DELAY = true;

    /** Процент мошенников по умолчанию (для транзакций с большой суммой) */
    private static final double DEFAULT_FRAUD_PERCENT = 8.5;

    /** Процент мошенников (для транзакций с большой суммой) */
    private static double fraudPercent = DEFAULT_FRAUD_PERCENT;

    /** Карта всех счетов банка */
    private Map<String, Account> accounts = new HashMap<>();

    /** Volatile-счётчик проверенных транзакций */
    private volatile int checkCount = 0;

    /** Переменная для работы холостой задержки в транзакциях */
    private volatile long tempCount = 0;

    public Bank() {
    }

    public Bank(Map<String, Account> accountMap) {
        accounts = accountMap;
    }

    public Bank(List<Account> accountList) {
        accounts = accountList.stream()
                .collect(Collectors.toMap(
                        a -> a.getAccountId(),
                        a -> a,
                        (oldValue, newValue) -> oldValue,
                        HashMap::new));
    }

    /**
     * Метод добавляет счёт в карту счетов банка.
     */
    public void addAccount(Account account) {
        accounts.putIfAbsent(account.getAccountId(), account);
    }

    /**
     * Метод находит и возвращает счёт по его номеру.
     */
    public Account getAccount(String accountId) throws AccountNotExistsException {
        if (isAccountExists(accountId)) {
            return accounts.get(accountId);
        } else {
            throw new AccountNotExistsException(accountId);
        }
    }

    /**
     * Метод добавляет некоторую карту счетов в карту счетов банка.
     */
    public void addAccounts(Map<String, Account> accountMap) {
        // 1
        accountMap.forEach((k, v) -> accounts.putIfAbsent(k, v));
        // 2
//        accountMap.forEach(accounts::putIfAbsent);
        // 3
//        for (Map.Entry<String, bank.Account> entry : accountMap.entrySet()) {
//            accounts.merge(entry.getKey(), entry.getValue(), (oldValue, newValue) -> oldValue);
//        }
        // 4
//        accountMap.forEach((k, v) -> accounts.merge(k, v, (oldValue, newValue) -> oldValue));
    }

    /**
     * Метод добавляет некоторый список счетов в карту счетов банка.
     */
    public void addAccounts(List<Account> accountList) {
        accountList.stream()
                .collect(Collectors.toMap(
                        a -> a.getAccountId(),
                        a -> a,
                        (oldValue, newValue) -> oldValue,
                        HashMap::new))
                .forEach((k, v) -> accounts.putIfAbsent(k, v));
    }

    /**
     * Метод возвращает карту всех счетов банка.
     */
    public Map<String, Account> getAccounts() {
        return accounts;
    }

    /**
     * Метод возвращает список идентификаторов всех счетов банка.
     */
    public List<String> getAccountIds() {
        return new ArrayList<>(accounts.keySet());
    }

    /**
     * Проверка транзакций в Службе Безопасности.
     * Служба Безопасности не может обрабатывать более одной транзакции одновременно.
     * Проверка занимает 1000 мс.
     */
    public synchronized boolean checkFraud(String fromAccountId, String toAccountId, UUID uiid, long sum)
            throws InterruptedException {
        log.info("Операция {} отправлена на проверку, сумма: {} RUR. Идёт проверка счетов: №{}, №{}",
                uiid, sum, fromAccountId, toAccountId);

        Thread.sleep(SECURITY_SERVICE_DELAY_MS);

        // задержка для того, чтобы увидеть состояние 'Running' в 'VisualVM'
        if (ENABLE_TRANSFER_LOOP_DELAY) {
            for (int i = 0; i < SECURITY_SERVICE_DELAY_COUNT; i++) {
                tempCount++;
            }
        }

        boolean fraud = generateFraud();
        if (fraud) {
            log.warn("Операция {} не прошла проверку. Счета заблокированы: №{}, №{}",
                    uiid, fromAccountId, toAccountId);
        }

        checkCount++;
        log.debug("Количество проверок: {}", checkCount);

        return fraud;
//        return ThreadLocalRandom.current().nextBoolean();
    }

    /**
     * Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции, она отправляется
     * на проверку Службе Безопасности – вызывается метод isFraud.
     * Если возвращается true, то делается блокировка счетов (как – на ваше усмотрение).
     */
    public void transfer(String fromAccountId, String toAccountId, long sum) {
        try {
            synchronized (fromAccountId.compareTo(toAccountId) > 0
                    ? getAccount(fromAccountId) : getAccount(toAccountId)) {
                synchronized (fromAccountId.compareTo(toAccountId) > 0
                        ? getAccount(toAccountId) : getAccount(fromAccountId)) {
                    log.trace("начало операции");

                    if (getAccount(toAccountId).isBlocked()) {
                        throw new BlockedAccountException(toAccountId);
                    }
                    if (getAccount(fromAccountId).isBlocked()) {
                        throw new BlockedAccountException(fromAccountId);
                    }
                    if (fromAccountId.equals(toAccountId)) {
                        throw new IdenticalAccountsException(fromAccountId);
                    }

                    // генерируем случайный уникальный идентификатор операции
                    UUID uiid = UUID.randomUUID();

                    if (sum > THRESHOLD_SUM_TO_CHECK) {
                        if (checkFraud(fromAccountId, toAccountId, uiid, sum)) {
                            getAccount(fromAccountId).block();
                            getAccount(toAccountId).block();
                        } else {
                            getAccount(fromAccountId).withdraw(sum);
                            getAccount(toAccountId).replenish(sum);
                        }
                    } else {
                        getAccount(fromAccountId).withdraw(sum);
                        getAccount(toAccountId).replenish(sum);
                    }

                    Thread.sleep(TRANSFER_DELAY_MS);

                    // задержка для того, чтобы увидеть состояние 'Running' в 'VisualVM'
                    if (ENABLE_TRANSFER_LOOP_DELAY) {
                        for (int i = 0; i < TRANSFER_DELAY_COUNT; i++) {
                            tempCount++;
                        }
                    }
                }
            }
        } catch (AccountNotExistsException |
                 BlockedAccountException |
                 IdenticalAccountsException |
                 ReplenishNotPossibleException |
                 WithdrawNotPossibleException e) {
            log.warn(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            log.trace("конец операции");
        }
    }

    /**
     * Возвращает остаток на счёте клиента.
     */
    public long getAccountBalance(String accountId) {
        try {
            return getAccount(accountId).getBalance().get();
        } catch (AccountNotExistsException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    /**
     * Возвращает сумму денежных средств всех счетов банка.
     */
    public long getBankBalance() {
        return accounts.values().stream()
                .map(a -> a.getBalance().get())
                .reduce(0L, Long::sum);
    }

    /**
     * Проверяет существование счёта.
     */
    public boolean isAccountExists(String accountId) {
        return accounts.containsKey(accountId);
    }

    /**
     * Генерирует мошенника.
     */
    private boolean generateFraud() {
        double randomPercent = ThreadLocalRandom.current().nextDouble(0,100);

        if (randomPercent < fraudPercent) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод возвращает список идентификаторов всех счетов банка.
     */
    public long countBlocked() {
        return accounts.values()
                .stream()
                .map(a -> a.isBlocked())
                .filter(b -> b)
                .count();
    }

    public void setFraudPercent(int value) {
        fraudPercent = value;
    }

    @Override
    public String toString() {
        return "bank.Bank{" +
                "accounts=" + accounts +
                '}';
    }
}
