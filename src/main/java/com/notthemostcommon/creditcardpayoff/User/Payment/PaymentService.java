package com.notthemostcommon.creditcardpayoff.User.Payment;

import com.notthemostcommon.creditcardpayoff.User.dto.AdditionalPaymentUpdateDTO;
import com.notthemostcommon.creditcardpayoff.User.dto.UserQueryDTO;

public interface PaymentService {

    public UserQueryDTO updatePayment(Long id, AdditionalPaymentUpdateDTO additionalPaymentUpdateDTO);
}
