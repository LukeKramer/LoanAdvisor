package com.example.lukekramer.assignment6.services.loan;

import android.content.Context;

import com.example.lukekramer.assignment6.entity.Loan;

/**
 * Created by lukekramer on 10/05/16.
 */
public interface ActivateLoanService {

    void addLoan(Context context, Loan loan);

    void updateLoan(Context context, Loan loan);

}
