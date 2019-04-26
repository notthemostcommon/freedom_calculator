package com.notthemostcommon.creditcardpayoff.StrategyTypes;

import com.notthemostcommon.creditcardpayoff.Calculator.DebtCalcService;
import com.notthemostcommon.creditcardpayoff.Calculator.InterestCalculator;
import com.notthemostcommon.creditcardpayoff.Debts.Debt;
import com.notthemostcommon.creditcardpayoff.Debts.DebtRepository;
import com.notthemostcommon.creditcardpayoff.User.AppUser;
import com.notthemostcommon.creditcardpayoff.User.UserRepository;
import com.notthemostcommon.creditcardpayoff.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SnowballStrategy {

    // need the payoostrategycalculation
    // need the debts
    // order debts based on strategy
    // update payoffstrategycalculation
    // calculations is highest balance first
    // capture remaining payment and apply to next highest balance

    @Autowired
    DebtRepository debtRepository;

    @Autowired
    UserRepository userRepository;

    InterestCalculator interestCalculator = new InterestCalculator();

    public List<Debt> arrangedList (List<Debt> debtList) {
        debtList.sort(Comparator.comparing(Debt::getBalance).reversed());

        return debtList;
    }

    // need to update debt table with values calculated incl
    // monthsToPayoff, finalPayment, interestAccrued, strategy
    // calculate and return payments of each debt
    public List<Object> payoffSchedule (Principal principal){
//        Long userId = Utils.getAuthUserId();
        String username = principal.getName();
        Long user_Id = userRepository.findByUsername(username).getId();
        List<Debt> debtList =  debtRepository.findByAppUserId(user_Id);
        List<Debt> arrangedList = arrangedList(debtList);
        List<Object> results = new ArrayList<>();
        for (Debt debt : arrangedList) {
            Map<String, Object> result = interestCalculator.calculateTotalAccruedInterest(debt.getApr(), debt.getBalance(), debt.getMinPayment());
            results.add(result);
        }
        System.out.println(user_Id.toString());
        return results;

    }




}
