package com.sgcc.javase.banking5_2;

public class Customer {

    private String firstName;
    private String lastName;

    private Account[] accounts;
    private int numberOfAccounts;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        accounts = new Account[2];
    }

    public Account getAccount(int index) {
        return accounts[index];
    }

    public void addAccount(Account account) {
        accounts[numberOfAccounts++] = account;
    }

    public int getNumOfAccounts() {
        return numberOfAccounts;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}