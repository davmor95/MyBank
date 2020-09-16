package model;

public class Account {


    private Long account_number;
    private Double balance;
    private AccountType type;

    public Account() {
        this(-1L, 0.0, AccountType.CHECKING);
    }

    public Account(Long account_number, Double balance, AccountType type) {
        this.account_number = account_number;
        this.balance = balance;
        this.type = type;
    }

    public Long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Long account_number) {
        this.account_number = account_number;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "account_number=" + account_number +
                ", balance=" + balance +
                ", type=" + type +
                '}' + " ";
    }
}
