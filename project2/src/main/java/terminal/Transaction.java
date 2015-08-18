package terminal;

/**
 * Created by Dotin School1 on 8/18/2015.
 */
public class Transaction {
    private int idTransaction;
    private enum typeTransaction{
        deposit,
        withdraw
    }
    private int amount;
    private int deposit;

    public Transaction(int idTransaction, int amount, int deposit) {
        this.idTransaction = idTransaction;
        this.amount = amount;
        this.deposit = deposit;
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }
}
