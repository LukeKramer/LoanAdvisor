package com.example.lukekramer.assignment6.services.loan.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.example.lukekramer.assignment6.entity.Loan;
import com.example.lukekramer.assignment6.repository.loan.Impl.LoanRepositoryImpl;
import com.example.lukekramer.assignment6.services.loan.ActivateLoanService;

/**
 * Created by lukekramer on 10/05/16.
 */
//The Handling of the loann actions is done By an IntentService on the background thread
public class ActivateLoanServiceImpl extends IntentService implements ActivateLoanService{

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */

    private static final String ACTION_ADD = "com.example.lukekramer.assignment6.services.loan.action.ACTION_ADD";
    private static final String EXTRA_ADD = "com.example.lukekramer.assignment6.services.loan.action.EXTRA_ADD";

    private static final String ACTION_UPDATE = "com.example.lukekramer.assignment6.services.loan.action.ACTION_UPDATE";
    private static final String EXTRA_UPDATE = "com.example.lukekramer.assignment6.services.loan.action.EXTRA_UPDATE";

    private static ActivateLoanServiceImpl service = null;

    public ActivateLoanServiceImpl() {
        super("ActivateLoanServiceImpl");


    }

    public static ActivateLoanServiceImpl getInstance() {
        if (service == null)
            service = new ActivateLoanServiceImpl();

        return service;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Loan loan = (Loan) intent.getSerializableExtra(EXTRA_ADD);
                postClient(loan);
            }
            else if(ACTION_UPDATE.equals(action))
            {
                final Loan loan =(Loan) intent.getSerializableExtra(EXTRA_UPDATE);
                clientUpdate(loan);
            }
        }

    }

    @Override
    public void addLoan(Context context, Loan loan) {

        Intent intent = new Intent(context, ActivateLoanServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, loan);
        context.startService(intent);

    }

    @Override
    public void updateLoan(Context context, Loan loan) {

        Intent intent = new Intent(context, ActivateLoanServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, loan);
        context.startService(intent);

    }

    private void clientUpdate(Loan loan)
    {
        LoanRepositoryImpl loanRepository = new LoanRepositoryImpl(getBaseContext());
        loanRepository.update(loan);
    }

    private void postClient(Loan loan) {

       LoanRepositoryImpl loanRepository = new LoanRepositoryImpl(getBaseContext());
        //POST and Save Local
        loanRepository.save(loan);
    }
}
