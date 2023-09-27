package exceptions;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Исключение, если пополненить банковский счёт невозможно.
 */
@AllArgsConstructor
@Log4j2
public class ReplenishNotPossibleException extends Exception {

    private String accountId;
    private String message;

//    @Override
//    public void printStackTrace() {
//        log.warn("Пополнение счёта №{} невозможно. {}.", accountId, message);
//    }

    @Override
    public String getMessage() {
        return "Пополнение счёта №" + accountId + " невозможно. " + message + ".";
    }
}
