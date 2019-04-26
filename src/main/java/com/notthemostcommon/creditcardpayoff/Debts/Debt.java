package com.notthemostcommon.creditcardpayoff.Debts;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.notthemostcommon.creditcardpayoff.PayoffStrategy.PayoffStrategy;
import com.notthemostcommon.creditcardpayoff.User.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="debt")
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="debt_name")
    private String debtName;

    @Column(name="apr")
    private float apr;

    @Column(name="balance")
    private float balance ;

    @Column(name="credit_limit")
    private float creditLimit;

    @Column(name="min_payment")
    private float minPayment;

    @ManyToOne
    @JoinColumn(name = "appUser_id")
    @JsonBackReference
    private AppUser appUser;

    @Column(name="months_of_payments")
    int monthsOfPayments;

    @Column(name="final_payment")
    float finalPaymentAmount;

    @Column(name="interest_accrued")
    BigDecimal interestAccrued;

    @Column(name="current_month")
    String currentMonth;

    @OneToOne
    @JoinColumn(name="strategy_id")
    PayoffStrategy strategy;



    public Debt(Long id, String debtName, float apr, float balance, float creditLimit, float minPayment, AppUser appUser){
        this.id=id;
        this.debtName = debtName;
        this.apr = apr;
        this.balance = balance;
        this.creditLimit = creditLimit;
        this.minPayment = minPayment;
        this.appUser = appUser;
    }




}
