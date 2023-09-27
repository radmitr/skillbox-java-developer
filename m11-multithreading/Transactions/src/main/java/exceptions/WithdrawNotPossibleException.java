package exceptions;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Исключение, если списание с банковского счёта невозможно.
 */
@AllArgsConstructor
@Log4j2
public class WithdrawNotPossibleException extends Exception {

    private String accountId;
    private String message;

//    @Override
//    public void printStackTrace() {
//        log.warn("Списание со счёта №{} невозможно. {}.", accountId, message);
//    }

    @Override
    public String getMessage() {
        return "Списание со счёта №" + accountId + " невозможно. " + message + ".";
    }
}
