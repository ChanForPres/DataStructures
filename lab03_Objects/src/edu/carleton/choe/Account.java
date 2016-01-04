package edu.carleton.choe;

/**
 * Created by EunjinCho on 9/24/15.
 */
public class Account {

    // instance variable that keeps track of balance
    private int myBalance;
    // instance variable for parent account
    private Account parentAccount;


    /**
     * Initialize an account with the given balance.
     */
    public Account(int balance) {
        this.myBalance = balance;
        this.parentAccount = null;
    }


    public Account(int balance, Account parentAccount) {
        this.myBalance = balance;
        this.parentAccount = parentAccount;
    }


    /**
     * Add the given amount to the account.
     */
    public void deposit(int amount) {
        if (amount < 0) {
            System.out.println("Cannot deposit negative amount.");
        } else {
            this.myBalance = this.myBalance + amount;
        }
    }

    /**
     * Subtract the given amount from the account if possible. If the amount
     * would leave a negative balance, print an error message and leave the
     * balance unchanged.
     */
    public boolean withdraw(int amount) {
        // Invalid amount
        if (amount < 0) {
            System.out.println("Cannot withdraw negative amount.");
            return false;

        }
        // If there's not enough money, withdraw overdraft from the parent account
        else if (this.myBalance < amount) {
            int overdraftAmt = amount - this.myBalance;
            // If no parent account, no way to withdraw
            if (this.parentAccount == null) {
                System.out.println("Error: insufficient funds and no overdraft account");
                return false;
            }
            // If parent account doesn't have enough money, no way to withdraw
            if (this.parentAccount.myBalance < overdraftAmt) {
                System.out.println("Error: insufficient funds and no overdraft account");
                return false;
            }

            this.myBalance = 0;
            parentAccount.myBalance -= overdraftAmt;
            return true;

        } else {
            this.myBalance = this.myBalance - amount;
            return true;
        }
    }

    /**
     * Return the number of dollars in the account.
     */
    public int balance() {
        return this.myBalance;
    }

    /**
     * Merge account "anotherAcct" into this one by
     * removing all the money from anotherAcct and
     * adding that amount to this one.
     */
    public void merge (Account anotherAcct) {
        this.myBalance += anotherAcct.myBalance;
        anotherAcct.myBalance = 0;
    }

}
