package com.example.lukekramer.assignment6.services.bank.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.lukekramer.assignment6.config.util.BankingSetup;
import com.example.lukekramer.assignment6.config.util.DomainState;
import com.example.lukekramer.assignment6.services.bank.ActivateBankConfirmationService;

/**
 * Created by lukekramer on 08/05/16.
 *
 */
//This is a bound service (on the UI thread)because the account needs to be verified before a loan can be made
public class ActivateBankConfirmationServiceImpl extends Service implements ActivateBankConfirmationService {

    private IBinder localbinder = new ActivateBankConfirmationServiceLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localbinder;
    }



    public class ActivateBankConfirmationServiceLocalBinder extends Binder
    {
        public ActivateBankConfirmationServiceImpl getService()
        {
            return ActivateBankConfirmationServiceImpl.this;
        }

    }

    @Override
    public String activateBankConfirmationAccount(String accountnumber) {

        if(true) {

            BankingSetup.ConfirmBankAccount(accountnumber);
            return DomainState.ACTIVATED.name();
        }
       return DomainState.NOTACTIVATED.name();
    }







}
