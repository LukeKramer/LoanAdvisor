package com.example.lukekramer.assignment6.services.loanstatus.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.lukekramer.assignment6.chain.LoanChainSetup;
import com.example.lukekramer.assignment6.config.util.DomainState;
import com.example.lukekramer.assignment6.services.loanstatus.ActivateLoanResultService;

/**
 * Created by lukekramer on 08/05/16.
 */
//The Handling of the client actions is done By an IntentService on the backgroun thread
public class ActivateLoanResultServiceImpl extends Service implements ActivateLoanResultService{

    private final IBinder localBinder = new ActivateLoanResultServiceLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }



    public class ActivateLoanResultServiceLocalBinder extends Binder
    {
        public ActivateLoanResultServiceImpl getService()
        {
            return ActivateLoanResultServiceImpl.this;
        }

    }

    @Override
    public String activateLoanResultAccount(String type, long income) {

        if(true)
        {
            LoanChainSetup.getLoanType(type,income);

            return DomainState.ACTIVATED.name();
        }
        else
        {
            return DomainState.NOTACTIVATED.name();
        }
    }




}
