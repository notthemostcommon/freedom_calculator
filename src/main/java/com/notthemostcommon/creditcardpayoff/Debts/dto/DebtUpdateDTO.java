package com.notthemostcommon.creditcardpayoff.Debts.dto;

import com.notthemostcommon.creditcardpayoff.PayoffStrategy.PayoffStrategy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class DebtUpdateDTO {

    @Id
    private long id;

    @Column(name="debt_name")
    private String debtName;

    @Column(name="apr")
    private float apr;

    @Column(name="balance")
    private float balance;

    @Column(name="credit_limit")
    private float creditLimit;

    @Column(name="min_payment")
    private float minPayment;

    @Column(name="months_of_payments")
    int monthsOfPayments;

    @Column(name="final_payment")
    float finalPaymentAmount;

    @Column(name="interest_accrued")
    float interestAccrued;

    @Column(name="current_month")
    String currentMonth;

    @OneToOne
    @JoinColumn(name="strategy_id")
    PayoffStrategy strategy;
}
