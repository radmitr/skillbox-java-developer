package bank;

import exceptions.ReplenishNotPossibleException;
import exceptions.WithdrawNotPossibleException;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.atomic.AtomicLong;

@Data
@Log4j2
public class Account {

    /** Номер счета */
    @NonNull
    private String accountId;

    /** Количество средств на счёте */
    private AtomicLong balance;

    /** Блокировка счёта */
    private boolean blocked;

    public Account(@NonNull String accountId, long balance) {
        this.accountId = accountId;
        this.balance = new AtomicLong(balance);
    }

    public Account(@NonNull String accountId, long balance, boolean blocked) {
        this.accountId = accountId;
        this.balance = new AtomicLong(balance);
        this.blocked = blocked;
    }

    /**
     * Блокировка счёта.
     */
    public void block() {
        blocked = true;
    }

    /**
     * Разблокировка счёта.
     */
    public void unblock() {
        blocked = false;
    }

    /**
     * Пополнение счёта на заданную сумму.
     */
    public void replenish(long sum) throws ReplenishNotPossibleException {
        if (isAllowedTransaction(sum)) {
            balance.addAndGet(sum);
            log.info("пополнение, счёт №{}, сумма: {} RUR, баланс: {} RUR", accountId, sum, balance.get());
        } else {
            if (sum <= 0) {
                throw new ReplenishNotPossibleException(accountId, "Некорректная сумма денег");
            } else if (blocked) {
                throw new ReplenishNotPossibleException(accountId, "Счёт заблокирован");
            }
        }
    }

    /**
     * Списание средств со счёта на заданную сумму.
     */
    public void withdraw(long sum) throws WithdrawNotPossibleException {
        if (isAllowedTransaction(sum) && balance.get() >= sum) {
            balance.accumulateAndGet(sum, (x, y) -> x - y);
            log.info("списание,   счёт №{}, сумма: {} RUR, баланс: {} RUR", accountId, sum, balance.get());
        } else {
            if (sum <= 0) {
                throw new WithdrawNotPossibleException(accountId, "Некорректная сумма денег");
            } else if (blocked) {
                throw new WithdrawNotPossibleException(accountId, "Счёт заблокирован");
            } else if (balance.get() < sum) {
                throw new WithdrawNotPossibleException(accountId, "Недостаточно средств");
            }
        }
    }

    /**
     * Проверка основных параметров операции перед выполнением.
     */
    private boolean isAllowedTransaction(long sum) {
        return !blocked && sum > 0;
    }
}
