package terminal;

import java.io.Serializable;
import java.math.BigDecimal;

public class Transaction implements Serializable {
    private int transactionId;

    private enum TypeTransaction {
        deposit,
        withdraw
    }

    private BigDecimal amount;
    private int deposit;
    private TypeTransaction typeTransactionName;


    public Transaction(int transactionId, BigDecimal amount, int deposit, String type) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.deposit = deposit;
        this.typeTransactionName = findEnum(type);
    }

    public TypeTransaction getTypeTransactionName() {
        return typeTransactionName;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public TypeTransaction findEnum(String type) {
        try {
            if (type.equals("deposit")) {
                return TypeTransaction.deposit;
            }
            if (type.equals("withdraw")) {
                return TypeTransaction.withdraw;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return typeTransactionName;
    }

}
