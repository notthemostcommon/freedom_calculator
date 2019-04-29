package com.notthemostcommon.creditcardpayoff.User.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UserQueryDTO {
    public UserQueryDTO(Long id,
                        String firstName,
                        String lastName,
                        String username,
                        float additionaPayment,
                        BigDecimal totalInterestAccrued) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.additionaPayment = additionaPayment;
        this.totalInterestAccrued = totalInterestAccrued;
    }

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private float additionaPayment;

    private BigDecimal totalInterestAccrued;

}
