package com.sgcc.javase.banking7;

public class SavingAccount extends Account{

    private double interestRate;

    public SavingAccount(double init_balance, double interestRate){
        super(init_balance);
        this.interestRate = interestRate;
    }

}
