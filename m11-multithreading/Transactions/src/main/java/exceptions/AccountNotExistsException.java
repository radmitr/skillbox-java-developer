package exceptions;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * Исключение, если банковский счёт не существует.
 */
@AllArgsConstructor
@Log4j2
public class AccountNotExistsException extends Exception {

    private String accountId;

//    @Override
//    public void printStackTrace() {
//        log.warn("Счёт №{} не найден.", accountId);
//    }

    @Override
    public String getMessage() {
        return "Счёт №" + accountId + " не найден.";
    }
}
