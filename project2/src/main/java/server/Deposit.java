package server;

import java.math.BigDecimal;

public class Deposit {
    private String customer;
    private int id;
    private BigDecimal initialBalance;//mojodi avaliye
    private BigDecimal upperBound;// hade bale mojodi

    public Deposit(String customer, int id, BigDecimal initialBalance, BigDecimal upperBound) {
        this.customer = customer;
        this.id = id;
        this.initialBalance = initialBalance;
        this.upperBound = upperBound;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigDecimal getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(BigDecimal upperBound) {
        this.upperBound = upperBound;
    }

    public synchronized BigDecimal deposit(BigDecimal amount) throws Exception {
        BigDecimal sum = amount.add(initialBalance);
        if (sum.compareTo(upperBound) == -1) {
            initialBalance = sum;
            return sum;
        }
        throw new Exception(" az saghfe enteghal bishtare");
    }

    public synchronized BigDecimal withdraw(BigDecimal amount) throws Exception {
        BigDecimal result = initialBalance.subtract(amount);
        if (result.compareTo(BigDecimal.ZERO) != -1) {
            initialBalance = result;
            return result;
        }
        throw new Exception("mojodi kafi nist");
    }
}
