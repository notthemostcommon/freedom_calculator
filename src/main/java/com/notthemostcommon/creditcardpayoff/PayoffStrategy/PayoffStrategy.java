package com.notthemostcommon.creditcardpayoff.PayoffStrategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="payoff_strategy")
public class PayoffStrategy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="strategy")
    private String strategy;

}
