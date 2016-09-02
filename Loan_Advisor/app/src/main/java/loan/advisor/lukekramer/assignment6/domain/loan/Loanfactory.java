package com.example.lukekramer.assignment6.domain.loan;

import com.example.lukekramer.assignment6.entity.Loan;
import com.example.lukekramer.assignment6.interfaces.CreateLoan;

/**
 * Created by student on 2016/03/31.
 */
public class Loanfactory implements CreateLoan {

    public Loan getLoan() {

        Loan loan = new Loan.Builder()
                .maxLoanAmount((long) 100000)
                .minLoanAmount((long) 1000)
                .build();
        return loan;
    }
}
