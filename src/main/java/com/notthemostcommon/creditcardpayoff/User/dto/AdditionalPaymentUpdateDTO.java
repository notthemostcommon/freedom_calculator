package com.notthemostcommon.creditcardpayoff.User.dto;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AdditionalPaymentUpdateDTO {

    @Id
    @NotNull
    private Long id;

    private float additionalPayment;

    public float getAdditionalPayment() {
        return additionalPayment;
    }
}
