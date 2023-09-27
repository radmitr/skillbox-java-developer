package bank;

import exceptions.ReplenishNotPossibleException;
import exceptions.WithdrawNotPossibleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    static Account account1;
    static Account account2;
    static Account account3;

    @BeforeEach
    void setUp() {
        createAccounts();
    }

    @Nested
    @DisplayName("Testing method: replenish()")
    class Method_Replenish {
        @Test
        void replenish()
                throws ReplenishNotPossibleException {
            account1.replenish(5_500);
            assertEquals(15_500, account1.getBalance().get());
        }

        @Test
        void replenish_whenBlockedAccount_thenThrowException() {
            account3.setBlocked(true);
            assertThrows(ReplenishNotPossibleException.class, () -> account3.replenish(5_500));
        }

        @Test
        void replenish_whenZeroSum_thenThrowException() {
            assertThrows(ReplenishNotPossibleException.class, () -> account1.replenish(0));
        }

        @Test
        void replenish_whenNegativeSum_thenThrowException() {
            assertThrows(ReplenishNotPossibleException.class, () -> account1.replenish(-10_000));
        }

        @Test
        public void replenish_whenManyThreads() {
                ArrayList<Thread> threads = new ArrayList<>();
                for (int i = 0; i < 200; i++) {
                    threads.add(new Thread(() -> {
                        try {
                            account1.replenish(1000);
                        } catch (ReplenishNotPossibleException e) {
                            e.printStackTrace();
                        }
                    }));
                }
                threads.forEach(Thread::start);
                for (Thread thread : threads) {
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                long actualBalance = account1.getBalance().get();
                assertEquals(210_000, actualBalance);
        }
    }

    @Nested
    @DisplayName("Testing method: withdraw()")
    class Method_Withdraw {
        @Test
        void withdraw()
                throws WithdrawNotPossibleException {
            account2.withdraw(8_000);
            assertEquals(12_000, account2.getBalance().get());
        }

        @Test
        void withdraw_whenBlockedAccount_thenThrowException() {
            account3.setBlocked(true);
            assertThrows(WithdrawNotPossibleException.class, () -> account3.withdraw(6_500));
        }

        @Test
        void withdraw_whenZeroSum_thenThrowException() {
            assertThrows(WithdrawNotPossibleException.class, () -> account2.withdraw(0));
        }

        @Test
        void withdraw_whenNegativeSum_thenThrowException() {
            assertThrows(WithdrawNotPossibleException.class, () -> account2.withdraw(-10_000));
        }

        @Test
        void withdraw_whenNotEnoughMoney_thenThrowException() {
            assertThrows(WithdrawNotPossibleException.class, () -> account2.withdraw(50_000));
        }

        @Test
        public void withdraw_whenManyThreads() {
            ArrayList<Thread> threads = new ArrayList<>();
            for (int i = 0; i < 200; i++) {
                threads.add(new Thread(() -> {
                    try {
                        account3.withdraw(100);
                    } catch (WithdrawNotPossibleException e) {
                        e.printStackTrace();
                    }
                }));
            }
            threads.forEach(Thread::start);
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            long actualBalance = account3.getBalance().get();
            assertEquals(10_000, actualBalance);
        }
    }

    /**
     * Метод для создания новых счетов
     */
    private static void createAccounts() {
        account1 = new Account("1", 10_000);
        account2 = new Account("2", 20_000);
        account3 = new Account("3", 30_000);
    }
}
