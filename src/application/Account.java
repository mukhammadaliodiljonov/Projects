package application;

import java.util.ArrayList;

public class Account {

    // The name of the account.
    private String name;
    // The unique identifier for the account.
    private String uuid;
    // The user who owns the account.
    private User holder;
    // A list of all transactions associated with the account.
    private ArrayList<Transaction> transactions;

    /*
     * Create a New Account
     * 
     * @param name the name of the account
     * 
     * @param holder the User object that holds this account
     * 
     * @param theBank the Bank object that manages this account
     */
    public Account(String name, User holder, Bank theBank) {

        // set the account name and holder
        this.name = name;
        this.holder = holder;

        // get a new unique UUID for the account
        this.uuid = theBank.getNewAccountUUID();

        // initialize the transaction list
        this.transactions = new ArrayList<Transaction>();
    }

    /*
     * Get the account id
     * 
     * @return the unique identifier for the account
     */
    public String getUUID() {
        return this.uuid;
    }

    /*
     * Get the summary line for the account
     * 
     * @retun the account balance
     */
    public String getSummaryLine() {
        // get account balance
        double balance = this.getBalance();

        // format the summary line depending on the whether the balance is negative
        if (balance >= 0) {
            return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
        } else {
            return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);

        }
    }

    /*
     * Get the balance of the account by adding the amounts of the transactions.
     * 
     * @return the account balance as a double value.
     */
    public double getBalance() {
        double balance = 0;
        for (Transaction t : this.transactions) {
            balance += t.getAmount();
        }

        return balance;
    }

    public void printTransHistory() {
        System.out.printf("\nTransaction History for Account %s:\n", this.uuid);
        for (int t = this.transactions.size() - 1; t >= 0; t--) {
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
    }

    /*
     * Add a new transaction to the account.
     * 
     * @param amount the amount of the transaction
     * 
     * @param memo the memo for the transaction
     */
    public void addTransaction(double amount, String memo) {
        // create a new transaction with the given amount and memo, and add it to the
        // list of transactions
        Transaction newTrans = new Transaction(amount, this, memo);
        this.transactions.add(newTrans);
    }
}
