package com.notthemostcommon.creditcardpayoff.Calculators;

import com.notthemostcommon.creditcardpayoff.Debts.DebtRepository;
import com.notthemostcommon.creditcardpayoff.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class DebtCalcServImpl implements CalculatorService {

    @Autowired
    DebtRepository debtRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public float calculateTotal() {

        return 0;
    }
}
