package com.notthemostcommon.creditcardpayoff.Calculator;

import java.text.DecimalFormat;

public class InterestCalculator {

    public InterestCalculator(){};

    DecimalFormat df = new DecimalFormat("###.##");

    public String calculateInterest(Double apr, Double balance ) {
        double convertPercentage = apr/100;
        double monthlyRate = convertPercentage/12;
        double interestAccrued = balance * monthlyRate;
        String formattedInterest = df.format(interestAccrued);

        return formattedInterest;
    }


}
