package terminal;

import java.math.BigDecimal;

public class Transaction {
    private int idTransaction;

    private enum TypeTransaction {
        deposit,
        withdraw
    }

    private BigDecimal amount;
    private int deposit;
    private TypeTransaction typeTransactionName;


    public Transaction(int idTransaction, BigDecimal amount, int deposit, String type) {
        this.idTransaction = idTransaction;
        this.amount = amount;
        this.deposit = deposit;
        this.typeTransactionName =findEnum(type);
    }

    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
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
