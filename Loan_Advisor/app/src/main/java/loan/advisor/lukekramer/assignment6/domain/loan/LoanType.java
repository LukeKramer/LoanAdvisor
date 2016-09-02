package com.example.lukekramer.assignment6.domain.loan;

/**
 * Created by student on 2016/03/31.
 */
public abstract class LoanType {

    LoanType nextLoan;

    public void setNextLoan(LoanType nextLoan) {
        this.nextLoan = nextLoan;
    }
    public abstract String handleRequest(String Type,long request);
}
