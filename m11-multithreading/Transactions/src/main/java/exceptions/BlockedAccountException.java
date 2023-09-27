package exceptions;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Исключение, если банковский счёт заблокирован.
 */
@AllArgsConstructor
@Log4j2
public class BlockedAccountException extends Exception {

    private String accountId;

//    @Override
//    public void printStackTrace() {
//        log.warn("Счёт №{} заблокирован. Перевод средств невозможен.", accountId);
//    }

    @Override
    public String getMessage() {
        return "Cчёт №" + accountId + " заблокирован. Перевод средств невозможен.";
    }
}
