package application;

import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    // The first name of the user
    private String firstName;
    // The last name of the user
    private String lastName;
    // The ID of the user (UUID) uniquely identifying the user in the system
    private String uuid;
    // The MD5 hash of the user's PIN (converted to byte array) for authentication
    // purposes
    private byte pinhash[];
    // The list of accounts associated with the user
    private ArrayList<Account> accounts;

    /*
     * Create a new user
     * 
     * @param firstName the first name of the user
     * 
     * @param lastName the last name of the user
     * 
     * @param pin the PIN of the user
     * 
     * @param theBank object that the user is a customer of
     */
    public User(String firstName, String lastName, String pin, Bank theBank) {
        // set user's name.
        this.firstName = firstName;
        this.lastName = lastName;
        // store the pin's MD5 hash , rather than the original value, for security
        // reasons.
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinhash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error,caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        // get a new unique univeral UUID for the user
        this.uuid = theBank.getNewUserUUID();

        // create empty list of accounts
        this.accounts = new ArrayList<>();

        // print log message
        System.out.printf("New user %s, %s with ID %s created.\n", firstName, lastName, this.uuid);
    }

    /*
     * Add an a ccount for the user
     * 
     * @param anAcct the account to be added to the user's list of accounts
     */
    public void addAccount(Account anAcct) {
        this.accounts.add(anAcct);
    }

    /*
     * Return the user uuid
     * 
     * @return the user's UUID
     */
    public String getUUID() {
        return this.uuid;
    }

    /*
     * Check whether the user's PIN is correct or not
     * 
     * @param aPin the PIN entered by the user
     * 
     * @return true if the PIN is correct, false otherwise
     */
    public boolean validatePin(String aPin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinhash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error,caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;
    }

    public String getFirstName() {
        return this.firstName;
    }

    /*
     * Print summaries for the accounts of this user
     */
    public void printAccountsSummary() {
        System.out.printf("\n\n%s's account summary\n ", this.firstName);
        for (int a = 0; a < this.accounts.size(); a++) {
            System.out.printf("  %d) %s\n", a + 1, this.accounts.get(a).getSummaryLine());
        }
        System.out.println();

    }

    /*
     * Return the number of accounts this user has
     * 
     * @return the number of accounts this user has
     */
    public int numAccounts() {
        return this.accounts.size();
    }

    /*
     * Print the transaction history for a specific account
     * 
     * @param acctIdx the index of the account whose transaction history needs to be
     * printed
     */
    public void printAcctTransHistory(int acctIdx) {
        this.accounts.get(acctIdx).printTransHistory();
    }

    /*
     * Return the balance of a specific account
     * 
     * @param acctIdx the index of the account whose balance needs to be returned
     * 
     * @return the balance of the account at the given index
     */
    public double getAcctBalance(int acctIdx) {
        return this.accounts.get(acctIdx).getBalance();
    }

    /*
     * Get the UUID of a partcular account
     * 
     * @param acctIdx the index of the account to use
     * 
     * @return the UUID of the account
     */
    public String getAcctUUID(int acctIdx) {
        return this.accounts.get(acctIdx).getUUID();
    }

    public void addAcctTransaction(int acctIdx, double amount, String memo) {
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }

}
