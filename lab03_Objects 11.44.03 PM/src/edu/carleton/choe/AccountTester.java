package edu.carleton.choe;

/**
 * Created by EunjinCho on 9/24/15.
 */
public class AccountTester {

    public static void main(String[] args) {
        Account mike;
        mike = new Account(1000);
        //System.out.println(mike.balance());
        mike.deposit(100);
        //System.out.println(mike.balance());
        mike.withdraw(200);
        //System.out.println(mike.balance());

        Account kathy;
        Account megan;
        kathy = new Account(500);
        megan = new Account(100, kathy);
        megan.withdraw(50);
        System.out.println("megan's balance: " + megan.balance());
        System.out.println("kathy's balance: " + kathy.balance());
        System.out.println();
        megan.deposit(50);
        megan.withdraw(200);
        System.out.println("megan's balance: " + megan.balance());
        System.out.println("kathy's balance: " + kathy.balance());
        System.out.println();
        megan.deposit(100);
        kathy.deposit(100);
        megan.withdraw(700);
    }

}
