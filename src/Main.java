import controller.UserController;
import model.Account;
import model.Transaction;
import model.TransactionType;
import model.User;
import repo.UserRepo;

import java.beans.Transient;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;


public class Main {
    static UserRepo service = null;
    static Long account_number_counter = 0L;

    public static void main(String[] args) {

        Scanner sc = null;
        int choice;
        do {
            service.users.entrySet().forEach(System.out::println);
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
            choice = getChoice(sc);
            switch (choice) {
                case 1:
                    createAccount(sc);
                    break;
                case 2:
                    login(sc);
                    break;
                case 3:
                    System.exit(0);
            }
        } while (choice > 0);
    }

    private static void login(Scanner sc) {
        sc = new Scanner(System.in);
        String userId = "";
        String password = "";
        System.out.println("User Id: ");
        if(sc.hasNextLine()) {
            userId = sc.nextLine();
        }
        System.out.println("Password: ");
        if(sc.hasNextLine()) {
            password = sc.nextLine();
        }


        if(!service.users.containsKey(userId)) {
            System.out.println("Invalid Credentials. Try again!");
        } else {
            User found = service.users.get(userId);
            if(!password.equals(found.getPassword())) {
                System.out.println("Invalid Password. Try again!");
            } else {
                welcomeCenter(sc, found.getUserId());
            }
        }
    }

    private static void welcomeCenter(Scanner sc, String userId) {
        int choice;
        do {
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
            service.users.entrySet().forEach(System.out::println);
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
            choice = getMenuChoice(sc);
            switch (choice) {
                case 1:
                    deposit(sc, userId);
                    break;
                case 2:
                    withdraw(sc, userId);
                    break;
                case 3:
                    transfer(sc, userId);
                    break;
                case 4:
                    recentTransactions(userId);
                    break;
                case 5:
                    displayCustomerInfo(userId);
                    break;
                case 6:
                    createAnotherAccount(sc, userId);
                    break;
                case 7:
                    return;
            }

        } while (choice > 0);
    }

    private static void createAnotherAccount(Scanner sc, String userId) {
        sc = new Scanner(System.in);
        User user = service.users.get(userId);
        System.out.println("Would you like");
    }

    private static void displayCustomerInfo(String userId) {
        User found = service.users.get(userId);
        System.out.println(found.toString2());
    }

    private static void recentTransactions(String userId) {
        //access the stack from user
        User found = service.users.get(userId);
        found.getTransactions().stream().forEach(System.out::println);
    }

    private static void transfer(Scanner sc, String userId) {
        sc = new Scanner(System.in);
        User user = service.users.get(userId);
        HashMap<Long, Account> userAccounts = user.getAccounts();
        Account fromAccount = null;
        Account toAccount = null;
        Double transfer;

        System.out.println("Which account do you want to transfer from?");
        userAccounts.entrySet().forEach(System.out::println);
        if(sc.hasNextLong()) {
            fromAccount = userAccounts.get(sc.nextLong());
        } else {
            System.out.println("Error in grabbing account");
            return;
        }
        System.out.println("Which account do you want to transfer to?");
        userAccounts.entrySet().forEach(System.out::println);
        if(sc.hasNextLong()) {
            toAccount = userAccounts.get(sc.nextLong());
        } else {
            System.out.println("Error in grabbing account");
            return;
        }
        Double fromBalance = fromAccount.getBalance();
        System.out.println("How much would you like to transfer?");
        if(sc.hasNextDouble()) {
            transfer = sc.nextDouble();

            if(fromBalance - transfer < 0) {
                System.out.println("Cannot transfer since you dont have suffient amount of money to do so.");
            } else {
                Double newBalanceFrom = fromBalance - transfer;
                fromAccount.setBalance(newBalanceFrom);
                userAccounts.put(fromAccount.getAccount_number(), fromAccount);
                //now add the new funds to the desired account
                Double toBalance = toAccount.getBalance();
                Double newBalanceTo = toBalance + transfer;
                toAccount.setBalance(newBalanceTo);
                userAccounts.put(toAccount.getAccount_number(), toAccount);
                user.setAccounts(userAccounts);
                service.users.put(userId, user);
                System.out.println("Transfer success");
            }
        } else {
            System.out.println("Error in receiving how much you would like to transfer");
            return;
        }






    }

    private static void withdraw(Scanner sc, String userID) {
        sc = new Scanner(System.in);
        Double withdraw;
        Double currentBalance;
        Double newTotalBalance;
        //First we have to get the user from the map
        User found = service.users.get(userID);
        currentBalance = found.getTotal_balance();
        HashMap<Long, Account> userAccounts = found.getAccounts();
        Account userDesiredAccount;
        Double balanceInAccount;
        Double newBalanceInAccount;
        Transaction transaction = new Transaction();

        System.out.println("Which account do you want to withdraw from");
        userAccounts.entrySet().forEach(System.out::println);
        if (sc.hasNextLong()) {
            userDesiredAccount = userAccounts.get(sc.nextLong());
            //Then we need to ask the console how much they want to withdraw
            System.out.println("How much do you want to withdraw?");
            if (sc.hasNextDouble()) {
                withdraw = sc.nextDouble();
                balanceInAccount = userDesiredAccount.getBalance();
                //check if the number they enter is not greater than the balance
                if (balanceInAccount - withdraw < 0) {
                    System.out.println("Exceeded current balance, you do not have enough money to pull out in this account!");
                } else {
                    //set the transaction as a withdraw and add it to transactions
                    transaction.setTransactionType(TransactionType.WITHDRAW);
                    transaction.setTransaction_balance(withdraw);
                    transaction.setAccountType(userDesiredAccount.getType());
                    transaction.setAccountNumber(userDesiredAccount.getAccount_number());
                    found.getTransactions().push(transaction);
                    //get the new total balance of all accounts and set it to the current user
                    newTotalBalance = currentBalance - withdraw;
                    found.setTotal_Balance(newTotalBalance);
                    //set the balance in the desired account
                    balanceInAccount = balanceInAccount - withdraw;
                    userDesiredAccount.setBalance(balanceInAccount);
                    //put the desired account back into the account map
                    userAccounts.put(userDesiredAccount.getAccount_number(), userDesiredAccount);
                    //set the account map back to the user
                    found.setAccounts(userAccounts);
                    service.users.put(userID, found);
                    System.out.println("Withdraw success!");

                }
            } else {
                System.out.println("Sorry, you did not enter a double!");
            }
        } else {
            System.out.println("Sorry, you did enter an account associated with the number provided");
        }
    }

