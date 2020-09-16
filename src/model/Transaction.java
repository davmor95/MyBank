package model;

import java.util.Date;

public class Transaction {

    private Double transaction_balance;
    private Date transaction_date;
    private AccountType accountType;
    private Long accountNumber;
    private TransactionType transactionType;


    public Transaction() {
        this(0.0, new Date(), AccountType.CHECKING, 0L, TransactionType.DEPOSIT);
    }

    public Transaction(Double transaction_balance, Date transaction_date, AccountType accountType, Long accountNumber, TransactionType transactionType) {
        this.transaction_balance = transaction_balance;
        this.transaction_date = transaction_date;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
    }

    public Double getTransaction_balance() {
        return transaction_balance;
    }

    public void setTransaction_balance(Double transaction_balance) {
        this.transaction_balance = transaction_balance;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_balance=" + transaction_balance +
                ", transaction_date=" + transaction_date +
                ", accountType=" + accountType +
                ", accountNumber=" + accountNumber +
                ", transactionType=" + transactionType +
                '}';
    }
}
