package com.sgcc.javase.banking7;

public class CheckingAccount extends Account{

    private Double overdraftProtection;

    public CheckingAccount(double init_balance){
        super(init_balance);
    }

    public CheckingAccount(double init_balance, double overdraftProtection){
        super(init_balance);
        this.overdraftProtection = overdraftProtection;
    }

    @Override
    public void withdraw(double amt) {
        if (balance >= amt){
            balance -= amt;
        }else {
            if (overdraftProtection == null){
                throw  new OverdraftException("no overdraft protection", (amt - balance));
            }
            if (overdraftProtection >= (amt - balance)){
                overdraftProtection -= (amt - balance);
            }else {
                throw new OverdraftException("Insufficient funds for overdraft protection", (amt - balance));
            }
        }
    }
}
