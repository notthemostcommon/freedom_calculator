package com.notthemostcommon.creditcardpayoff.Debts;

import com.notthemostcommon.creditcardpayoff.User.AppUser;

public class DebtBuilder {

    long id;
    String debtName;
    float apr;
    float balance;
    float creditLimit;
    float minPayment;
    AppUser appUser;


    public DebtBuilder(){};

    public DebtBuilder withId(long id){
        this.id = id;
        return this;
    }

    public DebtBuilder withDebtName(String debtName){
        this.debtName = debtName;
        return this;
    }

    public DebtBuilder withApr(float apr){
        this.apr = apr;
        return this;
    }

    public DebtBuilder withBalance(float balance){
        this.balance = balance;
        return this;
    }

    public DebtBuilder withCreditLimit(float creditLimit){
        this.creditLimit = creditLimit;
        return this;
    }

    public DebtBuilder withMinPayment(float minPayment){
        this.minPayment = minPayment;
        return this;
    }

    public DebtBuilder withUser(AppUser user){
        this.appUser = user;
        return this;
    }

    public Debt build() {
        return new Debt(id, debtName, apr, balance, creditLimit, minPayment, appUser);
    }
}
