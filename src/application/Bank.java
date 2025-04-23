package application;

import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    /*
     * Create a new bank with the emptylist of users and accounts.
     * 
     * @param name the name of the bank
     */
    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    /*
     * Generate a new universal unique identifier for a new user.
     * 
     * @return the uuid
     */
    public String getNewUserUUID() {
        // inits
        String uuid;
        Random random = new Random();
        int length = 6;
        boolean nonUnique;

        // continue generating until a unique id is found
        do {
            uuid = "";
            for (int i = 0; i < length; i++) {
                uuid += ((Integer) random.nextInt(10)).toString();
            }

            // check to make sure it is unique
            nonUnique = false; // assume the uuid is unique at first
            for (User u : this.users) {
                if (uuid.compareTo(u.getUUID()) == 0) {
                    nonUnique = true;
                    break;

                }
            }

        } while (nonUnique);

        return uuid;
    }

    /*
     * Generate a new universal unique identifier for a new account.
     * 
     * @return the uuid
     */
    public String getNewAccountUUID() {
        // inits
        String uuid;
        Random random = new Random();
        int length = 10;
        boolean nonUnique;

        // continue generating until a unique id is found
        do {
            uuid = "";
            for (int i = 0; i < length; i++) {
                uuid += ((Integer) random.nextInt(10)).toString();
            }

            // check to make sure it is unique
            nonUnique = false; // assume the uuid is unique at first
            for (Account a : this.accounts) {
                if (uuid.compareTo(a.getUUID()) == 0) {
                    nonUnique = true;
                    break;
                }

            }

        } while (nonUnique);

        return uuid;
    }

    /*
     * Add an Account
     * 
     * @param anAcct the account to be added to the bank's list of accounts
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /*
     * Create a new user of the Bank.
     * 
     * @param firstName the user's first name
     * 
     * @param lastName the user's last name
     * 
     * @param pin the user's pin number
     * 
     * @return the new user object
     */
    public User addUser(String firstName, String lastName, String pin) {

        // create a new user object and add it to our list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        // create a savings account for the user and add it to User and Bank account
        // list
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;

    }

    public User userLogin(String userID, String pin) {

        // Search for the user in the list
        for (User u : this.users) {

            // check user ID is correct
            if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)) {
                return u;
            }
        }
        // if we havent found the user or have an incorrect pin
        return null;
    }

    /*
     * Return the name of the bank
     * 
     * @return the bank's name
     */
    public String getName() {
        return this.name;
    }

}
