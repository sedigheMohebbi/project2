package server;

import exception.DepositTransactionException;


import java.math.BigDecimal;
import java.util.DuplicateFormatFlagsException;

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

    public BigDecimal deposit(BigDecimal amount) throws DepositTransactionException, InterruptedException {
        // synchronized (this) {
        if (Thread.currentThread().getName().equals("Thread-1")) {
            System.out.println(Thread.currentThread().getName());
            Thread.currentThread().sleep(100);
        }
        BigDecimal sum = amount.add(initialBalance);
        if (sum.compareTo(upperBound) == -1) {
            initialBalance = sum;
            return sum;
        }
        throw new DepositTransactionException("upper bound exceeded " + "Customer : " + customer + " id: " + id);
        //  }
    }

    public BigDecimal withdraw(BigDecimal amount) throws DepositTransactionException, InterruptedException {
        // synchronized (this) {
        if (Thread.currentThread().getName().equals("Thread-1")) {
            System.out.println(Thread.currentThread().getName());
            Thread.currentThread().sleep(100);
        }
        BigDecimal result = initialBalance.subtract(amount);
        if (result.compareTo(BigDecimal.ZERO) != -1) {
            initialBalance = result;
            return result;
        }
        //}
        throw new DepositTransactionException("not enough balance" + " customer: " + customer + " id: " + id);
    }
}
