package com.notthemostcommon.creditcardpayoff.Debts;

import com.notthemostcommon.creditcardpayoff.User.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="debt")
public class Debt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "appUser_id")
    private AppUser appUser;

//    protected Debt(){}
////
//    public Debt(String debtName, float apr, float balance, float creditLimit, float minPayment) {
//        this.debtName = debtName;
//        this.apr = apr;
//        this.balance = balance;
//        this.creditLimit = creditLimit;
//        this.minPayment = minPayment;
//    }


}
