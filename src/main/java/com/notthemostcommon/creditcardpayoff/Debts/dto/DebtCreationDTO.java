package com.notthemostcommon.creditcardpayoff.Debts.dto;

import com.notthemostcommon.creditcardpayoff.User.AppUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
public class DebtCreationDTO {

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
}
