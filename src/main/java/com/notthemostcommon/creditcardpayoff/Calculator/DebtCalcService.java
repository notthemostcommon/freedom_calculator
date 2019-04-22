package com.notthemostcommon.creditcardpayoff.Calculator;

import com.notthemostcommon.creditcardpayoff.Debts.Debt;
import com.notthemostcommon.creditcardpayoff.Debts.DebtRepository;
import com.notthemostcommon.creditcardpayoff.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class DebtCalcService {

    @Autowired
    DebtRepository debtRepository;

    @Autowired
    UserRepository userRepository;


    public double calculateTotal (Principal principal, String type) {
        Long userId = userRepository.findByUsername(principal.getName()).getId();
        List<Debt> debtList = debtRepository.findByAppUserId(userId);
        double totals = 0.00;
        for (Debt nums : debtList ) {
        switch(type){
            case "balance":
                totals += nums.getBalance();
                break;
            case "creditLimit":
                totals += nums.getCreditLimit();
                break;
            case "minPayment":
                totals += nums.getMinPayment();
                break;
            default:
                return totals;
            }

        }
        return totals;
    }

    public float calcTotal ( List<Debt> list, String type) {
        float totals =  0.00f;
        for (Debt nums : list ) {
            switch(type){
                case "balance":
                    totals += nums.getBalance();
                    break;
                case "creditLimit":
                    totals += nums.getCreditLimit();
                    break;
                case "minPayment":
                    totals += nums.getMinPayment();
                    break;
                default:
                    return totals;
            }

        }
        return totals;
    }

    public float calculateUsage(float balanceTotal, float limitTotal){
      float usage = balanceTotal/limitTotal;
      String u = String.format("%.2f", usage);
        float f = Float.valueOf(u.trim()).floatValue();
        System.out.println(f);

        return f;
    }

}
