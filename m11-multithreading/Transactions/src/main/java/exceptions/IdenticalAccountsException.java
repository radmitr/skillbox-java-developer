package exceptions;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Иисключение, если для перевода указан один и тот же банковский счёт.
 */
@AllArgsConstructor
@Log4j2
public class IdenticalAccountsException extends Exception {

    private String accountId;

//    @Override
//    public void printStackTrace() {
//        log.warn("Указаны одинаковые номера счетов: №{}. Перевод средств невозможен.", accountId);
//    }

    @Override
    public String getMessage() {
        return "Указаны одинаковые номера счетов: №" + accountId + ". Перевод средств невозможен.";
    }
}
