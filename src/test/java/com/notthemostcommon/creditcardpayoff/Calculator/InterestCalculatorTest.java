package com.notthemostcommon.creditcardpayoff.Calculator;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class InterestCalculatorTest {


    private InterestCalculator iCalc = new InterestCalculator();


    @Test
    public void calculatesInterest(){
        double apr = 10.00;
        double balance = 100.00;
        String interest = iCalc.calculateInterest(apr, balance);

        assertEquals("0.83", interest);

    }

    @Test
    @DisplayName("Return 1 month of interest")
    public void calculatesNoInterest(){

        double monthlyPayment = 100.00;
        Map<String, Object> interestCalc = iCalc.calculateTotalAccruedInterest(10, 100, monthlyPayment);
        String interestPaid = interestCalc.get("accruedInterest").toString();

        assertEquals("0.83", interestPaid );

    }

    @Test
    @DisplayName("Calculates 2 months of interest")
    public void calculates2MonthsInterest(){

        double monthlyPayment = 50.00;
        Map<String, Object> interestCalc = iCalc.calculateTotalAccruedInterest(10, 100, monthlyPayment);
        String interestPaid = interestCalc.get("accruedInterest").toString();

        assertEquals("1.66", interestPaid );

    }

    @Test
    @DisplayName("Counts number of months of payments")
    public void returns3Months(){
        String monthsTilPayoff = "3";
        double monthlyPayment = 50.00;
        Map<String, Object> interestCalc = iCalc.calculateTotalAccruedInterest(10, 100, monthlyPayment);
        String months = interestCalc.get("monthsOfPayments").toString();
        assertEquals(monthsTilPayoff,  months );

    }

    @Test
    @DisplayName("Returns 1 month of payment")
    public void returns1Month(){
        String monthsTilPayoff = "1";
        double monthlyPayment = 100.83;
        Map<String, Object> interestCalc = iCalc.calculateTotalAccruedInterest(10, 100, monthlyPayment);
        String months = interestCalc.get("monthsOfPayments").toString();
        assertEquals(monthsTilPayoff,  months );

    }


}