package com.example.lukekramer.assignment6.services;

import android.content.Context;
import android.test.AndroidTestCase;

import com.example.lukekramer.assignment6.entity.Loan;
import com.example.lukekramer.assignment6.repository.loan.Impl.LoanRepositoryImpl;
import com.example.lukekramer.assignment6.repository.loan.LoanRepository;
import com.example.lukekramer.assignment6.repository.tables.CreateTables;
import com.example.lukekramer.assignment6.services.loan.ActivateLoanService;
import com.example.lukekramer.assignment6.services.loan.Impl.ActivateLoanServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by lukekramer on 10/05/16.
 */
public class ActivateLoanServiceTest extends AndroidTestCase {

    private static final String TAG_LOAN="Loan TEST";

    public void testLoanIntentService() throws Exception {

        CreateTables createTables = new CreateTables(this.getContext());
        createTables.resetDatabase();
        createTables.createTables();

        Context context = getContext();

        //Add loan

        LoanRepository loanRepository = new LoanRepositoryImpl(this.getContext());

        ActivateLoanService loanService = ActivateLoanServiceImpl.getInstance();

        Loan standardLoan = new Loan.Builder()
                .maxLoanAmount((long)100000)
                .minLoanAmount((long)100)
                .build();

        loanService.addLoan(context,standardLoan);

        Assert.assertNotNull(standardLoan);

        //Update loan

        Loan updateLoan = new Loan.Builder()
                .copy(standardLoan)
                .minLoanAmount((long)1000)
                .build();

        loanService.updateLoan(context,updateLoan);

        Assert.assertNotNull(updateLoan);

        Thread.sleep(5000);

        Set<Loan> loans = loanRepository.findAll();
        Assert.assertTrue(TAG_LOAN+" READ ALL",loans.size()>0);



    }
}
