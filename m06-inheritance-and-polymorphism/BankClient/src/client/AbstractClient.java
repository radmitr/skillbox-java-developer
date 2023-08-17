package client;

public abstract class AbstractClient {

    protected String clientId;
    protected double bankAccount;

    public AbstractClient(double bankAccount) {
        this.clientId = String.valueOf(System.nanoTime());
        if (bankAccount > 0) {
            this.bankAccount = bankAccount;
        }
    }

    public String getClientId() {
        return clientId;
    }

    public abstract void deposit(double sum);

    public abstract void withdraw(double sum);

    public double balance() {
        return bankAccount;
    }

    public String toString() {
        return "Остаток средств на счёте ID=" + clientId +
                " составляет " + bankAccount;
    }
}
