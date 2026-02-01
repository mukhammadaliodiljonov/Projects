package application;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        // init Scanner
        Scanner scanner = new Scanner(System.in);

        // init bank
        Bank bank = new Bank("Anor Bank");

        // add a user to the bank which also creates a savings account
        User user = bank.addUser("John", "Doe", "2002");

        // add a checking account for our user
        Account newAccount = new Account("Checking", user, bank);
        user.addAccount(newAccount);
        bank.addAccount(newAccount);

        User curUser;
        while (true) {

            // stay in the login prompt until a valid user is found
            curUser = ATM.mainMenu(bank, scanner);

            // stay in the main menu until the user decides to exit
            ATM.printUserMenu(curUser, scanner);
        }
    }

    public static User mainMenu(Bank bank, Scanner scanner) {

        // inits
        String userID;
        String pin;
        User authUser;

        // prompt user for user id/pin combo untill a valid one is reached
        do {
            System.out.printf("\n\nWelcome to %s\n\n", bank.getName());
            System.out.print("Enter user ID: ");
            userID = scanner.nextLine();
            System.out.print("Enter PIN: ");
            pin = scanner.nextLine();

            // try to get the usr object corresponding to the given id/pin combo
            authUser = bank.userLogin(userID, pin);
            if (authUser == null) {
                System.out.println("Incorrect user ID or PIN. Please try again.");
            }
        } while (authUser == null); // continue until a valid user is found

        return authUser;
    }

    public static void printUserMenu(User theUser, Scanner scanner) {

        // print a summary of the user's accounts
        theUser.printAccountsSummary();

        // init
        int choice;

        // user menu
        do {
            System.out.printf("Welcome %s, what would you like to do?\n", theUser.getFirstName());
            System.out.println("   1) Show account transaction history");
            System.out.println("   2) Withdraw");
            System.out.println("   3) Deposit");
            System.out.println("   4) Transfer");
            System.out.println("   5) Exit");
            System.out.println();
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please choose 1 - 5.");
            }
        } while (choice < 1 || choice > 5);

        // process user's choice

        switch (choice) {
            case 1:
                ATM.showTransHistory(theUser, scanner);
                break;
            case 2:
                ATM.withdrawFunds(theUser, scanner);
                break;
            case 3:
                ATM.depositFunds(theUser, scanner);
                break;
            case 4:
                ATM.transferFunds(theUser, scanner);
                break;
            case 5:
                scanner.nextLine();
                break;
        }

        // redisplay the menu unless the user wants to quit
        if (choice != 5) {
            ATM.printUserMenu(theUser, scanner);
        }
    }

    private static void showTransHistory(User theUser, Scanner scanner) {
        int theAcct;

        // get the aacount whose transaction history we want to see
        do {
            System.out.printf(
                    "Enter the number (1-%d) of the account\n" + "whose transaction history you want to see: ",
                    theUser.numAccounts());
            theAcct = scanner.nextInt() - 1;
            if (theAcct < 0 || theAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account number. Please try again.");
            }
        } while (theAcct < 0 || theAcct >= theUser.numAccounts());

        // print the transaction history of the selected account
        theUser.printAcctTransHistory(theAcct);
    }

    /*
     * withdraw funds from the user's account
     * 
     * @param theUser The user whose account we're withdrawing from
     * 
     * @param scanner The scanner for user input
     */
    public static void transferFunds(User theUser, Scanner scanner) {

        // inits
        int fromAcct;
        int toAcct;
        double amount;
        double acctBal;

        // get the amount to tranfer from the user
        do {
            System.out.printf("Enter the number(1-%d) of the account\n" + "from which you want to transfer funds: ",
                    theUser.numAccounts());
            fromAcct = scanner.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account . Please try again.");
            }

        } while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
        acctBal = theUser.getAcctBalance(fromAcct);

        // get the account to tranfer to
        do {
            System.out.printf("Enter the number(1-%d) of the account\n" + "from which you want to transfer funds: ",
                    theUser.numAccounts());
            toAcct = scanner.nextInt() - 1;
            if (fromAcct < 0 || toAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account . Please try again.");
            }

        } while (toAcct < 0 || toAcct >= theUser.numAccounts());

        // get the amount to tranfer
        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $", acctBal);
            amount = scanner.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be grater than zero. Please try again.");
            } else if (amount > acctBal) {
                System.out.printf("Amount must be grater than \n" + "balance of $%.02f" + " Please try again.",
                        acctBal);
            }
        } while (amount < 0 || amount > acctBal);

        // transfer the funds
        theUser.addAcctTransaction(fromAcct, -1 * amount,
                String.format("Transfer to account %s", theUser.getAcctUUID(toAcct)));
        theUser.addAcctTransaction(toAcct, amount,
                String.format("Transfer to account %s", theUser.getAcctUUID(fromAcct)));

    }

    /*
     * Deposit funds into the user's account
     * 
     * @param theUser The user whose account we're depositing to
     * 
     * @param scanner The scanner for user input
     */
    public static void withdrawFunds(User theUser, Scanner scanner) {
        // inits
        int fromAcct;
        double amount;
        double acctBal;
        String memo;

        // get the amount to tranfer from the user
        do {
            System.out.printf("Enter the number(1-%d) of the account\n" + "to withdraw from: ",
                    theUser.numAccounts());
            fromAcct = scanner.nextInt() - 1;
            if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account . Please try again.");
            }

        } while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
        acctBal = theUser.getAcctBalance(fromAcct);
        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $", acctBal);
            amount = scanner.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be grater than zero. Please try again.");
            } else if (amount > acctBal) {
                System.out.printf("Amount must be grater than \n" + "balance of $%.02f" + " Please try again.",
                        acctBal);
            }
        } while (amount < 0 || amount > acctBal);

        // gobble up the rest of the previous line of input
        scanner.nextLine();

        // get memo
        System.out.print("Enter memo : ");
        memo = scanner.nextLine();

        // do the withdrawa
        theUser.addAcctTransaction(fromAcct, -1 * amount, memo);
    }

    /*
     * Deposit funds into the user's account
     * 
     */
    public static void depositFunds(User theUser, Scanner scanner) {
        int toAcct;
        double amount;
        double acctBal;
        String memo;

        // get the amount to tranfer from the user
        do {
            System.out.printf("Enter the number(1-%d) of the account\n" + "to deposit in: ",
                    theUser.numAccounts());
            toAcct = scanner.nextInt() - 1;
            if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
                System.out.println("Invalid account . Please try again.");
            }

        } while (toAcct < 0 || toAcct >= theUser.numAccounts());
        acctBal = theUser.getAcctBalance(toAcct);
        do {
            System.out.printf("Enter the amount to transfer (max $%.02f): $", acctBal);
            amount = scanner.nextDouble();
            if (amount < 0) {
                System.out.println("Amount must be grater than zero. Please try again.");
            }
        } while (amount < 0);

        // gobble up the rest of the previous line of input
        scanner.nextLine();

        // get memo
        System.out.print("Enter memo : ");
        memo = scanner.nextLine();

        // do the withdrawa
        theUser.addAcctTransaction(toAcct, amount, memo);
    }

}
