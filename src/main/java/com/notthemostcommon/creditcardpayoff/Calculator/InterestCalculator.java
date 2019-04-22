package com.notthemostcommon.creditcardpayoff.Calculator;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class InterestCalculator {

    public InterestCalculator( ){};

    DecimalFormat df = new DecimalFormat("##0.00");

    public String calculateInterest(double apr, double balance ) {
        double convertPercentage = apr/100;
        double monthlyRate = convertPercentage/12;
        double interestAccrued = balance * monthlyRate;
        String formattedInterest = df.format(interestAccrued);

        return formattedInterest;
    }

    public Map<String,Object> calculateTotalAccruedInterest(double apr, double balance, double payment){
        double accruedInterest = 0.00;
        double remainingBalance = balance;
        int month = 0;

        while(remainingBalance >= payment) {
            String monthlyInterest = calculateInterest(apr, balance);
            accruedInterest += Double.parseDouble(monthlyInterest);
            remainingBalance = (remainingBalance + accruedInterest) - payment;
            month++;
        }
        if (remainingBalance > 0) month++;
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("accruedInterest", df.format(accruedInterest));
        results.put("remainingBalance", remainingBalance);
        results.put("monthsOfPayments", month);

        return results;

    }




}
