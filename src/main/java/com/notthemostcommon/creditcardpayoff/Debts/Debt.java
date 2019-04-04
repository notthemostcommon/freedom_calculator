package com.notthemostcommon.creditcardpayoff.Debts;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Debt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String debtName;

    protected Debt(){}

    public Debt(String debtName) {
        this.debtName = debtName;
    }


}
