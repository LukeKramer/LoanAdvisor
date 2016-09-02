package com.example.lukekramer.assignment6.chain;

import com.example.lukekramer.assignment6.domain.loan.LoanGeneral;
import com.example.lukekramer.assignment6.domain.loan.LoanOther;
import com.example.lukekramer.assignment6.domain.loan.LoanPremium;
import com.example.lukekramer.assignment6.domain.loan.LoanType;

/**
 * Created by student on 2016/03/31.
 */
public class LoanChainSetup {

    public static String getLoanType(String name,long value){
        LoanType chain = setUpChain();
        return chain.handleRequest(name,value);
    }


    public static LoanType setUpChain()
    {
        LoanType a = new LoanPremium();
        LoanType b = new LoanGeneral();
        LoanType c = new LoanOther();

        a.setNextLoan(b);
        b.setNextLoan(c);
        return a;
    }
}
