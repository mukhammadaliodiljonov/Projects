package application;

import java.util.Date;

public class Transaction {

    // The amount of this transaction
    private double amount;
    // The time and date of this transaction
    private Date timestamp;
    // A memo for this transaction
    private String memo;
    // The account in which the transaction was
    private Account inAccount;

    /*
     * Create a new transaction with the given amount and account.
     * 
     * @param amount The amount of the transaction
     * 
     * @param iAccount The account in which the transaction was
     */
    public Transaction(double amount, Account iAccount) {

        this.amount = amount;
        this.inAccount = iAccount;
        this.timestamp = new Date();
        this.memo = "";
    }

    public Transaction(double amount, Account iAccount, String memo) {

        // call the two-argument constructor
        this(amount, iAccount);

        // set the memo
        this.memo = memo;

    }

    public double getAmount() {
        return this.amount;
    }

    /*
     * Get a summarizing the transaction
     * 
     * @return the summary string
     */
    public String getSummaryLine() {
        if (this.amount >= 0) {
            return String.format("%s : $%.02f : %s", this.timestamp.toString(), this.amount, this.memo);
        } else {
            return String.format("%s : $(%.02f) : %s", this.timestamp.toString(), this.amount, this.memo);

        }
    }
}
