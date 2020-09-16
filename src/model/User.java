package model;

import java.util.HashMap;
import java.util.Stack;

public class User {
    private String userId;
    private String name;
    private String phoneNumber;
    private String password;
    private Double total_balance;
    private String address;
    private Stack<Transaction> transactions;
    private HashMap<Long, Account> accounts;

    public User() {
        this("N/A", "N/A", "N/A", "N/A", 0.0, "N/A", new Stack<Transaction>(), new HashMap<Long, Account>());
    }

    public User(String userId, String name, String phoneNumber, String password, Double total_balance, String address, Stack<Transaction> transactions, HashMap<Long, Account> accounts) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.total_balance = total_balance;
        this.address = address;
        this.transactions = transactions;
        this.accounts = accounts;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stack<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Stack<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Double getTotal_balance() {
        return total_balance;
    }

    public void setTotal_Balance(Double total_balance) {
        this.total_balance = total_balance;
    }

    public HashMap<Long, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashMap<Long, Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + total_balance +
                ", address='" + address + '\'' +
                ", transactions=" + transactions +
                ", accounts=" + accounts +
                '}';
    }

    public String toString2() {
        return "User information {" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", balance=" + total_balance +
                ", address='" + address + '\'' +
                '}';
    }
}
