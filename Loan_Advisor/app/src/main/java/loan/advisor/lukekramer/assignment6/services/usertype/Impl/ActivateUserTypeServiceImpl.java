package com.example.lukekramer.assignment6.services.usertype.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.lukekramer.assignment6.chain.ClientChainSetup;
import com.example.lukekramer.assignment6.config.util.DomainState;
import com.example.lukekramer.assignment6.services.usertype.ActivateUserTypeService;

/**
 * Created by lukekramer on 08/05/16.
 */
public class ActivateUserTypeServiceImpl extends Service implements ActivateUserTypeService{

    private final IBinder localBinder = new ActivateUserTypeServiceLocalBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ActivateUserTypeServiceLocalBinder extends Binder
    {
        public ActivateUserTypeServiceImpl getService()
        {
            return ActivateUserTypeServiceImpl.this;
        }

    }

    @Override
    public String activateUserTypeAccount(long income) {

        if(true)
        {
           ClientChainSetup.getClientType(income);


            return DomainState.ACTIVATED.name();
        }
        else
        {
            return DomainState.NOTACTIVATED.name();
        }
    }


}