    private static void deposit(Scanner sc, String userId) {

        Long accountNumber;
        User user = service.users.get(userId);
        Double deposit;
        Double currentBalance;
        Transaction transaction = new Transaction();
        HashMap<Long, Account> accounts = user.getAccounts();
        Account desiredAccount = new Account();
        sc = new Scanner(System.in);
        System.out.println("Which account do you want to deposit?");
        //TODO ask the user which account they want to deposit
        accounts.entrySet().forEach(System.out::println);
        if(sc.hasNextLong()) {
            accountNumber = sc.nextLong();
            desiredAccount = accounts.get(accountNumber);
            System.out.println("Enter the amount you want to deposit: ");
            if (sc.hasNextDouble()) {
                deposit = sc.nextDouble();
                //setting the total balance
                currentBalance = user.getTotal_balance();
                currentBalance += deposit;
                user.setTotal_Balance(currentBalance);
                //setting the transaction
                transaction.setTransaction_balance(deposit);
                transaction.setAccountNumber(desiredAccount.getAccount_number());
                transaction.setAccountType(desiredAccount.getType());
                transaction.setTransactionType(TransactionType.DEPOSIT);
                user.getTransactions().push(transaction);
                //update the balance in desired account
                Double currentBalanceInAccount = desiredAccount.getBalance();
                currentBalanceInAccount += deposit;
                desiredAccount.setBalance(currentBalanceInAccount);
                //put the account back into accounts map
                accounts.put(desiredAccount.getAccount_number(), desiredAccount);
                //attach the account back to the user
                user.setAccounts(accounts);
                //put the user back to user map
                service.users.put(user.getUserId(), user);

            } else {
                System.out.println("Did not enter a double.");
            }
        } else {
            System.out.println("Did not enter the proper account number");
        }


    }

    private static int getMenuChoice(Scanner sc) {

        int choice;
        sc = new Scanner(System.in);
        System.out.println("WELCOME Customer");
        System.out.println("1. Deposit Amount");
        System.out.println("2. Withdraw Amount");
        System.out.println("3. Funds Transfer");
        System.out.println("4. View 5 Recent Transactions");
        System.out.println("5. Display Customer Information");
        System.out.println("6. Create another account");
        System.out.println("7. Sign Out");
        System.out.println();
        System.out.println();
        System.out.println("Enter choice (1, 2, 3, 4, 5 or 6)");

        choice = sc.nextInt();
        return choice;
    }

    private static void createAccount(Scanner sc) {

        sc = new Scanner(System.in);
        String name = "N/A";
        String address = "N/A";
        String phoneNumber = "N/A";
        String userId = "N/A";
        String password = "N/A";
        Stack<Transaction> transactions = new Stack<Transaction>();
        Transaction first_transaction = new Transaction();
        HashMap<Long, Account> accounts = new HashMap<Long, Account>();
        Account first_account = new Account();
        Double deposit = 0.0;


        System.out.println("Customer Name: ");
        if(sc.hasNextLine()) {
            name = sc.nextLine();
        }
        System.out.println("Customer Address: ");
        if(sc.hasNextLine()) {
            address = sc.nextLine();
        }
        System.out.println("Customer Contact Number");
        if(sc.hasNextLine()) {
            phoneNumber = sc.nextLine();
        }
        System.out.println("User Id");
        if (sc.hasNextLine()) {
            userId = sc.nextLine();
        }
        System.out.println("Password: 8 Characters With Lower,Upper & Special");
        if(sc.hasNextLine()) {
            password = sc.nextLine();
        }
        System.out.println("Initial Deposit Amount");
        if(sc.hasNextDouble()) {
            deposit = sc.nextDouble();
            first_account.setAccount_number(account_number_counter++);
            first_account.setBalance(deposit);
            accounts.put(first_account.getAccount_number(), first_account);
            first_transaction.setTransaction_balance(deposit);
            transactions.push(first_transaction);



        }
        //TODO must change that if statement on "Initial Deposit Amount" to put into proper account
        User newUser = new User(userId, name, phoneNumber, password, deposit, address, transactions, accounts);
        System.out.println(newUser);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");

        service.users.put(newUser.getUserId(), newUser);

    }

    private static int getChoice(Scanner sc) {
        int choice;
        sc = new Scanner(System.in);
        System.out.println("DOLLARSBANK Welcomes You!");
        System.out.println("1. Create New account");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println();
        System.out.println();
        System.out.println("Enter choice (1, 2, or 3)");

        choice = sc.nextInt();
        return choice;
    }
}
