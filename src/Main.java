import controller.UserController;
import model.User;
import repo.UserRepo;

import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner sc = null;
        int choice;
        do {
            choice = getChoice(sc);
            switch (choice) {
                case 1:
                    createAccount(sc);
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.exit(0);
            }
        } while (choice > 0);
    }

    private static void login() {

    }

    private static void createAccount(Scanner sc) {

        UserRepo service = null;
        sc = new Scanner(System.in);
        String name = "N/A";
        String address = "N/A";
        String phoneNumber = "N/A";
        String userId = "N/A";
        String password = "N/A";
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
        }

        User newUser = new User(userId, name, phoneNumber, password, deposit, address);
        System.out.println(newUser);

        service.users.add(newUser);

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
        System.out.println("Enter choice (1, 2, or 3");

        choice = sc.nextInt();
        return choice;
    }
}
